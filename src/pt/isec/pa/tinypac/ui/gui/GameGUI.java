package pt.isec.pa.tinypac.ui.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import pt.isec.pa.tinypac.model.data.Maze;

public class GameGUI {
    private static Scene game;
    private GridPane gridPane;

    public GameGUI(Maze maze){
        gridPane = new GridPane();
        updateGridPane(maze);

        game = new Scene(gridPane,maze.getMaze()[0].length * 20,maze.getMaze().length * 20);

    }

    private Text toText(char symbol) {
        return new Text(String.valueOf(symbol));
    }

    private void updateGridPane(Maze maze) {
        gridPane.getChildren().clear();

        for (int i = 0; i < maze.getMaze().length; i++)
            for (int j = 0; j < maze.getMaze()[i].length; j++){
                ImageView imageView = new ImageView(getRender(maze.get(i,j).getSymbol()));
                imageView.setFitHeight(20);
                imageView.setFitWidth(20);
                gridPane.add(imageView, j, i);
            }


        gridPane.setAlignment(Pos.CENTER);
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
}
