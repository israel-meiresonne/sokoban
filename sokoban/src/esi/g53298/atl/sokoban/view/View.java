package esi.g53298.atl.sokoban.view;

import esi.g53298.atl.sokoban.model.Maze;
import esi.g53298.atl.sokoban.model.Square;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author israelmeiresonne
 */
public class View {

    private Scanner in;

    /**
     * Constructor
     */
    public View() {
        this.in = new Scanner(System.in);;
    }

    /**
     * displays a welcome message
     */
    public void initialize() {
        System.out.println("Bienvenue sur Sokoban");
    }
    
    /**
     * displays a level success message
     */
    public void displaySuccess() {
        System.out.println("Bravo! Tu as réussi le niveau");
    }

    /**
     * Displays a goodbye message
     */
    public void quit() {
        System.out.println("Bye Bye");
    }

    /**
     * displays error message passed in argument
     *
     * @param message a error message to display
     */
    public void displayError(String message) {
        System.out.println(message);
    }

    /**
     * displays available commands
     */
    public void displayHelp() {
        System.out.println("Usage:");
        System.out.println("       Choisir un niveau: level");
        System.out.println("       Afficher les commandes disponibles: help");
        System.out.println("       Quitter le jeu: quit");
        System.out.println();
    }
    /**
     * displays available game commands
     */
    public void displayGameCommand() {
        System.out.println("Usage:");
        System.out.println("       Bouger vers le haut: move u");
        System.out.println("       Bouger vers le bas: move d");
        System.out.println("       Bouger vers le gauche: move l");
        System.out.println("       Bouger vers le droite: move r");
        System.out.println("       Annuler le dernier mouvement: undo");
        System.out.println("       Retablir le dernier mouvement: redo");
        System.out.println("       Abandonner la partie: giveup");
        System.out.println("       Recommencer le niveau: restart");
        System.out.println("       Afficher les commandes disponibles: help");
        System.out.println();
    }
    
    /**
     * Display the maze
     * @param maze 
     */
    public void displayMaze(Square[][] maze){
        int nbRow = maze.length;
        int nbCol = maze[0].length;
        for(int row = 0; row < nbRow; row++){
            for(int col = 0; col < nbCol; col++){
                String symbol = maze[row][col].getContentSymbol();
                System.out.print(maze[row][col].getContentSymbol());
            }
            System.out.println();
        }
    }

    /**
     * ask to enter a command and return this command
     *
     * @return a String commande
     */
    public String askCommand() {
        System.out.println("Entrer votre commande");
        String cmd = in.nextLine();
        return cmd;
    }
    
    /**
     * ask to enter a command and return this command
     *
     * @return a String commande
     */
    public String askGameCommand() {
        System.out.println("Entrer votre commande");
        String cmd = in.nextLine();
        return cmd;
    }
    
    /**
     * ask to enter a command and return this command
     *
     * @return a String commande
     */
    public int askLevel() {
        System.out.println("Choisis un niveau");
        String level = in.nextLine();
        return checkInt(level, "Le niveau est incorrect!");
    }
    
        /**
     * Convert string to int if it convertible else throw an error with the 
     * message passed in param
     * @param str the string to convert to int
     * @param errorMsg the error message to throw if the string is 
     * unconvertible to int
     * @return a int conersion double the string passed in param
     * @throws IllegalArgumentException if the string is unconvertible to int
     */
    private int checkInt(String str, String errorMsg) {
        try {
            return Integer.parseInt(str);
        } catch (Throwable e) {
            throw new IllegalArgumentException(errorMsg);
        }
    }
    
//    public static void main(String args[]) throws FileNotFoundException{
//        Maze maze = new Maze(1);
//        View view = new View();
//        Square[][] sq = maze.getMaze();
//        view.displayMaze(maze.getMaze());
//    }
}
