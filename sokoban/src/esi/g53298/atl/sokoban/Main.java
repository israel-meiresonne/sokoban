package esi.g53298.atl.sokoban;

import esi.g53298.atl.sokoban.controller.Controller;
import esi.g53298.atl.sokoban.model.Game;
import esi.g53298.atl.sokoban.model.Observer;
import esi.g53298.atl.sokoban.view.View;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author israelmeiresonne
 */
public class Main extends Application implements Observer {

    private Game game;
    private final String YELLOW = "#FFEA04";
    private final String PURPLE = "#7E03AC";
    private FadeTransition fadeIn;

    public static void main(String args[]) throws FileNotFoundException {
//        View view = new View(args);
//        Controller ctr = new Controller(view);
//        ctr.startGame();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        game = new Game();
        fadeIn = new FadeTransition(Duration.millis(450));

        primaryStage.setTitle("Bienvenue sur Sokoban 2.0");

        // home display
        VBox root = new VBox();
        BorderPane mainBox = new BorderPane();          // in root
        VBox instrucBox = new VBox(10);                 // in mainBox
        VBox levelBox = new VBox(15);                   // in instrucBox
        
        // game display


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

        // --- Root Container
        root.getChildren().add(mainBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.getStyleClass().add("main-background");

        // --- Welcom Message
        Label welcom = new Label("Bienvenue sur Sokoban 2.0");
        welcom.getStyleClass().add("main-text-color");
        welcom.getStyleClass().add("title-font");
        mainBox.setTop(welcom);
        BorderPane.setAlignment(welcom, Pos.TOP_CENTER);

        // --- Place Instruction VBox
        mainBox.setCenter(instrucBox);
        instrucBox.setAlignment(Pos.CENTER);

        // --- Add instruction label in instrucBox
        Label instruct = new Label("Veuillez choisir un niveau");
        instruct.getStyleClass().add("main-text-color");
        instruct.getStyleClass().add("main-font");
        instrucBox.getChildren().add(instruct);

        // --- Place levelBox in instrucBox
        instrucBox.getChildren().add(levelBox);
        levelBox.setAlignment(Pos.CENTER);

        // --- Place level buttons in levelBox and their behavor
        String[] lvls = {"1", "2", "3"};
        for (String lvl : lvls) {
            Button lvlBtn = new Button("Niveau " + lvl);
            lvlBtn.setId(lvl);
            lvlBtn.setOnAction((ActionEvent e) -> {
                int btnId = Integer.parseInt(lvlBtn.getId());
                try {
                    game.setGame(btnId);
                    mainBox.setVisible(false);
                    primaryStage.setTitle("Sokoban 2.0");
                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            lvlBtn.getStyleClass().add("button");
            levelBox.getStyleClass().add("main-font");
            levelBox.getChildren().add(lvlBtn);
        }

        Scene scene = new Scene(root, 1100, 490, Color.WHITE);
        String css = this.getClass().getResource("/esi/g53298/atl/sokoban/style.css").toExternalForm();
        scene.getStylesheets().add(css);
        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
