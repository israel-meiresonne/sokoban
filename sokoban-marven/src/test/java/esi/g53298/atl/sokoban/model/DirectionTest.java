package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.Direction.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author israelmeiresonne
 */
public class DirectionTest {

    /**
     * Test of getOpposite method, of class Direction.
     */
    @Test
    public void testGetOpposite_UP() {
        System.out.println("getOpposite UP");
        Direction instance = UP;
        Direction expResult = DOWN;
        Direction result = instance.getOpposite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpposite method, of class Direction.
     */
    @Test
    public void testGetOpposite_DOWN() {
        System.out.println("getOpposite DOWN");
        Direction instance = DOWN;
        Direction expResult = UP;
        Direction result = instance.getOpposite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpposite method, of class Direction.
     */
    @Test
    public void testGetOpposite_LEFT() {
        System.out.println("getOpposite LEFT");
        Direction instance = LEFT;
        Direction expResult = RIGHT;
        Direction result = instance.getOpposite();
        assertEquals(expResult, result);
    }

    /**
     * Test of getOpposite method, of class Direction.
     */
    @Test
    public void testGetOpposite_RIGHT() {
        System.out.println("getOpposite RIGHT");
        Direction instance = RIGHT;
        Direction expResult = LEFT;
        Direction result = instance.getOpposite();
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDir method, of class Direction.
     */
    @Test
    public void testStringToDir_UP() {
        System.out.println("stringToDir");
        String strDir = "UP";
        Direction expResult = UP;
        Direction result = Direction.stringToDir(strDir);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDir method, of class Direction.
     */
    @Test
    public void testStringToDir_DOWN() {
        System.out.println("stringToDir");
        String strDir = "DOWN";
        Direction expResult = DOWN;
        Direction result = Direction.stringToDir(strDir);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDir method, of class Direction.
     */
    @Test
    public void testStringToDir_LEFT() {
        System.out.println("stringToDir");
        String strDir = "LEFT";
        Direction expResult = LEFT;
        Direction result = Direction.stringToDir(strDir);
        assertEquals(expResult, result);
    }

    /**
     * Test of stringToDir method, of class Direction.
     */
    @Test
    public void testStringToDir_RIGHT() {
        System.out.println("stringToDir");
        String strDir = "RIGHT";
        Direction expResult = RIGHT;
        Direction result = Direction.stringToDir(strDir);
        assertEquals(expResult, result);
    }
}
