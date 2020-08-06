package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public enum MovableType {
    BOX("$"), PLAYER("@");
    
    private final String symbol;
    
    private MovableType(String symbol){
        this.symbol = symbol;
    }
//    
//    /**
//     * 
//     * @return the movable symbol
//     */
//    public String getSymbol(){
//        return symbol;
//    }

    @Override
    public String toString() {
        return symbol;
    }   
}
