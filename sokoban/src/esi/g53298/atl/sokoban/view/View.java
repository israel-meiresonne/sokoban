package esi.g53298.atl.sokoban.view;

import static esi.g53298.atl.sokoban.controller.Controller.*;
import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.model.Position;
import esi.g53298.atl.sokoban.model.Square;
import java.util.Scanner;

/**
 *
 * @author israelmeiresonne
 */
public class View {

    private Scanner in;
    private ViewPlayer layout;

    /**
     * Constructor
     */
    public View(String args[]) {
        this.in = new Scanner(System.in);
        ViewPlayer layout = new ViewPlayer(args);
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
        System.out.println("       Choisir un niveau: " + CMD_LEVEL);
        System.out.println("       Afficher les commandes disponibles: " + CMD_HELP);
        System.out.println("       Quitter le jeu: " + CMD_QUIT);
        System.out.println();
    }

    /**
     * displays available game commands
     */
    public void displayGameCommand() {
        System.out.println("Usage:");
        System.out.println("       Bouger vers le haut: " + CMD_MOVE + " " + CMD_UP);
        System.out.println("       Bouger vers le bas: " + CMD_MOVE + " " + CMD_DOWN);
        System.out.println("       Bouger vers le gauche: " + CMD_MOVE + " " + CMD_LEFT);
        System.out.println("       Bouger vers le droite: " + CMD_MOVE + " " + CMD_RIGHT);
        System.out.println("       Annuler le dernier mouvement: " + CMD_UNDO);
        System.out.println("       Retablir le dernier mouvement: " + CMD_REDO);
        System.out.println("       Abandonner la partie: " + CMD_RESTART);
        System.out.println("       Recommencer le niveau: " + CMD_GIVE_UP);
        System.out.println("       Afficher les commandes disponibles: " + CMD_HELP);
        System.out.println();
    }

    /**
     * Display the maze
     *
     * @param game current game
     */
    public void displayMaze(Game game) {
        int nbMove = game.getNbMove();
        int nbRow = game.getHeight();
        int nbCol = game.getWidth();
        for (int row = 0; row < nbRow; row++) {
            for (int col = 0; col < nbCol; col++) {
                Position pos = new Position(row, col);
                String symbol = game.getSquareAt(pos).toString();
//                System.out.print(maze[row][col].toString());
                System.out.print(symbol);
            }
            System.out.println();
        }
        System.out.println("Nombre de mouvement effectués: " + nbMove);
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
     *
     * @param str the string to convert to int
     * @param errorMsg the error message to throw if the string is unconvertible
     * to int
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
