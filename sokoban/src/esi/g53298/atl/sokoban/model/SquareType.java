package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public enum SquareType {
    Goal("+"), Empty(" "), Wall("#");
    private final String symbol;

    private SquareType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
    
    
}
