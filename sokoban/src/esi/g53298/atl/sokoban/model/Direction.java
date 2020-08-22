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

    /**
     * To get the opposite direction of the current direction
     *
     * @return the opposite direction of the current instance
     */
    public Direction getOpposite() {
        Direction oppositeDir;
        switch (this.toString()) {
            case "UP":
                oppositeDir = DOWN;
                break;
            case "DOWN":
                oppositeDir = UP;
                break;
            case "LEFT":
                oppositeDir = RIGHT;
                break;
            case "RIGHT":
                oppositeDir = LEFT;
                break;
            default:
                throw new IllegalStateException("The direction don't match any "
                        + "direction");
        }
        return oppositeDir;
    }

    /**
     * To convert a string direction to a Direction
     *
     * @param strDir a string direction
     * @return direction to a Direction
     */
    public static Direction stringToDir(String strDir) {
        strDir = strDir.toUpperCase();
        Direction dir = null;
        switch (strDir) {
            case "UP":
                dir = UP;
                break;
            case "DOWN":
                dir = DOWN;
                break;
            case "LEFT":
                dir = LEFT;
                break;
            case "RIGHT":
                dir = RIGHT;
                break;
            default:
                throw new IllegalStateException("The direction don't match any "
                        + "direction");
        }
        return dir;
    }
}
