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
        int level = 1;//view.askLevel();
        game = new Game(level);
        view.displayMaze(game.getMaze());
        view.displayHelp();
        boolean isEnd = false;
        String cmd;

        while (!isEnd) {
            cmd = "move u";//view.askCommand().toLowerCase();
            isEnd = treatCmd(cmd);
            if (game.isWin()) {
                view.displaySuccess();
                cmd = view.askCommand().toLowerCase();
                isEnd = treatCmd(cmd);
            }
            if (game.isGiveUp()) {
                view.displayError("Tu as abandonnée la partie!");
                cmd = this.view.askCommand().toLowerCase();
                isEnd = treatCmd(cmd);
            }
        }
    }

    private boolean treatCmd(String cmd) throws FileNotFoundException {
        String[] cmdTab = cmd.split(" ");

        switch (cmdTab[0]) {
            case "move":
                treatMove(cmdTab[1]);
                view.displayMaze(game.getMaze());
                break;
            case "giveup":
                game.giveUp();
                break;
            case "level":
                int level = view.askLevel();
                game = new Game(level);
                view.displayMaze(game.getMaze());
                view.displayHelp();
                break;
            case "restart":
                game.restarLevel();
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

    private void treatMove(String dir) {
        switch (dir) {
            case "u":
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
