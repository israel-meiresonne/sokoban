package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.MovableType.*;
import static esi.g53298.atl.sokoban.model.SquareState.*;
import static esi.g53298.atl.sokoban.model.SquareType.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 *
 * @author israelmeiresonne
 */
public class SquareTest {

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_PLAYER_ON_EMPTY() {
        System.out.println("put PLAYER_ON_EMPTY");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Empty);
        instance.put(movable);
        assumeTrue(instance.getMovable().geType() == PLAYER);
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_BOX_ON_EMPTY() {
        System.out.println("put BOX_ON_EMPTY");
        Movable movable = new Movable(BOX);
        Square instance = new Square(Empty);
        instance.put(movable);
        assumeTrue(instance.getMovable().geType() == BOX);
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_MOVALE_ON_OCCUPED_EMPTY() {
        System.out.println("put MOVALE_ON_OCCUPED_EMPTY");
        Movable movable2 = new Movable(PLAYER);
        Movable movable = new Movable(BOX);
        Square instance = new Square(Empty);
        instance.put(movable);
        assertThrows(IllegalStateException.class, () -> {
            instance.put(movable2);
        });
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_PLAYER_ON_Goal() {
        System.out.println("put PLAYER_ON_Goal");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Goal);
        instance.put(movable);
        assumeTrue(instance.getMovable().geType() == PLAYER);
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_BOX_ON_Goal() {
        System.out.println("put BOX_ON_Goal");
        Movable movable = new Movable(BOX);
        Square instance = new Square(Goal);
        instance.put(movable);
        assumeTrue(instance.getMovable().geType() == BOX);
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_MOVALE_ON_OCCUPED_Goal() {
        System.out.println("put MOVALE_ON_OCCUPED_Goal");
        Movable movable2 = new Movable(PLAYER);
        Movable movable = new Movable(BOX);
        Square instance = new Square(Goal);
        instance.put(movable);
        assertThrows(IllegalStateException.class, () -> {
            instance.put(movable2);
        });
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_PLAYER_ON_WALL() {
        System.out.println("put PLAYER_ON_WALL");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Wall);
        assertThrows(IllegalStateException.class, () -> {
            instance.put(movable);
        });
    }

    /**
     * Test of put method, of class Square.
     */
    @Test
    public void testPut_BOX_ON_WALL() {
        System.out.println("put BOX_ON_WALL");
        Movable movable = new Movable(BOX);
        Square instance = new Square(Wall);
        assertThrows(IllegalStateException.class, () -> {
            instance.put(movable);
        });
    }

    /**
     * Test of leaveSquare method, of class Square.
     */
    @Test
    public void testLeaveSquare() {
        System.out.println("leaveSquare");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Empty);
        instance.put(movable);  // put a movable on square
        instance.leaveSquare(); // the movable leave the square
        assertNull(instance.getMovable());
    }

    /**
     * Test of isFree method, of class Square.
     */
    @Test
    public void testIsFree_WALL() {
        System.out.println("isFree WALL");
        Square instance = new Square(Wall);
        boolean expResult = false;
        boolean result = instance.isFree();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFree method, of class Square.
     */
    @Test
    public void testIsFree_FREE_EMPTY() {
        System.out.println("isFree FREE_EMPTY");
        Square instance = new Square(Empty);
        boolean expResult = true;
        boolean result = instance.isFree();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFree method, of class Square.
     */
    @Test
    public void testIsFree_OCCUPED_EMPTY() {
        System.out.println("isFree OCCUPED_EMPTY");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Empty);
        instance.put(movable);
        boolean expResult = false;
        boolean result = instance.isFree();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFree method, of class Square.
     */
    @Test
    public void testIsFree_FREE_GAOL() {
        System.out.println("isFree FREE_GAOL");
        Square instance = new Square(Goal);
        boolean expResult = true;
        boolean result = instance.isFree();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFree method, of class Square.
     */
    @Test
    public void testIsFree_OCCUPED_GAOL() {
        System.out.println("isFree OCCUPED_GAOL");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Goal);
        instance.put(movable);
        boolean expResult = false;
        boolean result = instance.isFree();
        assertEquals(expResult, result);
    }

    /**
     * Test of isBox method, of class Square.
     */
    @Test
    public void testIsBox_FALSE() {
        System.out.println("isBox FALSE");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Goal);
        instance.put(movable);
        boolean expResult = false;
        boolean result = instance.isBox();
        assertEquals(expResult, result);
    }

    /**
     * Test of isBox method, of class Square.
     */
    @Test
    public void testIsBox_TRUE() {
        System.out.println("isBox TRUE");
        Movable movable = new Movable(BOX);
        Square instance = new Square(Goal);
        instance.put(movable);
        boolean expResult = true;
        boolean result = instance.isBox();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_EMPTY_GAOL() {
        System.out.println("getState STATE_EMPTY_GAOL");
        Square instance = new Square(Goal);
        SquareState expResult = STATE_EMPTY_GAOL;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_BOX_ON_GAOL() {
        System.out.println("getState STATE_BOX_ON_GAOL");
        Movable movable = new Movable(BOX);
        Square instance = new Square(Goal);
        instance.put(movable);
        SquareState expResult = STATE_BOX_ON_GAOL;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_PLAYER_ON_GAOL() {
        System.out.println("getState STATE_PLAYER_ON_GAOL");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Goal);
        instance.put(movable);
        SquareState expResult = STATE_PLAYER_ON_GAOL;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_EMPTY() {
        System.out.println("getState STATE_EMPTY");
        Square instance = new Square(Empty);
        SquareState expResult = STATE_EMPTY;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_BOX() {
        System.out.println("getState STATE_BOX");
        Movable movable = new Movable(BOX);
        Square instance = new Square(Empty);
        instance.put(movable);
        SquareState expResult = STATE_BOX;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_PLAYER() {
        System.out.println("getState STATE_PLAYER");
        Movable movable = new Movable(PLAYER);
        Square instance = new Square(Empty);
        instance.put(movable);
        SquareState expResult = STATE_PLAYER;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }

    /**
     * Test of getState method, of class Square.
     */
    @Test
    public void testGetState_STATE_WALL() {
        System.out.println("getState STATE_WALL");
        Square instance = new Square(Wall);
        SquareState expResult = STATE_WALL;
        SquareState result = instance.getState();
        assertEquals(expResult, result);
    }
}
