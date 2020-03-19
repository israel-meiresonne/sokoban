package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public class Move {

    private Direction dir;
    /**
     * set at true if a box has been moved else false
     */
    private boolean boxMoved;

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
     *
     * @return the direction enum
     */
    public Direction getDirection() {
        return dir;
    }
    
    /**
     *
     * @return the direction enum
     */
    public boolean getBoxMoved() {
        return boxMoved;
    }
}
