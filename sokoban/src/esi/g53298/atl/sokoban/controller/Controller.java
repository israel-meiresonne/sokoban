package esi.g53298.atl.sokoban.controller;

import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.view.View;
import java.io.FileNotFoundException;

/**
 *
 * @author israelmeiresonne
 */
public class Controller {

    private Game game;
    private View view;

    public Controller() {
        view = new View();
    }

    public void startGame() throws FileNotFoundException {
        view.initialize();
//        int level = 1;
        int level = view.askLevel();
        game = new Game(level);
        view.displayGameCommand();
        view.displayMaze(game.getMaze(), game.getNbMove());
        boolean isEnd = false;
        String cmd;

        while (!isEnd) {
            boolean gameIsEnd = false;
            String cmdGame;
            while (!gameIsEnd && !game.isWin()) {
                cmdGame = view.askGameCommand().toLowerCase();
                gameIsEnd = treatGameCmd(cmdGame);
            }
            view.displayHelp();
            cmd = view.askCommand().toLowerCase();
            isEnd = treatCmd(cmd);

        }
    }

    /**
     * Treat command entered by the player
     *
     * @param cmd the command given by the player
     * @return false if the command was treated with success or true if the
     * command is to give up the game
     * @throws FileNotFoundException if the file of the level don't existe
     */
    private boolean treatGameCmd(String cmd) throws FileNotFoundException {
        String[] cmdTab = cmd.split(" ");

        switch (cmdTab[0]) {
            case "move":
                treatMove(cmdTab[1]);
                view.displayMaze(game.getMaze(), game.getNbMove());
                break;
            case "undo":
                game.undoMove();
                view.displayMaze(game.getMaze(), game.getNbMove());
                break;
            case "redo":
                game.redoMove();
                view.displayMaze(game.getMaze(), game.getNbMove());
                break;
            case "restart":
                game.restarLevel();
                view.displayGameCommand();
                view.displayMaze(game.getMaze(), game.getNbMove());
                break;
            case "help":
                view.displayGameCommand();
                break;
            case "giveup":
                view.displayError("Tu as abandonnée la partie!");
                game.giveUp();
                return true;
            default:
                view.displayError("La commande '" + cmdTab[0] + "' est incorrecte!");
        }
        return false;
    }

    /**
     * Treat command entered by the player
     *
     * @param cmd the command given by the player
     * @return false if the command was treated with success or true if the
     * command is to quit the application
     * @throws FileNotFoundException if the file of the level don't existe
     */
    private boolean treatCmd(String cmd) throws FileNotFoundException {
        String[] cmdTab = cmd.split(" ");

        switch (cmdTab[0]) {
            case "level":
                int level = view.askLevel();
                game = new Game(level);
                view.displayGameCommand();
                view.displayMaze(game.getMaze(), game.getNbMove());
                break;
            case "help":
                view.displayHelp();
                break;
            case "quit":
                return true;
            default:
                view.displayError("La commande '" + cmdTab[0] + "' est incorrecte!");
        }
        return false;
    }

    /**
     * Try to move the player to the direction passed in param
     *
     * @param dir the direction character
     */
    private void treatMove(String dir) {
        switch (dir) {
            case "u": //@srv utiliser des constantes.
                game.moveUp();
                break;
            case "d":
                game.moveDown();
                break;
            case "l":
                game.moveLeft();
                break;
            case "r":
                game.moveRight();
                break;
        }
    }

}
