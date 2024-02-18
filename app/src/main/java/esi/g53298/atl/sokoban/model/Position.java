package esi.g53298.atl.sokoban.model;

/**
 * Represente a Position
 * @author israelmeiresonne
 */
public class Position {
    private final int row;
    private final int column;
    
    /**
     * Constructor
     * @param row
     * @param column 
     */
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    /**
     * give the row of a Position's instance
     *
     * @return the row of a Position's instance
     */
    public int getRow() {
        return row;
    }
    
    /**
     * give the column of a Position's instance
     *
     * @return the column of a Position's instance
     */
    public int getColumn() {
        return column;
    }
    
    /**
     * To get Position instance on the give Direction
     * @param dir Direction where to get the position
     * @return 
     */
    public Position getPosOnDir(Direction dir){
        int newRow = row+dir.getRow();
        int newCol = column+dir.getColumn();
        return (new Position(newRow, newCol));
    }

}
