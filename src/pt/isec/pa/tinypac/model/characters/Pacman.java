package pt.isec.pa.tinypac.model.characters;

import pt.isec.pa.tinypac.model.data.IMazeElement;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;
import pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates.PowerUp;

public class Pacman {
    private int x, y;
    private Direction direction;
    private int score;
    private int multiplicator;
    private IMazeElement render;

    private Maze board;
    private GameManager gameManager; //can be altered!

    public Pacman(int x, int y, Maze board, GameManager gameManager){
        this.x = x;
        this.y = y;
        direction = Direction.STILL;
        score = 0;
        multiplicator = 1;
        render = new IMazeElement() {
            @Override
            public char getSymbol() {
                return 'A';
            }
        };

        this.board = board; //reference
        this.gameManager = gameManager;

        board.set(y,x,render);
    }

    public int getX(){ return x;}
    public int getY(){ return y;}
    public Direction getDirection(){ return direction;}
    public int getScore(){return score;}
    public void updateDirection(Direction newDirection){ direction = newDirection;}
    public IMazeElement getRender(){return render;}

    //move
    public void updatePacman(){
        if(!frontTileObstructed()) { //assuming that pacman can advance:
            switch (direction) {
                case UP -> {
                    if (x > 0) x--; //can go up in the maze
                    else x = board.getMaze().length - 1; //x==0: pacman comes out the other side (bellow)
                }
                case DOWN -> {
                    if (x < board.getMaze().length - 1) x++; //can down up in the maze
                    else x = 0; //x== min height : pacman comes out the other side (above)
                }
                case RIGHT -> {
                    if (y > 0) y--; //can go right in the maze
                    else y = board.getMaze()[x].length - 1; //y==0: pacman comes out the other side (left)
                }
                case LEFT -> {
                    if (y < board.getMaze()[x].length - 1) y++; //can down left in the maze
                    else y = 0; //y== min height : pacman comes out the other side (right)
                }
            }
        }

        //pacman is in the new tile
        if (currentTileFood()) eat();
    }

    private boolean frontTileObstructed(){
        int extraX = 0, extraY = 0;
        switch (direction){
            case UP -> extraX = -1;
            case DOWN -> extraX = 1;
            case RIGHT -> extraY = 1;
            case LEFT -> extraY = -1;
        }
        //[x+extraX ; y+extraY] => tile pacman is facing

        //specific cases: looping
        if(direction == Direction.DOWN && x+extraX > board.getMaze().length - 1) {
            if (board.get(y, 0).getSymbol() == 'x') ;
                return true;
        }

        if(direction == Direction.RIGHT && y+extraY > board.getMaze()[x].length - 1) {
            if (board.get(0,x).getSymbol() == 'x') ;
                return true;
        }

        if(direction == Direction.UP && x+extraX < 0) {
            if (board.get(y, (board.getMaze().length - 1)).getSymbol() == 'x') ;
                return true;
        }

        if(direction == Direction.LEFT && y+extraY < 0) {
            if (board.get((board.getMaze()[x].length - 1),x).getSymbol() == 'x') ;
            return true;
        }


        if(board.get((y+extraY),(x+extraX)).getSymbol() == 'x')
            return true;
        // return yes if the tile that pacman faces == wall : he cant go foward

        return false; //no obstruction
    }

    private boolean currentTileFood(){
        if(board.get(y,x).getSymbol() == 'o' ||
                board.get(y,x).getSymbol() == 'O' ||
                board.get(y,x).getSymbol() == 'f')
            return true;
        // return true if the tile that pacman is standing == any food

        return false; //no food :(
    }

    //eat?
    private void eat(){
        switch (board.get(y,x).getSymbol()){
            case 'o' -> {
                score++;

                //resets tile
                IMazeElement floor = new IMazeElement() {
                    @Override
                    public char getSymbol() {
                        return ' ';
                    }
                };
                board.set(y,x, floor);
            }
            case 'O' -> {
                score += 10;
                //trigger vulnerable ghosts!
                gameManager.updateState(new PowerUp(gameManager));

                //resets tile
                IMazeElement floor = new IMazeElement() {
                    @Override
                    public char getSymbol() {
                        return ' ';
                    }
                };
                board.set(y,x, floor);
            }
            case 'f' -> {
                score += 25 * multiplicator;
                multiplicator++;

                //resets tree
                IMazeElement tree = new IMazeElement() {
                    @Override
                    public char getSymbol() {
                        return 'F';
                    }
                };
                board.set(y,x, tree);
            }
        }
    }
}
