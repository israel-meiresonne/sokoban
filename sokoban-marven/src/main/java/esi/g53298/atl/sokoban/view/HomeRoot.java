package esi.g53298.atl.sokoban.view;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Manage application's home layout
 *
 * @author israelmeiresonne
 */
public class HomeRoot extends VBox {

    private final VBox levelBox;
    private Collection<Button> buttons; //= new ArrayList<>();
    public final String STAGE_TITLE = "Bienvenue sur Sokoban 2.0";
    public final List<String> levels;

    public HomeRoot(List<String> levels) {
        this.levels = levels;
        BorderPane homeBox = new BorderPane();  // in root
        VBox instrucBox = new VBox(10);         // in homeBox
        levelBox = new VBox(15);                // in instrucBox

        // add Menu
        addMenu(this);

        // --- Root Container
        this.getChildren().add(homeBox);
        this.setAlignment(Pos.TOP_CENTER);
        this.getStyleClass().add("main-background");

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

        // --- Add level button in levelBox
        addLevelButton();
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

    public void addLevelButton() {
//        String[] lvls = {"1", "2", "3"};
        buttons = new ArrayList<>();
        levels.forEach(lvl -> {
            Button lvlBtn = new Button("Niveau " + lvl);
            lvlBtn.setId(lvl);
            lvlBtn.getStyleClass().add("button");
            buttons.add(lvlBtn);
        });
        levelBox.getChildren().addAll(buttons);
    }

    public Collection<Button> getButtons() {
        return buttons;
    }
}
