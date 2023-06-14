package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;

public class GameGUI {
    private static Scene game;
    private GridPane gridPane;
    private GameManager gameManager;

    public GameGUI(Maze maze, GameManager gameManager){
        this.gameManager = gameManager; //ref

        Text text = new Text("SCORE: " + gameManager.getScore());
        text.setFont(Font.font("Arial", FontWeight.BOLD,  18));
        text.setFill(Color.WHITE);



        gridPane = new GridPane();
        updateGridPane(maze,text);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(text, gridPane);


        StackPane stackPane = new StackPane(vbox);
        stackPane.setBackground(Background.fill(Color.BLACK));

        game = new Scene(stackPane,maze.getMaze()[0].length * 20,maze.getMaze().length * 20+18);
        game.setOnKeyPressed(event -> handleKeyPress(event.getCode(),maze,text));

    }

    private Text toText(char symbol) {
        return new Text(String.valueOf(symbol));
    }

    private void updateGridPane(Maze maze,Text text) {
        gridPane.getChildren().clear();

        for (int i = 0; i < maze.getMaze().length; i++)
            for (int j = 0; j < maze.getMaze()[i].length; j++){
                ImageView imageView = new ImageView(getRender(maze.get(i,j).getSymbol()));
                imageView.setFitHeight(20);
                imageView.setFitWidth(20);
                gridPane.add(imageView, j, i);
            }

        gridPane.setAlignment(Pos.CENTER);
        text.setText("SCORE: " + gameManager.getScore());
    }

    public static Scene getGame(){
        return game;
    }

    private Image getRender(char ch){
        return switch (ch) {
            case 'x' ->new Image("pt/isec/pa/tinypac/ui/gui/images/wall.png");
            case 'W' -> new Image("pt/isec/pa/tinypac/ui/gui/images/portal.png");
            case 'o' -> new Image("pt/isec/pa/tinypac/ui/gui/images/coin.png");
            case 'F' -> new Image("pt/isec/pa/tinypac/ui/gui/images/bush.png");
            case 'M' -> new Image("pt/isec/pa/tinypac/ui/gui/images/spawn.png");
            case 'O' -> new Image("pt/isec/pa/tinypac/ui/gui/images/powerup.png");
            case 'Y' -> new Image("pt/isec/pa/tinypac/ui/gui/images/cave_entrance.png");
            case 'y' -> new Image("pt/isec/pa/tinypac/ui/gui/images/cave.png");
            case 'A' -> new Image("pt/isec/pa/tinypac/ui/gui/images/pacman.png");
            case 'S' -> new Image("pt/isec/pa/tinypac/ui/gui/images/berry.png");
            case 'B', 'P', 'I', 'C' -> new Image("pt/isec/pa/tinypac/ui/gui/images/inky.png");
            default -> new Image("pt/isec/pa/tinypac/ui/gui/images/tile.png");
        };
    }

    private void handleKeyPress(KeyCode code, Maze maze, Text text){
        //gameManager.startLoop(code);
        gameManager.handleInput(code);
        System.out.println(code);
        updateGridPane(maze,text);

    }
}
