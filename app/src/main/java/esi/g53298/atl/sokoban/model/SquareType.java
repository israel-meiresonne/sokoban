package esi.g53298.atl.sokoban.model;

/**
 * Type of a square
 *
 * @author israelmeiresonne
 */
public enum SquareType {
    Goal("."), Empty(" "), Wall("#");
    private final String symbol;

    /**
     * Constructor
     *
     * @param symbol
     */
    private SquareType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

}
