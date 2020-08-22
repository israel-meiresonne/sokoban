package esi.g53298.atl.sokoban.model;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Model's Facade
 *
 * @author israelmeiresonne
 */
public class Game implements Subject {

    private Maze maze;
    private int level;
    private Stack<Move> doneMoves;
    private Stack<Move> undoMoves;
    private int nbMove;
    private boolean resetUndo;
    private ArrayList<Observer> observers;

    /**
     * Constructor for Javafx application
     */
    public Game() {
        observers = new ArrayList<>();
    }

    /**
     * Constructor for terminal displaying
     *
     * @param level the level to start
     * @throws FileNotFoundException
     */
    public Game(int level) throws FileNotFoundException {
        this();
        setGame(level);
    }

    /**
     * Initialize a game with a level
     *
     * @param level
     * @throws FileNotFoundException
     */
    public void setGame(int level) throws FileNotFoundException {
        XsbReader reader = new XsbReader(level);
        Position playerPosition = reader.getPlayerPosition();
        ArrayList<Square> gaols = reader.getGaols();

        this.maze = new Maze(reader.getMaze(), playerPosition, gaols);
        this.level = level;
        doneMoves = new Stack<>();
        undoMoves = new Stack<>();
        nbMove = 0;
        resetUndo = true;
    }

    /**
     * Getter for game's level
     *
     * @return game's level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Getter for game's done moves
     *
     * @return game's done moves
     */
    public Stack<Move> getDoneMoves() {
        return (Stack<Move>) doneMoves.clone();
    }

    /**
     * Getter for game's undo moves
     *
     * @return game's undo moves
     */
    public Stack<Move> getUndoMoves() {
        return (Stack<Move>) undoMoves.clone();
    }

    /**
     * Getter for game's number of gaol
     *
     * @return number of gaol
     */
    public int getNumberGaol() {
        return maze.getNumberGaol();
    }

    /**
     * Getter for game's number of goal achieved
     *
     * @return number of goal achieved
     */
    public int getAchievedGaol() {
        return maze.getAchievedGaol();
    }

    /**
     * To get the square at the given position
     *
     * @param pos position where to get the square
     * @return the square at the given position
     */
    public Square getSquareAt(Position pos) {
        return maze.getSquare(pos);
    }

    /**
     * To get maze's height
     *
     * @return maze's height
     */
    public int getHeight() {
        return maze.getHeight();
    }

    /**
     * To get maze's width
     *
     * @return maze's width
     */
    public int getWidth() {
        return maze.getWidth();
    }

    /**
     * Getteer for number of move
     *
     * @return the number of valid move
     */
    public int getNbMove() {
        return nbMove;
    }

    /**
     * To get all level playable
     *
     * @return array of level playable
     * @throws java.io.FileNotFoundException
     */
    public List<String> getLevels() throws FileNotFoundException {
        return (new XsbReader()).getLevels();
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
        notifyObserver();
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
        notifyObserver();
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
            // the last turn
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
        notifyObserver();
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
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObserver() {
        observers.forEach((obs) -> {
            obs.update(this);
        });
    }

}
