package esi.g53298.atl.sokoban.model;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author israelmeiresonne
 */
public class Maze {

    private Square[][] maze;
    private Position playerPosition;

    /**
     * Constructor
     * @param level 
     */
    public Maze(int level) throws FileNotFoundException {
        maze = buildMaze(level);
    }

    /**
     * Fill the maze with all element (wall, box, player, and depot) indicated
     * by the xsb file and set the playerPosition attribut
     * @param level the level to play
     * @return the maze filled
     */
    private Square[][] buildMaze(int level) throws FileNotFoundException {
        String levelDir = System.getProperty("user.dir") + "/../level";
        String fileName = "level" + level + ".xsb";
        File levelFile = new File(levelDir + fileName);

        Scanner myReader = new Scanner(levelFile);
        int nbRow = 0;
        int maxCol = 0;
        
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            int nbCol = data.length();
            if(nbCol > maxCol){
                maxCol = nbCol;
            }
            nbRow++;
        }
        
        myReader.reset();
        Square[][] maze = new Square[nbRow][maxCol];
        int row = 0;
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            int nbCol = data.length();
            
            for(int col = 0; col < maxCol; col++){
                if(col < data.length()){
                    char elementChar = data.charAt(col);
                    String element =String.valueOf(elementChar);  
                    switch(element){
                        case " ":
                            maze[row][col] = new Square(SquareType.Empty);
                            break;
                        case "#":
                            maze[row][col] = new Square(SquareType.Wall);
                            break;
                        case "$":
                            maze[row][col] = new Square(SquareType.Empty);
                            Box box = new Box();
                            maze[row][col].addMovable(box);
                            break;
                        case ".":
                            maze[row][col] = new Square(SquareType.Goal);
                            break;
                        case "*":
                            maze[row][col] = new Square(SquareType.Goal);
                            box = new Box();
                            maze[row][col].addMovable(box);
                            break;
                        case "@":
                            maze[row][col] = new Square(SquareType.Empty);
                            Player player = new Player();
                            playerPosition = new Position(row, col);
                            maze[row][col].addMovable(player);
                            break;
                        case "+":
                            maze[row][col] = new Square(SquareType.Goal);
                            player = new Player();
                            maze[row][col].addMovable(player);
                            break;
                    }
                } else {
                    maze[row][col] = new Square(SquareType.Empty);
                }
            }
            row++;
        }
        
        return maze;
    }
}
