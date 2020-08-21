package esi.g53298.atl.sokoban;

import static esi.g53298.atl.sokoban.model.Direction.*;
import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.model.Observer;
import esi.g53298.atl.sokoban.model.Position;
import esi.g53298.atl.sokoban.model.SquareState;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author israelmeiresonne
 */
public class Main extends Application implements Observer {

    private Scene mainScene;
    private VBox homeRoot;
    private VBox levelRoot;
    private Game game;
    
    public static void main(String args[]) throws FileNotFoundException {
//        Controller ctr = new Controller();
//        ctr.startGame();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bienvenue sur Sokoban 2.0");
        game = new Game();

        // get home scene
        homeRoot = getHomeRoot(primaryStage);

//        mainScene = new Scene(homeRoot);
        mainScene = new Scene(homeRoot, 1100, 700, Color.WHITE);
        String css = this.getClass().getResource("/esi/g53298/atl/sokoban/style.css").toExternalForm();
        mainScene.getStylesheets().add(css);
        primaryStage.setScene(mainScene);
//        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addMenu(VBox root) {
        // --- Menu File
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        menuBar.getMenus().add(menuFile);
        root.getChildren().add(menuBar);

        MenuItem exit = new MenuItem("Exit");
        menuFile.getItems().add(exit);
        exit.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });
    }

    private VBox getHomeRoot(Stage primaryStage) {
        // home display
        VBox root = new VBox();
        BorderPane homeBox = new BorderPane();  // in root
        VBox instrucBox = new VBox(10);         // in homeBox
        VBox levelBox = new VBox(15);           // in instrucBox

        // add Menu
        addMenu(root);

        // --- Root Container
        root.getChildren().add(homeBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("main-background");

        // --- Welcom Message
        Label welcom = new Label("Bienvenue sur Sokoban 2.0");
        // welcom.getStyleClass().add("main-text-color");
        welcom.getStyleClass().add("title-font");
        homeBox.setTop(welcom);
        BorderPane.setAlignment(welcom, Pos.TOP_CENTER);

        // --- Place Instruction VBox
        homeBox.setCenter(instrucBox);
        instrucBox.setAlignment(Pos.CENTER);

        // --- Add instruction label in instrucBox
        Label instruct = new Label("Veuillez choisir un niveau");
        // instruct.getStyleClass().add("main-text-color");
        instruct.getStyleClass().add("main-font");
        instrucBox.getChildren().add(instruct);

        // --- Place levelBox in instrucBox
        instrucBox.getChildren().add(levelBox);
        levelBox.getStyleClass().add("main-font");
        levelBox.setAlignment(Pos.CENTER);

        // --- Place level buttons in levelBox and their behavor
        String[] lvls = {"1", "2", "3"};
        Collection<Button> btns = new ArrayList<>();
        for (String lvl : lvls) {
            Button lvlBtn = new Button("Niveau " + lvl);
            lvlBtn.setId(lvl);
            lvlBtn.setOnAction((ActionEvent e) -> {
                int btnId = Integer.parseInt(lvlBtn.getId());
                switchLevelRoot(primaryStage, btnId);
            });
            lvlBtn.getStyleClass().add("button");
            btns.add(lvlBtn);
        }
        levelBox.getChildren().addAll(btns);

        return root;
    }

    private VBox getLevelRoot(Stage primaryStage) {
        primaryStage.setTitle("Sokoban 2.0");

        // game layout
        VBox root = new VBox();
        VBox lvlBox = new VBox(10);             // 1, in root
        VBox innerBox = new VBox(50);           // 2, in lvlBox
        HBox contentBox = new HBox(10);         // 3, in innerBox
        HBox btnBox = new HBox(10);             // 4, in innerBox
        VBox infoBox = new VBox(20);            // 5, in contentBox
        GridPane gameGrid = new GridPane();     // 6, in contentBox
        VBox moveBox = new VBox(50);            // 7, in contentBox
        VBox keyValueBox = new VBox(10);        // 8, in infoBox
        VBox doneMovesLabelBox = new VBox(5);  // 9, in moveBox
        VBox undoMovesLabelBox = new VBox(5);  // 10, in moveBox
        VBox doneMovesBox = new VBox(10);       // 12, in doneMovesLabelBox
        VBox undoMovesBox = new VBox(10);       // 11, in undoMovesLabelBox

        // add Menu
        addMenu(root);

        // --- Root Container
        root.getStyleClass().add("main-background");

        // --- Place lvlBox in root and hide it by default
        lvlBox.getStyleClass().add("lvlBox");
        root.getChildren().add(lvlBox);

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
        buildMaze(gameGrid);

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
        Button quit = new Button("Quitter");
        quit.getStyleClass().add("main-font");
        quit.getStyleClass().add("button");
        Button restart = new Button("Recommencer");
        restart.getStyleClass().add("main-font");
        restart.getStyleClass().add("button");
        btnBox.getChildren().addAll(quit, restart);

        return root;
    }

    private void switchLevelRoot(Stage primaryStage, int level) {
        try {
            game.setGame(level);
            // get game scene 
            levelRoot = getLevelRoot(primaryStage);
            mainScene.setRoot(levelRoot);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void buildMaze(GridPane gameGrid) {
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

}
