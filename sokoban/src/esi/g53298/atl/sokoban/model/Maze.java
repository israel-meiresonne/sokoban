package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.Direction.DOWN;
import static esi.g53298.atl.sokoban.model.Direction.LEFT;
import static esi.g53298.atl.sokoban.model.Direction.RIGHT;
import static esi.g53298.atl.sokoban.model.Direction.UP;
import esi.g53298.atl.sokoban.view.View;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author israelmeiresonne
 */
public class Maze { //@srv cette classe est tro longue, trop de responsabilités.

    private Square[][] maze;
    private Position playerPosition;
    private final ArrayList<Square> gaols;
    
    //@srv les attributs ci-dessous vont dans Gameet sont gérés par Game.
    private final int level;//@srv dans Game
    private Stack<Move> doneMoves; // @srv dans Game
    private Stack<Move> undoMoves;
    private int nbMove; //@srv dans Game

    /**
     * Constructor
     *
     * @param level
     * @throws java.io.FileNotFoundException if the file don't existe
     */
    public Maze(int level) throws FileNotFoundException {
        this.level = level;
        gaols = new ArrayList<>();
        doneMoves = new Stack<>();
        undoMoves = new Stack<>();
        nbMove = 0;
        maze = buildMaze(this.level);
    }

    /**
     * Fill the maze with all element (wall, box, player, and depot) indicated
     * by the xsb file and set the playerPosition attribut
     *
     * @param level the level to play
     * @return the maze filled
     */
    private Square[][] buildMaze(int level) throws FileNotFoundException {
        String levelDir = System.getProperty("user.dir") + "/../level";
        String fileName = "/level" + level + ".xsb";
        File levelFile = new File(levelDir + fileName);

        Scanner myReader = new Scanner(levelFile);
        int nbRow = 0;
        int maxCol = 0;

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            int nbCol = data.length();
            if (nbCol > maxCol) {
                maxCol = nbCol;
            }
            nbRow++;
        }
        myReader.close();
        myReader = new Scanner(levelFile);
        Square[][] maze = new Square[nbRow][maxCol];
        int row = 0;
        while (myReader.hasNextLine()) { //@srv la lecture/décodage du fichier se fait dans une classe dédiée: XsbReader.
            String data = myReader.nextLine();
            int nbCol = data.length();

            for (int col = 0; col < maxCol; col++) {
                if (col < data.length()) {
                    char elementChar = data.charAt(col);
                    String element = String.valueOf(elementChar);
                    switch (element) {
                        case " ":
                            maze[row][col] = new Square(SquareType.Empty);
                            break;
                        case "#":
                            maze[row][col] = new Square(SquareType.Wall);
                            break;
                        case "$":
                            maze[row][col] = new Square(SquareType.Empty);
                            Movable box = new Movable(MovableType.BOX);
                            maze[row][col].put(box);
                            break;
                        case ".":
                            maze[row][col] = new Square(SquareType.Goal);
                            gaols.add(maze[row][col]);
                            break;
                        case "*":
                            maze[row][col] = new Square(SquareType.Goal);
                            box = new Movable(MovableType.BOX);
                            maze[row][col].put(box);
                            break;
                        case "@":
                            maze[row][col] = new Square(SquareType.Empty);
                            Movable player = new Movable(MovableType.PLAYER);
                            playerPosition = new Position(row, col);
                            maze[row][col].put(player);
                            break;
                        case "+":
                            maze[row][col] = new Square(SquareType.Goal);
                            player = new Movable(MovableType.PLAYER);
                            maze[row][col].put(player);
                            break;
                    }
                } else {
                    maze[row][col] = new Square(SquareType.Empty);
                }
            }
            row++;
        }
        myReader.close();

