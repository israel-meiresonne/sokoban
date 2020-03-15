package esi.g53298.atl.sokoban.model;

/**
 *
 * @author israelmeiresonne
 */
public enum Direction {
    UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

    private int row;
    private int column;

    /**
     * gives each enumeration its row and column value
     *
     * @param row is a row of an enumeration
     * @param column is a column of an enumeration
     */
    Direction(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * getter to access the row value of an enumeration
     *
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * getter to access the column value of an enumeration
     *
     * @return
     */
    public int getColumn() {
        return column;
    }
    
    public static void main(String args[]){
        ;
        System.out.println(UP.getRow());
    }

}
