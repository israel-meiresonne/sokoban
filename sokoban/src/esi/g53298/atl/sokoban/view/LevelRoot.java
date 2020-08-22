package esi.g53298.atl.sokoban.view;

import esi.g53298.atl.sokoban.Main;
import esi.g53298.atl.sokoban.model.Direction;
import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.model.Move;
import esi.g53298.atl.sokoban.model.Observer;
import esi.g53298.atl.sokoban.model.Position;
import esi.g53298.atl.sokoban.model.SquareState;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.*;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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
    private VBox keyValueBox;
    private VBox doneMovesBox;
    private VBox undoMovesBox;

    private Label goalLB;
    private Label achievedLB;
    private Label nbMoveLB;
    private HashMap<SquareState, Image> files;

    private Button quit;
    private Button restart;

    public final String STAGE_TITLE = "Sokoban 2.0";
    private final String CMD_REDO = "r";
    private final String CMD_UNDO = "u";

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
        VBox moveBox = new VBox(20);            // 7, in contentBox
//        VBox keyValueBox = new VBox(10);        // 8, in infoBox
        keyValueBox = new VBox(10);        // 8, in infoBox
        VBox doneMovesLabelBox = new VBox(5);   // 9, in moveBox
        VBox undoMovesLabelBox = new VBox(5);   // 10, in moveBox
//    scrollPane.setFitToHeight(true);
        ScrollPane doneScrollPane = new ScrollPane();   // 12, in doneMovesLabelBox
        ScrollPane undoScrollPane = new ScrollPane();   // 11, in undoMovesLabelBox

        doneMovesBox = new VBox(10);       // 14, in doneScrollPane
        undoMovesBox = new VBox(10);       // 13, in undoScrollPane

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

        // --- Hide scrollPane's scroll bar
//        scrollPane.setHbarPolicy(ScrollBarPolicy.NEVER);
//        scrollPane.setVbarPolicy(ScrollBarPolicy.NEVER);
        // --- Place doneMovesLabelBox and undoMovesLabelBox in moveBox
        doneMovesLabelBox.setMaxHeight(200);
        doneMovesLabelBox.setMinHeight(200);
        undoMovesLabelBox.setMaxHeight(200);
        undoMovesLabelBox.setMinHeight(200);
        moveBox.getChildren().addAll(doneMovesLabelBox, undoMovesLabelBox);

        // --- Place doneScrollPane in doneMovesLabelBox
        doneScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER); // --- Hide scrollPane's scroll bar
        doneScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER); // --- Hide scrollPane's scroll bar
        doneScrollPane.setFitToHeight(true);
        doneMovesLabelBox.getChildren().add(doneScrollPane);

        // --- Place undoScrollPane in undoMovesLabelBox
        undoScrollPane.setHbarPolicy(ScrollBarPolicy.NEVER); // --- Hide scrollPane's scroll bar
        undoScrollPane.setVbarPolicy(ScrollBarPolicy.NEVER); // --- Hide scrollPane's scroll bar
        undoScrollPane.setFitToHeight(true);
        undoMovesLabelBox.getChildren().add(undoScrollPane);

        // --- Place doneMovesBox in doneScrollPane
        doneMovesBox.getStyleClass().add("main-background");
//        doneMovesBox.getStyleClass().add("border-invisible");
        doneMovesBox.getStyleClass().add("border-invisible");
        doneScrollPane.setContent(doneMovesBox);

        // --- Place undoMovesBox in undoScrollPane
        undoMovesBox.getStyleClass().add("main-background");
        undoScrollPane.setContent(undoMovesBox);

        // --- Title Message
        Label title = new Label("Sokoban 2.0");
        title.getStyleClass().add("title-font");
        lvlBox.getChildren().add(0, title);

        // --- add Level in infoBox
        Label levelLB = new Label("Niveau " + game.getLevel());
        levelLB.getStyleClass().add("main-font");
        infoBox.getChildren().add(0, levelLB);

        // --- fill keyValueBox with game stats
        int nbGaol = game.getNumberGaol();
        goalLB = new Label("Nombre d'objectif: " + nbGaol);
        goalLB.getStyleClass().add("info-font");
        achievedLB = new Label("Nombre d'objectif atteint: " + game.getAchievedGaol() + "/" + nbGaol);
        achievedLB.getStyleClass().add("info-font");
        nbMoveLB = new Label("Nombre de déplacements: " + game.getNbMove());
        nbMoveLB.getStyleClass().add("info-font");
        keyValueBox.getChildren().addAll(goalLB, achievedLB, nbMoveLB);

        // --- fill gameGrid with game's maze
        buildMaze(game);

        // --- Add label in doneMovesLabelBox
        Label lastMvLB = new Label("Dernier mouvements");
        lastMvLB.getStyleClass().add("main-font");
        doneMovesLabelBox.getChildren().add(0, lastMvLB);

        // --- Add done Moves in doneMovesBox
