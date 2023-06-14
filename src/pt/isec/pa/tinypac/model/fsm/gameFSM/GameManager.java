package pt.isec.pa.tinypac.model.fsm.gameFSM;
import javafx.scene.input.KeyCode;
import pt.isec.pa.tinypac.model.characters.Direction;
import pt.isec.pa.tinypac.model.characters.Pacman;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates.*;

import static java.lang.Thread.sleep;


public class GameManager {
    private GameStateModel currentState;
    private Maze maze;
    int level;

    protected Pacman pacman;

    public void startGame() throws Exception {
        this.level = 1;
        currentState = new Start(this); //começa o jogo c/ o estado start
        this.maze = currentState.enterState(level); //faz o codigo inicial do estado start
        this.pacman = currentState.updateState(this.pacman);
    }

    public void startLoop(KeyCode code) throws InterruptedException {
        currentState = new Awaken(this);
        sleep(5);
        currentState = new Moving(this);
        currentState.updateState(); //?
    }

    public void handleInput(KeyCode code){
        System.out.println("-" + code);
        switch (code){
            case LEFT -> pacman.updateDirection(Direction.LEFT);
            case RIGHT -> pacman.updateDirection(Direction.RIGHT);
            case UP -> pacman.updateDirection(Direction.UP);
            case DOWN -> pacman.updateDirection(Direction.DOWN);
        }
    }
    private void updateGame(){
        currentState.updateState();
        System.out.println(currentState.getState().toString()); //to erase
    }

    public void updateState(GameStateModel newState){
        currentState.exitState();
        currentState = newState;
        currentState.enterState();
    }

    private void printMaze(){
        for(int i=0; i< maze.getMaze().length; i++) {
            for (int j = 0; j < maze.getMaze()[i].length; j++)
                System.out.print(maze.get(i, j).getSymbol());
            System.out.println();
        }
    }
    public Maze getMaze(){return maze;}
    public int getScore(){return pacman.getScore();}

}
