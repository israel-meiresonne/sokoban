package esi.g53298.atl.sokoban.view;

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
        System.out.println("Bienvenue sur Sokoban\n Choisis ton niveau");
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
        System.out.println("       Bouger vers le haut: move U");
        System.out.println("       Bouger vers le bas: move D");
        System.out.println("       Bouger vers le gauche: move L");
        System.out.println("       Bouger vers le droite: move R");
        System.out.println("       Recommencer le niveau: restart");
        System.out.println("       Afficher l'aide: help");
        System.out.println("       Abandonner la partie: give up");
        System.out.println();
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
}
