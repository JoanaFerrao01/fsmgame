package pt.isec.pa.tinypac.model.fsm.gameFSM;
import pt.isec.pa.tinypac.model.characters.Pacman;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates.*;



public class GameManager {
    private GameStateModel currentState;
    private Maze maze;
    int level;

    protected Pacman pacman;

    public void startGame() throws Exception {
        this.level = 1;
        currentState = new Start(this); //começa o jogo c/ o estado start
        this.maze = currentState.enterState(level,pacman); //faz o codigo inicial do estado start

        //loop do jogo
        while(currentState.getState() != GameStates.LEVELCLEARED) { // || pacman has 0 lives?
            handleInput();
            updateGame();
            printMaze();

            updateState(new LevelCleared(this));
        }

        System.out.println("finished game"); // to erase
    }

    private void handleInput(){

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


}
