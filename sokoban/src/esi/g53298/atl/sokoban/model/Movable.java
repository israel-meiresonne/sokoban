package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public class Movable {
    private MovableType type;
    private final String SYMBOL;
    
    public Movable(MovableType type){
        this.type = type;
        SYMBOL = type.getSymbol();
    }
}
