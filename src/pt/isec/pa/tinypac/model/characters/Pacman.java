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

    private Maze maze;
    private GameManager gameManager; //can be altered!

    public Pacman(int x, int y, Maze maze, GameManager gameManager){
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

        this.maze = maze; //reference
        this.gameManager = gameManager;

        maze.set(y,x,render);
    }

    public int getX(){ return x;}
    public int getY(){ return y;}
    public Direction getDirection(){ return direction;}
    public int getScore(){return score;}
    public void updateDirection(Direction newDirection){ direction = newDirection; updatePacman();}
    public IMazeElement getRender(){return render;}

    //move
    public void updatePacman(){
        if(frontTileObstructed()) return;

        //assuming that pacman can advance:
        int row=y, col=x;
        switch (direction) {
                case UP -> {
                    if (y > 0) row--; //can go up in the maze
                    else row = maze.getMaze().length - 1; //y==0: pacman comes out the other side (bellow)
                }
                case DOWN -> {
                    if (y < maze.getMaze().length - 1) row++; //can down up in the maze
                    else row = 0; //y== min height : pacman comes out the other side (above)
                }
                case RIGHT -> {
                    if (x < maze.getMaze()[x].length - 1) col++; //can down left in the maze
                    else col = 0; //x== min height : pacman comes out the other side (left)

                }
                case LEFT -> {
                    if (x > 0) col--; //can go right in the maze
                    else col = maze.getMaze()[x].length - 1; //x==0: pacman comes out the other side (right)
                }
                case STILL -> {
                    return;
                }
            }




        movePacman(row,col);

        //pacman is in the new tile
        if (currentTileFood()) eat();
        if(currentTilePortal()) teleport();
    }

    private void movePacman(int row, int col){

        char resetChar = switch (maze.get(row,col).getSymbol()){
            case 'W': yield 'W';
            case 'F':
            case 'S': yield 'F';
            default: yield ' ';
        };
        IMazeElement reset = new IMazeElement() {
            @Override
            public char getSymbol() {
                return resetChar;
            }
        };

        int resetY = y, resetX = x;


        x=col;
        y=row;
        maze.set(y,x,render);

    }

    private boolean frontTileObstructed(){
        int extraX = 0, extraY = 0;
        switch (direction){
            case UP -> extraY = -1;
            case DOWN -> extraY = 1;
            case RIGHT -> extraX = 1;
            case LEFT -> extraX = -1;
            case STILL -> {
                return true;
            }
        }
        //[x+extraX ; y+extraY] => tile pacman is facing

        //specific cases: looping
        if(direction == Direction.DOWN && y+extraY > maze.getMaze().length - 1) {
            if (maze.get(0,x).getSymbol() == 'x' || maze.get(0,x).getSymbol() == 'Y') ;
                return true;
        }

        if(direction == Direction.RIGHT && x+extraX > maze.getMaze()[x].length - 1) {
            if (maze.get(y,0).getSymbol() == 'x' || maze.get(y,0).getSymbol() == 'Y') ;
                return true;
        }

        if(direction == Direction.UP && y+extraY < 0) {
            if (maze.get((maze.getMaze().length - 1),0).getSymbol() == 'x' || maze.get((maze.getMaze().length - 1),0).getSymbol() == 'Y') ;
                return true;
        }

        if(direction == Direction.LEFT && x+extraX < 0) {
            if (maze.get(y,(maze.getMaze().length-1)).getSymbol() == 'x' || maze.get(y,(maze.getMaze().length-1)).getSymbol() == 'Y') ;
            return true;
        }


        if(maze.get((y+extraY),(x+extraX)).getSymbol() == 'x' || maze.get((y+extraY),(x+extraX)).getSymbol() == 'Y')
            return true;
        // return yes if the tile that pacman faces == wall : he cant go foward

        return false; //no obstruction
    }

    private boolean currentTileFood(){
        if(maze.get(y,x).getSymbol() == 'o' ||
                maze.get(y,x).getSymbol() == 'O' ||
                maze.get(y,x).getSymbol() == 'f')
            return true;
        // return true if the tile that pacman is standing == any food

        return false; //no food :(
    }

    private boolean currentTilePortal(){
        if(maze.get(y,x).getSymbol() == 'W')
            return true;
        // return true if the tile that pacman is standing == any food

        return false; //no food :(
    }

    //eat?
    private void eat(){
        switch (maze.get(y,x).getSymbol()){
            case 'o' -> {
                score++;
                System.out.println(score);

                //resets tile
                IMazeElement floor = new IMazeElement() {
                    @Override
                    public char getSymbol() {
                        return ' ';
                    }
                };
                maze.set(y,x, floor);
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
                maze.set(y,x, floor);
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
                maze.set(y,x, tree);
            }
        }
    }

    private void teleport(){
        for(int i=0; i<maze.getMaze().length; i++)
            for(int j=0; j<maze.getMaze()[i].length; j++)
                if(maze.get(i,j).getSymbol() == 'W' && (i!=y || j!=x))
                    movePacman(5,5);

    }
}
