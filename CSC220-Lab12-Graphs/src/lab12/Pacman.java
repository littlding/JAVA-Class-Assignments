package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

    /** representation of the graph as a 2D array */
    private Node[][] maze;
    /** input file name */
    private String inputFileName;
    /** output file name */
    private String outputFileName;
    /** height and width of the maze */
    private int height;
    private int width;
    /** starting (X,Y) position of Pacman */
    private int startX;
    private int startY;

    /** A Node is the building block for a Graph. */
    private static class Node {
        /** the content at this location */
        private char content;
        /** the row where this location occurs */
        private int row;
        /** the column where this location occurs */
        private int col;
        private boolean visited;
        private Node parent;

        public Node(int x, int y, char c) {
            visited = false;
            content = c;
            parent =  null;
            this.row = x;
            this.col = y;
        }

        public boolean isWall() { return content == 'X'; }
        public boolean isVisited() { return visited; }
    }

    /** constructor */
    public Pacman(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        buildGraph();
    }

    private boolean inMaze(int index, int bound){
        return index < bound && index >= 0;
    }

    /** helper method to construct the solution path from S to G
     *  NOTE this path is built in reverse order, starting with the goal G.
     */
    private void backtrack(Node end){
        // TODO for assignment12
        if(end.content=='S'){
            return;
        }
        if(end.content!='G'){
            end.content = '.';
        }
        backtrack(end.parent);
    }

    /** writes the solution to file */
    public void writeOutput() {
        // TODO for lab12
        try {
            PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
            // FILL IN
            String s = "";
            s += height + " " + width + "\n";
            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    s += maze[i][j].content;
                }
                s += "\n";
            }
            output.print(s);
            output.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        String s = "";
        s += height + " " + width + "\n";
        for (int i = 0; i < height; i++){
            for (int j = 0; j < width; j++){
                s += maze[i][j].content + " ";
            }
            s += "\n";
        }
        return s;
    }

    /** helper method to construct a graph from a given input file;
     *  all member variables (e.g. maze, startX, startY) should be
     *  initialized by the end of this method
     */
    private void buildGraph() {
        // TODO for lab12
        try {
            // don't forget to close input when you're done
            BufferedReader input = new BufferedReader(
                    new FileReader(inputFileName));
            // FILL IN
            String line = input.readLine();
            String[] size = line.split(" ");
            this.height = Integer.parseInt(size[0]);
            this.width = Integer.parseInt(size[1]);
            this.maze = new Node[this.height][this.width];
            for(int i=0;i<this.height;i++){
                line=input.readLine();
                for(int j=0;j<this.width;j++){
                    char ch = line.charAt(j);
                    this.maze[i][j] = new Node(i, j, ch);
                    if(ch=='S'){
                        this.startX = i;
                        this.startY = j;
                    }
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /** obtains all neighboring nodes that have *not* been
     *  visited yet from a given node when looking at the four
     *  cardinal directions: North, South, West, East (IN THIS ORDER!)
     *
     * @param currentNode the given node
     * @return an ArrayList of the neighbors (i.e., successors)
     */
    public ArrayList<Node> getNeighbors(Node currentNode) {
        // TODO for assignment12
        Node north, south, east, west;
        int x = currentNode.row;
        int y = currentNode.col;
        // 0. Initialize an ArrayList of nodes
        ArrayList<Node> result = new ArrayList<>();
        // 1. Inspect the north neighbor
        int nx = x - 1;
        int ny = y;
        if(nx>=0&&nx<this.height&&ny>=0&&ny<this.width){
            north = this.maze[nx][ny];
            if(!north.isVisited()&&!north.isWall()){
                result.add(north);
            }
        }
        // 2. Inspect the south neighbor
        nx = x + 1;
        ny = y;
        if(nx>=0&&nx<this.height&&ny>=0&&ny<this.width){
            south = this.maze[nx][ny];
            if(!south.isVisited()&&!south.isWall()){
                result.add(south);
            }
        }
        // 3. Inspect the west neighbor
        nx = x;
        ny = y - 1;
        if(nx>=0&&nx<this.height&&ny>=0&&ny<this.width){
            west = this.maze[nx][ny];
            if(!west.isVisited()&&!west.isWall()){
                result.add(west);
            }
        }
        // 4. Inspect the east neighbor
        nx = x;
        ny = y + 1;
        if(nx>=0&&nx<this.height&&ny>=0&&ny<this.width){
            east = this.maze[nx][ny];
            if(!east.isVisited()&&!east.isWall()){
                result.add(east);
            }
        }
        return result;
    }

    /** Pacman uses BFS strategy to solve the maze */
    public void solveBFS() {
        // TODO for assignment12
        Queue<Node> queue = new LinkedList<>();
        Node startNode = this.maze[this.startX][this.startY];
        startNode.visited = true;
        queue.offer(startNode);
        while(!queue.isEmpty()){
            Node cur = queue.poll();
            if(cur.content=='G'){
                backtrack(cur);
                break;
            }
            ArrayList<Node> neighbors = getNeighbors(cur);
            for(Node node:neighbors){
                node.parent = cur;
                node.visited = true;
                queue.offer(node);
            }
        }
    }

    /** Pacman uses DFS strategy to solve the maze */
    public void solveDFS() {
        // TODO for assignment12
        Stack<Node> stack = new Stack<>();
        Node startNode = this.maze[this.startX][this.startY];
        startNode.visited = true;
        stack.push(startNode);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            if(cur.content=='G'){
                backtrack(cur);
                break;
            }
            ArrayList<Node> neighbors = getNeighbors(cur);
            for(Node node:neighbors){
                node.visited = true;
                node.parent = cur;
                stack.push(node);
            }
        }
    }

}