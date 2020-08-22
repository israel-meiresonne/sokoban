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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import esi.g53298.atl.sokoban.controller.Controller;
import esi.g53298.atl.sokoban.view.HomeRoot;
import esi.g53298.atl.sokoban.view.LevelRoot;

/**
 *
 * @author israelmeiresonne
 */
public class Main extends Application {

    private Scene mainScene;
    private Stage primaryStage;
    private HomeRoot homeRoot;
    private LevelRoot levelRoot;
    private Game game;

    public static void main(String args[]) throws FileNotFoundException {
//        Controller ctr = new Controller();
//        ctr.startGame();
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.primaryStage = stage;
        game = new Game();

        // get home scene
        homeRoot = new HomeRoot(game.getLevels());
        addLevelButtonBehavor(homeRoot.getButtons());
        primaryStage.setTitle(homeRoot.STAGE_TITLE);

        mainScene = new Scene(homeRoot, 1100, 700, Color.WHITE);
        String css = this.getClass().getResource("/esi/g53298/atl/sokoban/style.css").toExternalForm();
        mainScene.getStylesheets().add(css);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private void addLevelButtonBehavor(Collection<Button> buttons) {
        buttons.forEach((lvlBtn) -> {
            lvlBtn.setOnAction((ActionEvent e) -> {
                int btnId = Integer.parseInt(lvlBtn.getId());
                switchLevelRoot(btnId);
            });
        });
    }

    private void addQuitButtonBehavor(Button quit) {
        quit.setOnAction((ActionEvent e) -> {
            switchHomeRoot();
        });
    }

    private void switchLevelRoot(int level) {
        try {
            game.setGame(level);
            levelRoot = new LevelRoot(game);
            game.registerObserver(levelRoot);
            addQuitButtonBehavor(levelRoot.getQuit());
            mainScene.setRoot(levelRoot);
            primaryStage.setTitle(levelRoot.STAGE_TITLE);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void switchHomeRoot() {
        game.giveUp();
        mainScene.setRoot(homeRoot);
        primaryStage.setTitle(homeRoot.STAGE_TITLE);
    }
}
