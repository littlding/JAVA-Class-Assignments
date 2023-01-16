package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {

	/**
	 * Determines if the first operator has same or greater precedence than the
	 * second
	 *
	 * @param op1 the first operator
	 * @param op2 the second operator
	 * @return the boolean result
	 */
	private static boolean hasPrecedence(String op1, String op2) {
		return opToPrcd(op1) >= opToPrcd(op2); // placeholder
	}

	/**
	 * Converts an operator to its precedence priority
	 *
	 * We expect you to be able to handle +, -, *, /, ^, and ( (why don't you need
	 * ")" as well? see algorithm in part 4)
	 *
	 * The order of these is as follows: ^, * and /, + and -, (
	 *
	 * @param op a string representing an operator, e.g. "+" or "-"
	 * @return an integer value reflecting its precedence
	 */
	private static int opToPrcd(String op) {
		switch (op) {
		case "^":
			return 4;
		case "*":
		case "/":
			return 3;
		case "-":
		case "+":
			return 2;
		case "(":
			return 1;
		default:
			return 0;

		}
		// placeholder
	}

	/**
	 * determines if the input token is an operator
	 *
	 * @param token the string token to check
	 * @return a boolean reflecting the result
	 */
	private static boolean isOperator(String token) {
		if (token.equals(")")) {
			return true;
		}
		return opToPrcd(token) > 0;
		// placeholder
	}

	/**
	 * Evaluates an expression
	 *
	 * NOTE Beware the order of pop and evaluation when receiving operand1 and
	 * operand2 as input.
	 *
	 * @param operand1 the first operand
	 * @param operator the operator to apply
	 * @param operand2 the second operand
	 * @return a double expressing the result
	 * @throws IllegalArgumentException if operator passed is not one of "+", "-",
	 *                                  "*", "/", or "^"
	 *
	 */
	private static double evaluate(double operand1, String operator, double operand2) {
		switch (operator) {
		case "^":
			return Math.pow(operand1, operand2);
		case "*":
			return operand1 * operand2;
		case "/":
			return operand1 / operand2;
		case "-":
			return operand1 - operand2;
		case "+":
			return operand1 + operand2;
		default:
			throw new RuntimeException("IllegalArgumentException" + operator + "is not valid");
		}
	}

	/**
	 * give a description of the purpose of this method
	 * 
	 * @param line fill in
	 * @return fill in
	 */
	public static double infixEvaluator(String line) {
		StringSplitter data = new StringSplitter(line);

		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();

//		data.hasNext()-> there is something in the data
//		data.next()->pop off the data
//		data.peek()->looks at the top
//		
//		operands.pop();
//		operands.peek();
//		operands.push(null);
//		operands.isEmpty();
//		operands.size();
//		
//		double d = new Double.parseDouble("2,1");

//	1. Scan the input string (infix notation) from left to right. One pass is sufficient, 
//		take  one token at a time. Each type of token is treated differently: 
		while (data.hasNext()) {
			String curr = data.next();

//		1.1. number: push it onto the operand stack. 
			if (!isOperator(curr)) {
				double d = Double.parseDouble(curr);
				operands.push(d);
			}
//		1.2. a left parenthesis: push it onto the operator stack. 
			else if (curr.equals("(")) {
				operators.push(curr);
			}
//		1.3. a right parenthesis: 
			else if (curr.equals(")")) {

//			1.3.1. while the thing on top of the operator stack is not a left parenthesis
				while (!operators.peek().equals("(")) {
//				1.3.1.1. pop an operator from the operator stack, pop two operands from the operand stack, 
//				apply the operator to the operands, in the correct order, push the result onto the operand stack. 
					String op = operators.pop();
					double num2 = operands.pop();
					double num1 = operands.pop();
					double ans = evaluate(num1, op, num2);
					operands.push(ans);

				}

//			1.3.2. pop the left parenthesis from the operator stack and discard it 
//			1.4. an operator (call it current operator): 
				operators.pop();
			}

//		1.4.1. while the operator stack is not empty, and the top thing on the operator  
//		stack has the same or greater precedence as the current operator [“(“ should have 
//				the smallest precedence as is suggested in the code comments]
			else if (isOperator(curr)) {
				while (!operators.isEmpty() && hasPrecedence(operators.peek(), curr)) {
//				1.4.1.1. pop an operator from the operator stack, pop two operands from the operand stack, 
//						apply the operator to the operands, in the correct order, push the result onto the operand stack
//		1.4.2. push the current operator onto the operator stack
					String op = operators.pop();
					double num2 = operands.pop();
					double num1 = operands.pop();
					double ans = evaluate(num1, op, num2);
					operands.push(ans);
				}
				operators.push(curr);
			}
		}
//
//	2. (After you have traversed the entire StringSplitter queue) While the operator stack is not empty:
		while (!operators.isEmpty()) {
//		2.1. pop an operator from the operator stack, pop two operands from the operand  stack, 
//		apply the operator to the operands, in the correct order, push the  result onto the operand stack 

			String op = operators.pop();
			double num2 = operands.pop();
			double num1 = operands.pop();
			double ans = evaluate(num1, op, num2);
			operands.push(ans);
		}
//	3. At this point the operator stack MUST be empty, and the operand stack MUST have  a single value, 
//	which is the final result.
		if (!operators.isEmpty() && operands.size() != 1) {
			throw new RuntimeException("Shunting Yard Exception");
		}
//	4. pop that value and return it. 

		return operands.pop(); // placeholder

	}

	/**
	 * give a description of the purpose of this method
	 * 
	 * @param line fill in
	 * @return fill in
	 */
	public static String toPostfix(String line) {
//		
//		1. 	Scan the input string (infix notation) from left to right (one pass)
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();

		StringBuilder postfix = new StringBuilder();

		while (data.hasNext()) {
			String curr = data.next();

//			2. 	If the current token is an operand
			if (!isOperator(curr)) {
//				2.1. append it to the postfix string.
				postfix.append(curr);
			} else if (opToPrcd(curr) > 1) {
//				3. 	If the current token is an operator (call it current operator).
//				3.1. Pop from the operator stack and append to the postfix string every operator on the stack that
//	              3.1.1.  is above the most recently scanned left parenthesis, and
//                3.1.2. has precedence higher than to that of the current operator 
				while (curr.equals("(") || !operators.isEmpty() && hasPrecedence(operators.peek(), curr)) {
					String op = operators.pop();

					postfix.append(op);
				}

//			      3.2. Push the current operator onto the stack.
				operators.push(curr);
			} else if (curr.equals("(")) {
//				4. 	If the current token is a left parenthesis 4.1. push it onto the stack.
				operators.push(curr);
			} else if (curr.equals(")")) {
//				5. 	If the current token is a right parenthesis
				while (!operators.peek().equals("(")) {
					
//					5.1. Pop all operators down to the most recently scanned left parenthesis and append them to the postfix string.
					String op = operators.pop();

					postfix.append(op);
				}

//				5.2. Pop the corresponding left parenthesis and discard this pair of parentheses
				operators.pop();
			}
		}

		return postfix.toString(); // placeholder
	}

	public static void main(String[] args) {

		if (infixEvaluator("10 + 2") != 12)
			System.err.println("test1 failed --> your answer should have been 12");

		if (infixEvaluator("10 - 2 * 2 + 1") != 7)
			System.err.println("test2 failed --> your answer should have been 7");

		if (infixEvaluator("100 * 2 + 12") != 212)
			System.err.println("test3 failed --> your answer should have been 212");

		if (infixEvaluator("100 * ( 2 + 12 )") != 1400)
			System.err.println("test4 failed --> your answer should have been 1400");

		if (infixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
			System.err.println("test5 failed --> your answer should have been 100");

		System.out.println("Lab Testing Done!!!");

		/* uncomment the below lines for assignmemt */
		 if (!toPostfix(new String("(4+5)")).equals("45+"))
		     System.err.println("test1 failed --> should have been 45+");

		 if (!toPostfix(new String("((4+5)*6)")).equals("45+6*"))
		     System.err.println("test2 failed --> should have been 45+6*");

		 if (!toPostfix(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
             System.err.println("test3 failed --> should have been 456*7/+8-");

		 if (!toPostfix(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
		     System.err.println("test4 failed --> should have been 4567-*+8/");

		if (!toPostfix(new String("(9+(8*7-(6/5^4)*3)*2)")).equals("987*654^/3*-2*+"))
			System.err.println("test5 failed --> should have been 987*654^/3*-2*+");

		System.out.println("Assignment Testing Done!!!");

	}

}