        return maze;
    }

    /**
     * 
     * @return the maze
     */
    public Square[][] getMaze() {
        return maze;
    }

    /**
     *
     * @return the number of valid move
     */
    public int getNbMove() {
        return nbMove;
    }

    /**
     * Allows to move the player to a new position and update the playerPosition
     * attribut
     *
     * @param newPosition the position where to move the player
     */
    private void movePlayer(Position newPosition) {
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
    private boolean moveBox(Position boxPosition, Position newBoxPosition) {
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
     * Manage the player's moves in the four direction (UP, DOWN, LEFT, RIGHT)
     *
     * @param direction an enum witch indicate the direction to move
     */
    private void treatMove(Direction direction) {
        int newRow = playerPosition.getRow() + direction.getRow();
        int newColumn = playerPosition.getColumn() + direction.getColumn();

        SquareType type = maze[newRow][newColumn].getType();
        if (type != SquareType.Wall) {
            if (maze[newRow][newColumn].isFree()) {
                Position newPosition = new Position(newRow, newColumn);
                movePlayer(newPosition);
                doneMoves.push(new Move(direction, false));
                nbMove += 1;
            } else {
                if (maze[newRow][newColumn].isBox()) {
                    Position boxpos = new Position(newRow, newColumn);
                    Position newBoxpos = new Position(newRow + direction.getRow(),
                            newColumn + direction.getColumn());

                    if (moveBox(boxpos, newBoxpos)) {
                        Position newPosition = new Position(newRow, newColumn);
                        movePlayer(newPosition);
                        doneMoves.push(new Move(direction, true));
                        nbMove += 1;
                    }
                }
            }
        }
    }

    /**
     * Ollows to move the player to left by checking if the left square isn(t a
     * wall and if it free
     *
     * @throws IllegalStateException if the left Square is a wall
     */
    public void moveLeft() {
        treatMove(LEFT);
        undoMoves = new Stack<>();
    }

    /**
     * Ollows to move the player to right
     */
    public void moveRight() {
        treatMove(RIGHT);
        undoMoves = new Stack<>();
    }

    /**
     * Ollows to move the player to up
     */
    public void moveUp() {
        treatMove(UP);
        undoMoves = new Stack<>();
    }

    /**
     * Ollows to move the player to left
     */
    public void moveDown() {
        treatMove(DOWN);
        undoMoves = new Stack<>();
    }

    /**
     * Undo the last move witch worked
     */
    public void undoMove() {
        if (!doneMoves.empty()) {
            Position newBoxPos = playerPosition; // if a box has been moved at 
            //the last trun
            Move lastMove = doneMoves.pop();
            Direction lastMoveDir = lastMove.getDirection();
            Direction oppositeDir = lastMoveDir.getOpposite();
            int newRow = playerPosition.getRow() + oppositeDir.getRow();
            int newCol = playerPosition.getColumn() + oppositeDir.getColumn();
            Position newPosition = new Position(newRow, newCol);
            movePlayer(newPosition);
            nbMove -= 1;

            if (lastMove.getBoxMoved()) {
                int currentBoxRow = newBoxPos.getRow() + lastMoveDir.getRow();
                int currentBoxCol = newBoxPos.getColumn() + lastMoveDir.getColumn();
                Position boxPosition = new Position(currentBoxRow, currentBoxCol);
                moveBox(boxPosition, newBoxPos);
            }
            undoMoves.push(lastMove);
        }
    }

    /**
     * Redo the last undone move
     */
    public void redoMove() {
        if (!undoMoves.empty()) {
            Move undoneMove = undoMoves.pop();
            treatMove(undoneMove.getDirection());
        }
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

    /**
     * Restar the level by rebuilding the maze
     *
     * @throws FileNotFoundException
     */
    public void restarLevel() throws FileNotFoundException {
        maze = buildMaze(level);
    }

//    public static void main(String args[]) throws FileNotFoundException {
//        Maze maze = new Maze(1);
//        maze.moveUp();
//        View view = new View();
//        view.displayMaze(maze.getMaze());
//    }
}
