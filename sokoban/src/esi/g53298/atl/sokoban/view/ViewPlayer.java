package esi.g53298.atl.sokoban.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author israelmeiresonne
 */
public class ViewPlayer extends Application {

    public ViewPlayer(String args[]) {
        launch(args);
    }

//    public static void main(String[] args) {
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bienvenue sur Sokoban 2.0");

        VBox root = new VBox();
        HBox mainBox = new HBox();          // in root
        VBox instrucBox = new VBox();       // in mainBox
        VBox levelBox = new VBox();       // in instrucBox

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

}
