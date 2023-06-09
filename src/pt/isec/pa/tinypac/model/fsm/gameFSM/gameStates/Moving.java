package pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates;

import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStateModel;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStates;

public class Moving extends GameStateModel {
    public Moving(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void enterState() {
        this.state = GameStates.MOVING;
        //pacman acorda aka começa a mexer-se
    }

    @Override
    public Maze enterState(int level) throws Exception {
        return null;
    }

    @Override
    public void updateState() {

    }

    @Override
    public void exitState() {

    }
}
