package esi.g53298.atl.sokoban.view;

import esi.g53298.atl.sokoban.Main;
import esi.g53298.atl.sokoban.model.Direction;
import static esi.g53298.atl.sokoban.model.Direction.*;
import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.model.Observer;
import esi.g53298.atl.sokoban.model.Position;
import esi.g53298.atl.sokoban.model.SquareState;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Manage level layout
 *
 * @author israelmeiresonne
 */
public class LevelRoot extends VBox implements Observer {

    private GridPane gameGrid;
    private Button quit;
    private Button restart;
    public final String STAGE_TITLE = "Sokoban 2.0";
    private static final String CMD_REDO = "r";
    private static final String CMD_UNDO = "u";

    public LevelRoot(Game game) {
//        primaryStage.setTitle("Sokoban 2.0");

        // game layout
//        VBox root = new VBox();
        VBox lvlBox = new VBox(10);             // 1, in root
        VBox innerBox = new VBox(50);           // 2, in lvlBox
        HBox contentBox = new HBox(10);         // 3, in innerBox
        HBox btnBox = new HBox(10);             // 4, in innerBox
        VBox infoBox = new VBox(20);            // 5, in contentBox
        gameGrid = new GridPane();              // 6, in contentBox
        VBox moveBox = new VBox(50);            // 7, in contentBox
        VBox keyValueBox = new VBox(10);        // 8, in infoBox
        VBox doneMovesLabelBox = new VBox(5);   // 9, in moveBox
        VBox undoMovesLabelBox = new VBox(5);   // 10, in moveBox
        VBox doneMovesBox = new VBox(10);       // 12, in doneMovesLabelBox
        VBox undoMovesBox = new VBox(10);       // 11, in undoMovesLabelBox

        // add Menu
        addMenu(this);

        // --- Root Container
        this.getStyleClass().add("main-background");

        // --- Place lvlBox in root and hide it by default
        lvlBox.getStyleClass().add("lvlBox");
        this.getChildren().add(lvlBox);

        // --- Place innerBox in lvlBox
        lvlBox.getChildren().add(innerBox);

        // --- Place contentBox and btnBox in innerBox
        innerBox.getChildren().addAll(contentBox, btnBox);

        // --- Place infoBox, gameGrid and moveBox in contentBox
        contentBox.getChildren().addAll(infoBox, gameGrid, moveBox);

        // --- Place keyValueBox in infoBox
        infoBox.getChildren().add(keyValueBox);

        // --- Place moveBox with its children and grand-children
        moveBox.getChildren().addAll(doneMovesLabelBox, undoMovesLabelBox);
        doneMovesLabelBox.getChildren().add(doneMovesBox);
        undoMovesLabelBox.getChildren().add(undoMovesBox);

        // --- Title Message
        Label title = new Label("Sokoban 2.0");
        title.getStyleClass().add("title-font");
        lvlBox.getChildren().add(0, title);

        // --- add Level in infoBox
        Label level = new Label("Niveau " + game.getLevel());
        level.getStyleClass().add("main-font");
        infoBox.getChildren().add(0, level);

        // --- fill keyValueBox with game stats
        int nbGaol = game.getNumberGaol();
        Label goal = new Label("Nombre d'objectif: " + nbGaol);
        goal.getStyleClass().add("info-font");
        Label achieved = new Label("Nombre d'objectif atteint: " + game.getAchievedGaol() + "/" + nbGaol);
        achieved.getStyleClass().add("info-font");
        Label nbMove = new Label("Nombre de déplacements: " + game.getNbMove());
        nbMove.getStyleClass().add("info-font");
        keyValueBox.getChildren().addAll(goal, achieved, nbMove);

        // --- fill gameGrid with game's maze
        buildMaze(game);

        // --- Add label in doneMovesLabelBox
        Label lastMvLB = new Label("Dernier mouvements");
        lastMvLB.getStyleClass().add("main-font");
        doneMovesLabelBox.getChildren().add(0, lastMvLB);

        // --- Add done Moves in doneMovesBox
        Label lastMv = new Label("Player " + UP);
        lastMv.getStyleClass().add("info-font");
        lastMv.getStyleClass().add("button");
        doneMovesBox.getChildren().add(lastMv);

        // --- Add label in undoMovesLabelBox
        Label undoMvLB = new Label("Mouvement annulés");
        undoMvLB.getStyleClass().add("main-font");
        undoMovesLabelBox.getChildren().add(0, undoMvLB);

        // --- Add undo Moves in undoMovesBox
        Label undoMv = new Label("Player " + DOWN + "\nBox " + DOWN);
        undoMv.getStyleClass().add("info-font");
        undoMv.getStyleClass().add("button");
        undoMovesBox.getChildren().add(undoMv);

        // --- Add quit and restart buttons in btnBox
        quit = new Button("Quitter");
        quit.getStyleClass().add("main-font");
        quit.getStyleClass().add("button");
        restart = new Button("Recommencer");
        restart.getStyleClass().add("main-font");
        restart.getStyleClass().add("button");
        btnBox.getChildren().addAll(quit, restart);

        // --- Add behavor on restart button
        addRestartBehavor(game);

        // --- Add key board commad
        addKeyBoardCommand(game);
    }

