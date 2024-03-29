package esi.g53298.atl.sokoban.model;

import java.util.ArrayList;

/**
 * Holds the maze and manage moves of movable
 *
 * @author israelmeiresonne
 */
public class Maze {

    private Square[][] maze;
    private Position playerPosition;
    private ArrayList<Square> gaols;

    /**
     * Constructor
     *
     * @param maze the maze
     * @param playerPosition player's begin position
     * @param gaols squre where is goals
     */
//    public Maze(int level) throws FileNotFoundException {
    public Maze(Square[][] maze, Position playerPosition, ArrayList<Square> gaols) {
        this.maze = maze;
        this.playerPosition = playerPosition;
        this.gaols = gaols;
    }

    /**
     * To get maze's height
     *
     * @return maze's height
     */
    public int getHeight() {
        return maze.length;
    }

    /**
     * To get maze's width
     *
     * @return maze's width
     */
    public int getWidth() {
        return maze[0].length;
    }

    /**
     * Getter for player's position
     *
     * @return Position player's position
     */
    public Position getPlayerPosition() {
        return (new Position(playerPosition.getRow(), playerPosition.getColumn()));
    }

    /**
     * To get maze's square at the given position
     *
     * @param position position of the square to get
     * @return square at the given position
     */
    public Square getSquare(Position position) {
        return maze[position.getRow()][position.getColumn()];
    }

    /**
     * Getter for game's number of gaol
     *
     * @return number of gaol
     */
    int getNumberGaol() {
        return gaols.size();
    }

    /**
     * Getter for game's number of goal achieved
     *
     * @return number of goal achieved
     */
    public int getAchievedGaol() {
        int achieved = 0;
        for (Square sq : gaols) {
            if (sq.isBox()) {
                achieved++;
            }
        }
        return achieved;
    }

    /**
     * Allows to move the player to a new position and update the playerPosition
     * attribut
     *
     * @param newPosition the position where to move the player
     */
    public void movePlayer(Position newPosition) {
        int newRow = newPosition.getRow();
        int newColumn = newPosition.getColumn();

        int holdRow = playerPosition.getRow();
        int holdColumn = playerPosition.getColumn();

        Movable player = maze[holdRow][holdColumn].getMovable();
        maze[newRow][newColumn].put(player);
        maze[holdRow][holdColumn].leaveSquare();
        playerPosition = newPosition;
    }

    /**
     * Move a Box to a new Position if this Position is free
     *
     * @param boxPosition Box's current Position in maze
     * @param newBoxPosition Position where to move the box
     * @return true if the Box was move else false
     */
    public boolean moveBox(Position boxPosition, Position newBoxPosition) {
        int boxRow = boxPosition.getRow();
        int boxColumn = boxPosition.getColumn();
        int newbBoxRow = newBoxPosition.getRow();
        int newbBoxColumn = newBoxPosition.getColumn();

        if (maze[newbBoxRow][newbBoxColumn].isFree()) {
            Movable box = maze[boxRow][boxColumn].getMovable();
            maze[newbBoxRow][newbBoxColumn].put(box);
            maze[boxRow][boxColumn].leaveSquare();
            return true;
        }
        return false;
    }

    /**
     * Check if the level is successful by check if all box is in a gaol square
     *
     * @return true if the level successful else false
     */
    public boolean isWin() {
        for (Square sq : gaols) {
            if (!sq.isBox()) {
                return false;
            }
        }
        return true;
    }
}
