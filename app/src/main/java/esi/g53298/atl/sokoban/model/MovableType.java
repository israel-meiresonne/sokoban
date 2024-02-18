package esi.g53298.atl.sokoban.model;

/**
 * Type of movable element
 *
 * @author israelmeiresonne
 */
public enum MovableType {
    BOX("$"), PLAYER("@");

    private final String symbol;

    /**
     * Constructor
     *
     * @param symbol the display of a movable
     */
    private MovableType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}
