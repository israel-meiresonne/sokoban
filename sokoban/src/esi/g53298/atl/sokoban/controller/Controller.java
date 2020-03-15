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
        int level = view.askLevel();
        game = new Game(level);
        view.displayMaze(game.getMaze());
        view.displayHelp();
        boolean isEnd = false;
        String cmd;
        while (!isEnd) {
            cmd = this.view.askCommand().toLowerCase();
            isEnd = treatCmd(cmd);
        }
    }

    private boolean treatCmd(String cmd) {
        String[] cmdTab = cmd.split(" ");

        switch (cmdTab[0]) {
            case "move":
                treatMove(cmdTab[1]);
                view.displayMaze(game.getMaze());
                break;
            default:
                view.displayError("La commande '"+ cmdTab[0] +"' est incorrecte!");
        }
        return false;
    }

    private void treatMove(String dir) {
        switch (dir) {
            case "U":
                game.moveUp();
                break;
            case "D":
                game.moveDown();
                break;
            case "L":
                game.moveLeft();
                break;
            case "R":
                game.moveRight();
                break;
        }
    }

}