//        Label lastMv = new Label("Player " + UP);
//        lastMv.getStyleClass().add("info-font");
//        lastMv.getStyleClass().add("button");
//        doneMovesBox.getChildren().add(lastMv);
//        fillMovePane(doneMovesBox, game.getDoneMoves());
        // --- Add label in undoMovesLabelBox
        Label undoMvLB = new Label("Mouvement annulés");
        undoMvLB.getStyleClass().add("main-font");
        undoMovesLabelBox.getChildren().add(0, undoMvLB);

        // --- Add undo Moves in undoMovesBox
//        Label undoMv = new Label("Player " + DOWN + "\nBox " + DOWN);
//        undoMv.getStyleClass().add("info-font");
//        undoMv.getStyleClass().add("button");
//        undoMovesBox.getChildren().add(undoMv);
//        fillMovePane(undoMovesBox, game.getUndoMoves());
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
            try {
                Direction dir = Direction.stringToDir(strDir);
                game.move(dir);
//                System.out.println("move done: " + strDir);
            } catch (IllegalStateException ex) {
//                Logger.getLogger(LevelRoot.class.getName()).log(Level.SEVERE, null, ex);
//                System.out.println("invalid direction: " + strDir);
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

    /**
     * To fill a move pane with move
     *
     * @param moves
     */
    private void fillMovePane(Pane pane, Stack<Move> moves) {
//        pane.getChildren().removeAll(pane.getChildren());
        removeChildren(pane);
        moves.forEach(move -> {
            Direction dir = move.getDirection();
            String txt = "Player " + dir;
            txt += (move.getBoxMoved()) ? "\nBox " + dir : "";
            Label label = new Label(txt);
            label.getStyleClass().add("info-font");
            label.getStyleClass().add("button");
//            label.setPrefWidth(200);
            pane.getChildren().add(0, label);
        });
    }

    private void buildMaze(Game game) {
        removeChildren(gameGrid);
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
        if (files == null) {
            files = new HashMap<>();
        }
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
        if ((!fileName.isEmpty()) && files.containsKey(state)) {
            img = files.get(state);
        } else {
            FileInputStream inputstream = new FileInputStream(dir + fileName);
            img = new Image(inputstream);
            files.put(state, img);
        }
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

    /**
     * To remove all Children in a pane
     *
     * @param pane a pane to remove Children
     */
    private void removeChildren(Pane pane) {
        pane.getChildren().clear();
    }

    @Override
    public void update(Game game) {
        // --- Update stats label of game
        int nbGaol = game.getNumberGaol();
        goalLB.setText("Nombre d'objectif: " + nbGaol);
        achievedLB.setText("Nombre d'objectif atteint: " + game.getAchievedGaol() + "/" + nbGaol);
        nbMoveLB.setText("Nombre de déplacements: " + game.getNbMove());

        // --- Add done Moves in doneMovesBox
        fillMovePane(doneMovesBox, game.getDoneMoves());

        // --- Add undo Moves in undoMovesBox
        fillMovePane(undoMovesBox, game.getUndoMoves());

        // --- fill gameGrid with game's maze
//        gameGrid.getChildren().removeAll(gameGrid.getChildren());
        buildMaze(game);

        if (game.isWin()) {
            Alert alert = new Alert(CONFIRMATION, "Félicitation! Vous avez terminé le niveau!\nVoules vous rejouer la partie?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    restart.fire();
                } else {
                    quit.fire();
                }
            });
        }
    }
}
