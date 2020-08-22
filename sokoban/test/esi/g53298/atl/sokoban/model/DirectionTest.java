/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.Direction.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author israelmeiresonne
 */
public class DirectionTest {

    private Direction[] directions;

    @BeforeEach
    public void setUp() {
        directions = new Direction[]{UP, DOWN, LEFT, RIGHT};
    }

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
    public void testStringToDir() {
        System.out.println("stringToDir");
        String strDir = "";
        Direction expResult = null;
        Direction result = Direction.stringToDir(strDir);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
