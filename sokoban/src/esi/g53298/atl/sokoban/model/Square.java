package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public class Square {
    private SquareType type;
    private Movable movable;
    
    /**
     * Constructor
     * @param type an SquareType enumeration
     */
    public Square(SquareType type){
        this.type = type;
        movable = null;
    }
    
    /**
     * Allows to add a movable object in the squre if the square can reaceive one
     * @param movable 
     * @throws IllegalStateException if the square can't receive a movable 
     * object or if the square already holds one
     */
    public void addMovable(Movable movable){
        if(type == SquareType.Wall){
            throw new IllegalStateException("A wall can't receive a movable "
                    + "object");
        }
        if(this.movable != null){
            throw new IllegalStateException("There is already a movable object "
                    + "on this square");
        }
        this.movable = movable;
    }
}
