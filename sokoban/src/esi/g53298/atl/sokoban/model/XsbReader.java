package esi.g53298.atl.sokoban.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author israelmeiresonne
 */
public class XsbReader {

    private Position playerPosition;
    private final ArrayList<Square> gaols;
    private Square[][] maze;

    /**
     * Constructor
     *
     * @param level the level to play
     */
    public XsbReader(int level) throws FileNotFoundException {
        gaols = new ArrayList<>();
        maze = buildMaze(level);
    }

    /**
     * Fill the maze with all element (wall, box, player, and depot) indicated
     * by the xsb file and set the playerPosition attribut
     *
     * @param level the level to play
     * @return the maze filled
     */
    private Square[][] buildMaze(int level) throws FileNotFoundException {
        String levelDir = System.getProperty("user.dir") + "/../level";
        String fileName = "/level" + level + ".xsb";
        File levelFile = new File(levelDir + fileName);

        Scanner myReader = new Scanner(levelFile);
        int nbRow = 0;
        int maxCol = 0;

        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            int nbCol = data.length();
            if (nbCol > maxCol) {
                maxCol = nbCol;
            }
            nbRow++;
        }
        myReader.close();
        myReader = new Scanner(levelFile);
        Square[][] maze = new Square[nbRow][maxCol];
        int row = 0;
        while (myReader.hasNextLine()) { //@srv la lecture/décodage du fichier se fait dans une classe dédiée: XsbReader.
            String data = myReader.nextLine();
//            int nbCol = data.length();

            for (int col = 0; col < maxCol; col++) {
                if (col < data.length()) {
                    char elementChar = data.charAt(col);
                    String element = String.valueOf(elementChar);
                    switch (element) {
                        case " ":
                            maze[row][col] = new Square(SquareType.Empty);
                            break;
                        case "#":
                            maze[row][col] = new Square(SquareType.Wall);
                            break;
                        case "$":
                            maze[row][col] = new Square(SquareType.Empty);
                            Movable box = new Movable(MovableType.BOX);
                            maze[row][col].put(box);
                            break;
                        case ".":
                            maze[row][col] = new Square(SquareType.Goal);
                            gaols.add(maze[row][col]);
                            break;
                        case "*":
                            maze[row][col] = new Square(SquareType.Goal);
                            box = new Movable(MovableType.BOX);
                            maze[row][col].put(box);
                            break;
                        case "@":
                            maze[row][col] = new Square(SquareType.Empty);
                            Movable player = new Movable(MovableType.PLAYER);
                            playerPosition = new Position(row, col);
                            maze[row][col].put(player);
                            break;
                        case "+":
                            maze[row][col] = new Square(SquareType.Goal);
                            player = new Movable(MovableType.PLAYER);
                            maze[row][col].put(player);
                            break;
                    }
                } else {
                    maze[row][col] = new Square(SquareType.Empty);
                }
            }
            row++;
        }
        myReader.close();

        return maze;
    }

    /**
     * Getter for the player's start position
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Getter for the player's goals
    */
    public ArrayList<Square> getGaols() {
        return gaols;
    }

    /**
     * Getter for the game's maze
    */
    public Square[][] getMaze() {
        return maze;
    }
    
    
}
