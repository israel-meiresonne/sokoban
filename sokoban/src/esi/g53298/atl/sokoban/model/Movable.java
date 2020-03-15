package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public class Movable {
    private MovableType type;
    private final String SYMBOL;
    
    /**
     * Constructor
     * @param type 
     */
    public Movable(MovableType type){
        this.type = type;
        SYMBOL = type.getSymbol();
    }
    
    public MovableType geType(){
        return type;
    }
}
