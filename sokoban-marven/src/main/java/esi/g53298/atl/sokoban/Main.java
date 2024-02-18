package esi.g53298.atl.sokoban;

import esi.g53298.atl.sokoban.model.Game;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import esi.g53298.atl.sokoban.view.HomeRoot;
import esi.g53298.atl.sokoban.view.LevelRoot;

/**
 * Application Luncher
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

        mainScene = new Scene(homeRoot, 1150, 850, Color.WHITE);
        mainScene.getStylesheets().add("style/style.css");
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    /**
     * To add bheavor on select level buttons from homeRoot
     *
     * @param buttons homeRoot's level buttons
     */
    private void addLevelButtonBehavor(Collection<Button> buttons) {
        buttons.forEach((lvlBtn) -> {
            lvlBtn.setOnAction((ActionEvent e) -> {
                int btnId = Integer.parseInt(lvlBtn.getId());
                switchLevelRoot(btnId);
            });
        });
    }

    /**
     * To add behavor on quit button from LevelRoot to give up the level
     *
     * @param quit quit button from LevelRoot
     */
    private void addQuitButtonBehavor(Button quit) {
        quit.setOnAction((ActionEvent e) -> {
            switchHomeRoot();
        });
    }

    /**
     * To Switch scene root on levelRoot
     *
     * @param level the level to start
     */
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

    /**
     * To Switch scene root on homdeRoot
     */
    private void switchHomeRoot() {
        game.giveUp();
        mainScene.setRoot(homeRoot);
        primaryStage.setTitle(homeRoot.STAGE_TITLE);
    }
}
