package lab12;

public class tester {
    public static void main(String[] args) {
        String inputFile = "mazes/randomMaze.txt";
        String outputFile = "out.txt";
        Pacman pacman = new Pacman(inputFile, outputFile);
        pacman.solveDFS();
        pacman.writeOutput();
    }
}
