package esi.g53298.atl.sokoban.model;

import java.io.FileNotFoundException;
import java.util.Stack;

/**
 *
 * @author israelmeiresonne
 */
public class Game {

    private Maze maze;
    //@srv les attributs ci-dessous vont dans Gameet sont gérés par Game.
    private int level;//@srv dans Game
    private Stack<Move> doneMoves; // @srv dans Game
    private Stack<Move> undoMoves;
    private int nbMove; //@srv dans Game
    private boolean resetUndo;

    /**
     * Constructor
     *
     * @param level
     * @throws FileNotFoundException
     */
    public Game(int level) throws FileNotFoundException {
        setGame(level);
    }

    private void setGame(int level) throws FileNotFoundException {
        this.maze = new Maze(level);
        this.level = level;
        doneMoves = new Stack<>();
        undoMoves = new Stack<>();
        nbMove = 0;
        resetUndo = true;
    }

    public Square[][] getMaze() { //@srv supprimer cette méthode et remplacer par getHeight, getWidth et getSquareAt(pos)
        return maze.getMaze();
    }

    /**
     *
     * @return the number of valid move
     */
    public int getNbMove() {
        return nbMove;
    }

    /**
     * Manage the player's moves in the four direction (UP, DOWN, LEFT, RIGHT)
     *
     * @param direction an enum witch indicate the direction to move
     */
    public void move(Direction direction) {
        undoMoves = (resetUndo) ? new Stack<>() : undoMoves;
        Position playerPosition = maze.getPlayerPosition();
        int newRow = playerPosition.getRow() + direction.getRow();
        int newColumn = playerPosition.getColumn() + direction.getColumn();
        Position newPosition = new Position(newRow, newColumn);

        Square newSquare = maze.getSquare(newPosition);
        SquareType type = newSquare.getType();
        if (type != SquareType.Wall) {
            if (newSquare.isFree()) {
                maze.movePlayer(newPosition);
                doneMoves.push(new Move(direction, false));
                nbMove += 1;
            } else {
                if (newSquare.isBox()) {
                    Position boxpos = new Position(newRow, newColumn);
                    Position newBoxpos = new Position(newRow + direction.getRow(),
                            newColumn + direction.getColumn());

                    if (maze.moveBox(boxpos, newBoxpos)) {
                        maze.movePlayer(newPosition);
                        doneMoves.push(new Move(direction, true));
                        nbMove += 1;
                    }
                }
            }
        }
    }

    /**
     * Check if the level is successful by checking if all box is in a gaol
     * square
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
        setGame(level);
    }

    /**
     * Allow to give up the game
     */
    public void giveUp() {
        maze = null;
    }

    /**
     * Undo the last move witch worked
     */
    public void undoMove() {
        if (!doneMoves.empty()) {
            Position playerPosition = maze.getPlayerPosition();
            Position newBoxPos = playerPosition; // if a box has been moved at 
            //the last trun
            Move lastMove = doneMoves.pop();
            Direction lastMoveDir = lastMove.getDirection();
            Direction oppositeDir = lastMoveDir.getOpposite();
            int newRow = playerPosition.getRow() + oppositeDir.getRow();
            int newCol = playerPosition.getColumn() + oppositeDir.getColumn();
            Position newPosition = new Position(newRow, newCol);
            maze.movePlayer(newPosition);
            nbMove -= 1;

            if (lastMove.getBoxMoved()) {
                int currentBoxRow = newBoxPos.getRow() + lastMoveDir.getRow();
                int currentBoxCol = newBoxPos.getColumn() + lastMoveDir.getColumn();
                Position boxPosition = new Position(currentBoxRow, currentBoxCol);
                maze.moveBox(boxPosition, newBoxPos);
            }
            undoMoves.push(lastMove);
        }
    }

    /**
     * Redo the last undone move
     */
    public void redoMove() {
        resetUndo = false;
        if (!undoMoves.empty()) {
            Move undoneMove = undoMoves.pop();
            move(undoneMove.getDirection());
        }
        resetUndo = true;
    }
}
