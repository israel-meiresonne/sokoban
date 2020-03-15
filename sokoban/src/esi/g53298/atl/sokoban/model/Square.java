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
     *
     * @param type an SquareType enumeration
     */
    public Square(SquareType type) {
        this.type = type;
        movable = null;
    }

    /**
     * 
     * @return the movable attribut
     */
    public Movable getMovable() {
        return movable;
    }
    
    /**
     * 
     * @return the type of the square
     */
    public SquareType getType(){
        return type;
    }

    /**
     * Allows to add a movable object in the squre if the square can reaceive
     * one
     *
     * @param movable
     * @throws IllegalStateException if the square can't receive a movable
     * object or if the square already holds one
     */
    public void put(Movable movable) {
        if (type == SquareType.Wall) {
            throw new IllegalStateException("A wall can't receive a movable "
                    + "object");
        }
        if (this.movable != null) {
            throw new IllegalStateException("There is already a movable object "
                    + "on this square");
        }
        this.movable = movable;
    }
    
    /**
     * Set the movable attribut to null
     */
    public void leaveSquare(){
        movable = null;
    }
    
    /**
     * Check if the square can receive a movable
     * @return true if the square isn't a wall or don't has a movable object 
     * already else false
     */
    public boolean isFree(){
        return !(type == SquareType.Wall || movable != null);
    }
    
    /**
     * Check if the movable is a Box
     * @return 
     */
    public boolean isBox(){
        return movable.geType() == MovableType.BOX;
    }

}
