package pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates;

import pt.isec.pa.tinypac.LevelLoader.TxtToMaze;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStateModel;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStates;

public class Start extends GameStateModel {
    public Start(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void enterState() {

    }

    @Override
    public Maze enterState(int level) throws Exception {
        this.state = GameStates.START;
        //setup game
        TxtToMaze converter = new TxtToMaze("src\\pt\\isec\\pa\\tinypac\\levels\\Level0" +level+".txt");
       return converter.initMaze();
    }

    @Override
    public void updateState() {

    }

    @Override
    public void exitState() {

    }
}
