package esi.g53298.atl.sokoban.model;

import java.io.FileNotFoundException;

/**
 *
 * @author israelmeiresonne
 */
public class Game {
    private Maze maze;
    
    public Game(int level) throws FileNotFoundException{
        this.maze = new Maze(level);
    }
}
