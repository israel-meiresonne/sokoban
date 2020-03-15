package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public class Game {
    private Maze maze;
    
    public Game(int level){
        this.maze = new Maze(level);
    }
}