    private void addMenu(Pane root) {
        // --- Menu File
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        root.getChildren().add(0, menuBar);

        MenuItem exit = new MenuItem("Exit");
        menuFile.getItems().add(exit);
        exit.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });
    }

    private void addRestartBehavor(Game game) {
        restart.setOnAction((e) -> {
            try {
                game.restarLevel();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LevelRoot.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void addKeyBoardCommand(Game game) {
        this.addEventHandler(KeyEvent.KEY_PRESSED, e -> {
            String strDir = e.getCode().toString().toLowerCase();
            System.out.println(strDir);
            try {
                Direction dir = Direction.stringToDir(strDir);
                game.move(dir);
            } catch (IllegalStateException error) {
//                System.out.println("invalid direction:" + strDir);
            }
            switch (strDir) {
                case CMD_UNDO:
                    game.undoMove();
//                    System.out.println("undo");
                    break;
                case CMD_REDO:
                    game.redoMove();
//                    System.out.println("redo");
                    break;
            }
        });
    }

    private void buildMaze(Game game) {
        int nbRow = game.getHeight();
        int nbCol = game.getWidth();
        for (int row = 0; row < nbRow; row++) {
            for (int col = 0; col < nbCol; col++) {
                Position pos = new Position(row, col);
                game.getSquareAt(pos);
                try {
                    Image img;
                    img = buildImg(game.getSquareAt(pos).getState());
                    ImageView imageView = new ImageView(img);
                    imageView.setFitHeight(30);
                    imageView.setFitWidth(30);
                    gameGrid.add(imageView, col, row);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private Image buildImg(SquareState state) throws FileNotFoundException {
        Image img = null;
        String fileName = null;
        String dir = System.getProperty("user.dir") + "/../game-image";
        switch (state) {
            case STATE_EMPTY_GAOL:
                fileName = "/empty_gaol.png";
                break;
            case STATE_BOX_ON_GAOL:
                fileName = "/box_on_gaol.png";
                break;
            case STATE_PLAYER_ON_GAOL:
                fileName = "/player_on_gaol.png";
                break;
            case STATE_EMPTY:
                fileName = "/empty.png";
                break;
            case STATE_BOX:
                fileName = "/box.png";
                break;
            case STATE_PLAYER:
                fileName = "/player.png";
                break;
            case STATE_WALL:
                fileName = "/wall.png";
                break;
        }
        FileInputStream inputstream = new FileInputStream(dir + fileName);
        img = new Image(inputstream);
        return img;
    }

    /**
     * Getter for the level quit button
     *
     * @return
     */
    public Button getQuit() {
        return quit;
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
