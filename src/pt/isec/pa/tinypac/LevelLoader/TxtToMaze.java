package pt.isec.pa.tinypac.LevelLoader;

import pt.isec.pa.tinypac.model.data.Maze;

import java.nio.file.Files;
import java.nio.file.Paths;

public class TxtToMaze {
    private String data;

    public TxtToMaze(String fileName) throws Exception {
        data = new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private int countLines(){
        String[] lines = data.split("\n"); //divides data string by paragraphs -> saves it in a String array
        return  lines.length; //counts the array elements (number of times data String has been split, aka number of paragraphs)
    }

    private int countRows(){
        String[] lines = data.split("\n"); //divides data string by paragraphs -> saves it in a String array
        return  lines[0].length()-1; //returns the lenght of the 1st element, aka number of characters in the first line
    }

    public Maze initMaze(){
        String[] lines = data.split("\n");

        int height = countLines();
        int width = countRows();


        Maze newMaze = new Maze(height,width);

        charToMazeElement converter = new charToMazeElement();

        for(int i=0; i<height; i++){
            for(int j=0; j<width; j++){
                newMaze.set(i,j,converter.convertToMazeElement(lines[i].charAt(j)));
            }
        }

        return newMaze;

    }
}
