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

    public Square[][] getMaze() { //@srv supprimer cette méthode et remplacer par getHeight, getWidth et getSquareAt(pos)
        return maze.getMaze();
    }
    
    /**
     * 
     * @return the number of valid move
     */
    public int getNbMove(){
        return maze.getNbMove();
    }

    /**
     * Ollows to move the player to left by checking if the left square isn(t a
     * wall and if it free
     *
     * @throws IllegalStateException if the left Square is a wall
     */
    public void moveLeft() { //@srv: 1 seule méthode: move(Direction dir)
        maze.moveLeft();
    }

    /**
     * Ollows to move the player to right
     */
    public void moveRight() {
        maze.moveRight();
    }

    /**
     * Ollows to move the player to up
     */
    public void moveUp() {
        maze.moveUp();
    }

    /**
     * Ollows to move the player to left
     */
    public void moveDown() {
        maze.moveDown();
    }

    /**
     * Check if the level is successful by check if all box is in a gaol square
     *
     * @return true if the level successful else false
     */
    public boolean isWin() {
        if (maze != null) {
            return maze.isWin();
        }
        return false;
    }

    /**
     * Restar the level by rebuilding the maze
     *
     * @throws FileNotFoundException
     */
    public void restarLevel() throws FileNotFoundException {
        maze.restarLevel();
    }

    /**
     * Allow to give up the game
     */
    public void giveUp() {
        maze = null;
    }

//    /**
//     * Check if the player has given up
//     *
//     * @return true if the game is given up else false
//     */
//    public boolean isGiveUp() {
//        return maze == null;
//    }

    /**
     * Undo the last move witch worked
     */
    public void undoMove() {
        maze.undoMove();
    }

    /**
     * Redo the last undone move
     */
    public void redoMove() {
        maze.redoMove();
    }

}
