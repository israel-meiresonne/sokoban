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
     *
     * @param level
     * @throws FileNotFoundException
     */
    public Game(int level) throws FileNotFoundException {
        this.maze = new Maze(level);
    }
    
    public Square[][] getMaze(){
        return maze.getMaze();
    }

    /**
     * Check if the level is successful by check if all box is in a gaol square
     *
     * @return true if the level successful else false
     */
    public boolean isWin() {
        return maze.isWin();
    }
    
    /**
     * Restar the level by rebuilding the maze
     * @throws FileNotFoundException 
     */
    public void restarLevel() throws FileNotFoundException{
        maze.restarLevel();
    }
    
    /**
     * Allow to give up the game
     */
    public void giveUp(){
        maze = null;
    }

}
