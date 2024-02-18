package esi.g53298.atl.sokoban.model;

/**
 * Represente a move done
 *
 * @author israelmeiresonne
 */
public class Move {

    private final Direction dir;
    /**
     * set at true if a box has been moved else false
     */
    private final boolean boxMoved;

    /**
     * Constructor
     *
     * @param dir the direction of the move
     * @param boxMoved indicate if a box has been moved
     */
    public Move(Direction dir, boolean boxMoved) {
        this.dir = dir;
        this.boxMoved = boxMoved;
    }

    /**
     * Getter for move's direction
     *
     * @return the direction enum
     */
    public Direction getDirection() {
        return dir;
    }

    /**
     * Getter for boxMoved
     *
     * @return the direction enum
     */
    public boolean getBoxMoved() {
        return boxMoved;
    }
}
