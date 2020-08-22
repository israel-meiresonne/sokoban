package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public class Position {
    private int row;
    private int column;
    
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

}
