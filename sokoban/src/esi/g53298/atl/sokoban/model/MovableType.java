package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public enum MovableType {
    BOX("$"), PLAYER("@");
    
    private String symbol;
    
    MovableType(String symbol){
        this.symbol = symbol;
    }
    
    /**
     * 
     * @return the movable symbol
     */
    public String getSymbol(){
        return symbol;
    }
}
