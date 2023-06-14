package pt.isec.pa.tinypac.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;

public class PacmanGUI extends Application {

    @Override
    public void start(Stage menu) {
        // Create a button
        Button start = new Button("Start Game");
        start.setOnAction(event -> {
            menu.setScene(GameGUI.getGame());
            menu.show();
        });

        // Create a layout pane and add the button to it
        StackPane root = new StackPane(start);

        // Create a scene with the layout pane
        Scene scene = new Scene(root, 500, 350);

        // Set the scene on the primary stage
        menu.setScene(scene);

        // Set the title of the stage
        menu.setTitle("Pacman - PA 2023");

        // Show the stage
        menu.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX application
        GameManager m = new GameManager();
        try { m.startGame(); }
        catch (Exception e) { throw new RuntimeException(e);}

        GameGUI gameGUI = new GameGUI(m.getMaze());
        launch(args);
    }
}
