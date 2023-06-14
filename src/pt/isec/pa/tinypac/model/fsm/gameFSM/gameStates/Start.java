package pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates;

import pt.isec.pa.tinypac.LevelLoader.TxtToMaze;
import pt.isec.pa.tinypac.model.characters.Pacman;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStateModel;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStates;

public class Start extends GameStateModel {
    Maze maze;
    Pacman pacman;
    public Start(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void enterState() {

    }

    @Override
    public void updateState() {

    }

    @Override
    public Maze enterState(int level) throws Exception {
        this.state = GameStates.START;
        //setup game
        TxtToMaze converter = new TxtToMaze("src\\pt\\isec\\pa\\tinypac\\levels\\Level0" +level+".txt");
        maze = converter.initMaze();

        return maze;
    }

    @Override
    public Pacman updateState(Pacman pacman) {
        for(int i=0; i< maze.getMaze().length; i++) {
            for (int j = 0; j < maze.getMaze()[i].length; j++)
                if (maze.get(i, j).getSymbol() == 'M')
                    pacman = new Pacman(j,i, maze, gameManager);
        }
        return pacman;
    }

    @Override
    public void exitState() {

    }
}