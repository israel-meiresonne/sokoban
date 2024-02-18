package esi.g53298.atl.sokoban.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File reader and maze builder
 *
 * @author israelmeiresonne
 */
public class XsbReader {

    private final String dir;
    private Position playerPosition;
    private final ArrayList<Square> gaols;
    private Square[][] maze;

    /**
     * Constructor
     *
     */
    public XsbReader() {
        dir = System.getProperty("user.dir") + "/src/main/resources/level";
        gaols = new ArrayList<>();
    }

    /**
     * Constructor
     *
     * @param level the level to play
     * @throws java.io.FileNotFoundException
     */
    public XsbReader(int level) throws FileNotFoundException {
        this();
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
        String fileName = "/level" + level + ".xsb";
        File levelFile = new File(dir + fileName);

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
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
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
     *
     * @return player's position
     */
    public Position getPlayerPosition() {
        return playerPosition;
    }

    /**
     * Getter for the maze's goals
     *
     * @return maze's goals
     */
    public ArrayList<Square> getGaols() {
        return gaols;
    }

    /**
     * Getter for the maze
     *
     * @return the maze
     */
    public Square[][] getMaze() {
        return maze;
    }

    /**
     * To get all level playable
     *
     * @return array of level playable
     */
    public List<String> getLevels() {
        List<String> levels = new ArrayList<>();

        File f = new File(dir);
        String[] files = f.list((fDir, name) -> {
            Pattern p = Pattern.compile("^level([0-9]+).xsb$");
            Matcher m = p.matcher(name);
            Boolean found = m.find();
            if (found) {
                levels.add(m.group(1));
            }
            return found;
        });
        Collections.sort(levels);
        return levels;
    }
}
