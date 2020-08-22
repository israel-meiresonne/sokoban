package esi.g53298.atl.sokoban.controller;

import static esi.g53298.atl.sokoban.model.Direction.*;
import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.view.View;
import java.io.FileNotFoundException;

/**
 *
 * @author israelmeiresonne
 */
public class Controller {

    private Game game;
    private final View view;
    public static final String CMD_MOVE = "move";
    public static final String CMD_UNDO = "undo";
    public static final String CMD_REDO = "redo";
    public static final String CMD_RESTART = "restart";
    public static final String CMD_HELP = "help";
    public static final String CMD_GIVE_UP = "giveup";
    public static final String CMD_LEVEL = "level";
    public static final String CMD_QUIT = "quit";
    public static final String CMD_UP = "u";
    public static final String CMD_DOWN = "d";
    public static final String CMD_LEFT = "l";
    public static final String CMD_RIGHT = "r";

    public Controller() {
        this.view = new View();
    }

    public void startGame() throws FileNotFoundException {
        view.initialize();
//        int level = 1;
        int level = view.askLevel();
        game = new Game(level);
        view.displayGameCommand();
        view.displayMaze(game);
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
            case CMD_MOVE:
                treatMove(cmdTab[1]);
                view.displayMaze(game);
                break;
            case CMD_UNDO:
                game.undoMove();
                view.displayMaze(game);
                break;
            case CMD_REDO:
                game.redoMove();
                view.displayMaze(game);
                break;
            case CMD_RESTART:
                game.restarLevel();
                view.displayGameCommand();
                view.displayMaze(game);
                break;
            case CMD_HELP:
                view.displayGameCommand();
                break;
            case CMD_GIVE_UP:
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
            case CMD_LEVEL:
                int level = view.askLevel();
                game = new Game(level);
                view.displayGameCommand();
//                view.displayMaze(game.getMaze(), game.getNbMove());
                view.displayMaze(game);
                break;
            case CMD_HELP:
                view.displayHelp();
                break;
            case CMD_QUIT:
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
            case CMD_UP: //@srv utiliser des constantes.
                game.move(UP);
                break;
            case CMD_DOWN:
                game.move(DOWN);
                break;
            case CMD_LEFT:
                game.move(LEFT);
                break;
            case CMD_RIGHT:
                game.move(RIGHT);
                break;
        }
    }

}
