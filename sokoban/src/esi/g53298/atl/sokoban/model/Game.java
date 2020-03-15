package esi.g53298.atl.sokoban.model;

import java.io.FileNotFoundException;

/**
 *
 * @author israelmeiresonne
 */
public class Game {
    private Maze maze;
    
    /**
     * Constructor
     * @param level
     * @throws FileNotFoundException 
     */
    public Game(int level) throws FileNotFoundException{
        this.maze = new Maze(level);
    }
    
    
}
