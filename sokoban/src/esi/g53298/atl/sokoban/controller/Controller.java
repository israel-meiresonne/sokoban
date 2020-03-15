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
    
    public Controller(){
        view = new View();
    }
    
    public void startGame() throws FileNotFoundException{
        int level = view.askLevel();
        game = new Game(level);
        view.displayMaze(game.getMaze());
    }

    
    
    
    
    
}
