package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.Direction.*;
import static esi.g53298.atl.sokoban.model.MovableType.*;
import static esi.g53298.atl.sokoban.model.SquareType.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 *
 * @author israelmeiresonne
 */
public class MazeTest {

    private Maze maze;
    private Square[][] sqs;
    private ArrayList<Square> gaols;
    private Position boxPos;

    public MazeTest() {
    }

    public void setUpMovePleyer() {
        gaols = new ArrayList<>();
        sqs = new Square[][]{
            {new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall)},
            {new Square(Wall), new Square(Empty), new Square(Empty), new Square(Empty), new Square(Wall)},
            {new Square(Wall), new Square(Empty), new Square(Empty), new Square(Empty), new Square(Wall)},
            {new Square(Wall), new Square(Empty), new Square(Empty), new Square(Empty), new Square(Wall)},
            {new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall)},};
        int row = 2;
        int col = 2;
        Position playerPos = new Position(row, col);
        sqs[row][col].put(new Movable(PLAYER));
        gaols.add(new Square(Goal));
        maze = new Maze(sqs, playerPos, gaols);
    }

    public void setUpMoveBox() {
        gaols = new ArrayList<>();
        sqs = new Square[][]{
            {new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall)},
            {new Square(Wall), new Square(Empty), new Square(Empty), new Square(Empty), new Square(Wall)},
            {new Square(Wall), new Square(Empty), new Square(Empty), new Square(Empty), new Square(Wall)},
            {new Square(Wall), new Square(Empty), new Square(Empty), new Square(Empty), new Square(Wall)},
            {new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall), new Square(Wall)},};
        Position playerPos = new Position(1, 1);
        int row = 2;
        int col = 2;
        boxPos = new Position(row, col);
        sqs[row][col].put(new Movable(BOX));
        gaols.add(new Square(Goal));
        maze = new Maze(sqs, playerPos, gaols);
    }

    /**
     * Test of movePlayer method, of class Maze.
     */
    @Test
    public void testMovePlayer_UP() {
        System.out.println("movePlayer UP");
        setUpMovePleyer();
        Position holdPos = maze.getPlayerPosition();
        Position newPosition = maze.getPlayerPosition().getPosOnDir(UP);
        maze.movePlayer(newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == PLAYER);
        assumeTrue(maze.getSquare(holdPos).isFree());
    }

    /**
     * Test of movePlayer method, of class Maze.
     */
    @Test
    public void testMovePlayer_DOWN() {
        System.out.println("movePlayer DOWN");
        setUpMovePleyer();
        Position holdPos = maze.getPlayerPosition();
        Position newPosition = maze.getPlayerPosition().getPosOnDir(DOWN);
        maze.movePlayer(newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == PLAYER);
        assumeTrue(maze.getSquare(holdPos).isFree());
    }

    /**
     * Test of movePlayer method, of class Maze.
     */
    @Test
    public void testMovePlayer_LEFT() {
        System.out.println("movePlayer LEFT");
        setUpMovePleyer();
        Position holdPos = maze.getPlayerPosition();
        Position newPosition = maze.getPlayerPosition().getPosOnDir(LEFT);
        maze.movePlayer(newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == PLAYER);
        assumeTrue(maze.getSquare(holdPos).isFree());
    }

    /**
     * Test of movePlayer method, of class Maze.
     */
    @Test
    public void testMovePlayer_RIGHT() {
        System.out.println("movePlayer RIGHT");
        setUpMovePleyer();
        Position holdPos = maze.getPlayerPosition();
        Position newPosition = maze.getPlayerPosition().getPosOnDir(RIGHT);
        maze.movePlayer(newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == PLAYER);
        assumeTrue(maze.getSquare(holdPos).isFree());
    }

    /**
     * Test of moveBox method, of class Maze.
     */
    @Test
    public void testMoveBox_UP() {
        System.out.println("moveBox UP");
        setUpMoveBox();
        Position newPosition = boxPos.getPosOnDir(UP);
        maze.moveBox(boxPos, newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == BOX);
        assumeTrue(maze.getSquare(boxPos).isFree());
    }

    /**
     * Test of moveBox method, of class Maze.
     */
    @Test
    public void testMoveBox_DOWN() {
        System.out.println("moveBox DOWN");
        setUpMoveBox();
        Position newPosition = boxPos.getPosOnDir(DOWN);
        maze.moveBox(boxPos, newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == BOX);
        assumeTrue(maze.getSquare(boxPos).isFree());
    }

    /**
     * Test of moveBox method, of class Maze.
     */
    @Test
    public void testMoveBox_LEFT() {
        System.out.println("moveBox LEFT");
        setUpMoveBox();
        Position newPosition = boxPos.getPosOnDir(LEFT);
        maze.moveBox(boxPos, newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == BOX);
        assumeTrue(maze.getSquare(boxPos).isFree());
    }

    /**
     * Test of moveBox method, of class Maze.
     */
    @Test
    public void testMoveBox_RIGHT() {
        System.out.println("moveBox RIGHT");
        setUpMoveBox();
        Position newPosition = boxPos.getPosOnDir(RIGHT);
        maze.moveBox(boxPos, newPosition);
        assumeTrue(maze.getSquare(newPosition).getMovable().geType() == BOX);
        assumeTrue(maze.getSquare(boxPos).isFree());
    }

    /**
     * Test of moveBox method, of class Maze.
     */
    @Test
    public void testMoveBox_TO_WALL() {
        System.out.println("moveBox TO_WALL");
        setUpMoveBox();
        Position newPosition = boxPos.getPosOnDir(RIGHT);
        sqs[newPosition.getRow()][newPosition.getColumn()] = new Square(Wall);
        maze.moveBox(boxPos, newPosition);
        assumeTrue(maze.getSquare(boxPos).getMovable().geType() == BOX);
    }
}
