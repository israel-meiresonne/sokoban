package esi.g53298.atl.sokoban.model;

import static esi.g53298.atl.sokoban.model.MovableType.BOX;
import static esi.g53298.atl.sokoban.model.SquareState.*;

/**
 *
 * @author israelmeiresonne
 */
public class Square {

    private SquareType type;
    private Movable movable;
    public static final String ANSI_RED = "\u001B[31m";
    private final String PLAYER_ON_GAOL = "+";
    private final String BOX_ON_GAOL = "*";
    
//    public final String STATE_EMPTY_GAOL = "STATE_EMPTY_GAOL";
//    public final String STATE_BOX_ON_GAOL = "STATE_BOX_ON_GAOL";
//    public final String STATE_PLAYER_ON_GAOL = "STATE_PLAYER_ON_GAOL";
//    public final String STATE_EMPTY = "STATE_EMPTY";
//    public final String STATE_BOX = "STATE_BOX";
//    public final String STATE_PLAYER = "STATE_PLAYER";
//    public final String STATE_WALL = "STATE_WALL";

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
//        return !(type == SquareType.Wall || movable != null);
        return (type != SquareType.Wall && movable == null);
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
     * To evaluate the state of the square
     * @return the state of the square
     */
    public SquareState getState(){
        SquareState state = null;
        switch (type) {
            case Goal:
                if (isFree()) {
                    state = STATE_EMPTY_GAOL;
                } else {
                    state = (movable.geType() == BOX)
                            ? (STATE_BOX_ON_GAOL) : STATE_PLAYER_ON_GAOL;
                }
                break;
            case Empty:
                if(isFree()){
                    state = STATE_EMPTY;
                } else {
                    switch(movable.geType()){
                        case BOX:
                            state = STATE_BOX;
                            break;
                        case PLAYER:
                            state = STATE_PLAYER;
                            break;
                    }
                }
                break;
            case Wall:
                state = STATE_WALL;
                break;
        }
        return state;
    }

    /**
     * @return the symbol of the square or its movable object
     */
    @Override
    public String toString() { //@srv doit se trouver dans la vue.
        String symbol = "";
        switch (type) {
            case Goal:
                if (movable == null) {
                    symbol = type.toString();
                } else {
                    symbol = (movable.geType() == BOX)
                            ? (ANSI_RED + BOX_ON_GAOL) : PLAYER_ON_GAOL;
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
