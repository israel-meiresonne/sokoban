package esi.g53298.atl.sokoban.model;

/**
 * Represente element that can be moved throught square
 *
 * @author israelmeiresonne
 */
public class Movable {

    private final MovableType type;
    private final String SYMBOL;

    /**
     * Constructor
     *
     * @param type
     */
    public Movable(MovableType type) {
        this.type = type;
        SYMBOL = type.toString();
    }

    /**
     * Getter for movable's type
     *
     * @return the type of the movable object
     */
    public MovableType geType() {
        return type;
    }

    @Override
    public String toString() {
        return SYMBOL;
    }

}
