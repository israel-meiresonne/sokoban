package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.MovableType.BOX;

/**
 *
 * @author israelmeiresonne
 */
public class Square {

    private SquareType type;
    private Movable movable;
    public static final String ANSI_RED = "\u001B[31m";
    private final String EMPTY_GAOL = ".";
    private final String BOX_ON_GAOL = "*";

    /**
     * Constructor
     *
     * @param type an SquareType enumeration
     */
    public Square(SquareType type) {
        this.type = type;
        movable = null;
    }

    /**
     *
     * @return the movable attribut
     */
    public Movable getMovable() {
        return movable;
    }

    /**
     *
     * @return the type of the square
     */
    public SquareType getType() {
        return type;
    }

    /**
     * Allows to add a movable object in the squre if the square can reaceive
     * one
     *
     * @param movable
     * @throws IllegalStateException if the square can't receive a movable
     * object or if the square already holds one
     */
    public void put(Movable movable) {
        if (type == SquareType.Wall) {
            throw new IllegalStateException("A wall can't receive a movable "
                    + "object");
        }
        if (this.movable != null) {
            throw new IllegalStateException("There is already a movable object "
                    + "on this square");
        }
        this.movable = movable;
    }

    /**
     * Set the movable attribut to null
     */
    public void leaveSquare() {
        movable = null;
    }

    /**
     * Check if the square can receive a movable
     *
     * @return true if the square isn't a wall or don't has a movable object
     * already else false
     */
    public boolean isFree() {
        return !(type == SquareType.Wall || movable != null);
    }

    /**
     * Check if the movable is a Box
     *
     * @return
     */
    public boolean isBox() {
        if (movable != null) {
            return movable.geType() == MovableType.BOX;
        }
        return false;
    }

    /**
     * @return the symbol of the square or its movable object
     */
    public String toString() { //@srv doit se trouver dans la vue.
        String symbol = "";
        switch (type) {
            case Goal:
//                if (movable == null) {
//                    symbol = ".";
//                } else {
//                    return (movable.geType() == BOX)
//                            ? (ANSI_RED + movable.toString()) : "+";
//                }
                if (movable == null) {
                    symbol = EMPTY_GAOL;
                } else {
                    symbol = (movable.geType() == BOX)
                            ? (ANSI_RED + BOX_ON_GAOL) : type.toString();
                }
                break;
            case Empty:
                symbol = (movable == null) ? type.toString() : movable.toString();
                break;
            case Wall:
                symbol = type.toString();
                break;
        }
        return symbol;
    }
}
