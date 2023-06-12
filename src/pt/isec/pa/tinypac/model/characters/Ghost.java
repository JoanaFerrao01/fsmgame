package pt.isec.pa.tinypac.model.characters;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;

public class Ghost {
    protected IMazeElement name;
    protected int x, y;
    protected Direction direction;
    protected boolean isScared;

    protected Maze board;
    
    public Ghost(Maze board, char name, int x, int y){
        this.name = new IMazeElement() {
            @Override
            public char getSymbol() {
                return name;
            }
        };

        this.x = x;
        this.y = y;
        isScared = false;
        direction = Direction.STILL;

        this.board = board;
    }

    public int getX(){return x;}
    public int getY(){return y;}
    public boolean isScared(){return isScared;}
    public void changeScaredState(){isScared = !isScared;}

    public void updateGhost(){} //diferent movement for all ghosts

    protected boolean frontTileObstructed(){
        int extraX = 0, extraY = 0;
        switch (direction){
            case UP -> extraX = -1;
            case DOWN -> extraX = 1;
            case RIGHT -> extraY = 1;
            case LEFT -> extraY = -1;
        }
        //[x+extraX ; y+extraY] => tile pacman is facing

        if(board.get((y+extraY),(x+extraX)).getSymbol() == 'x')
            return true;
        // return yes if the tile that pacman faces == wall : he cant go foward

        return false; //no obstruction
    }
}
