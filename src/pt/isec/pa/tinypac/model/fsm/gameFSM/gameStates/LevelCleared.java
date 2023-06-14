package pt.isec.pa.tinypac.model.fsm.gameFSM.gameStates;

import pt.isec.pa.tinypac.model.characters.Pacman;
import pt.isec.pa.tinypac.model.data.Maze;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameManager;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStateModel;
import pt.isec.pa.tinypac.model.fsm.gameFSM.GameStates;

public class LevelCleared extends GameStateModel {
    public LevelCleared(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void enterState() {
        this.state = GameStates.LEVELCLEARED;
        //venceu! proximo nivel?
    }

    @Override
    public void updateState() {

    }

    @Override
    public void exitState() {

    }

    @Override
    public Maze enterState(int level, Pacman pacman) throws Exception {
        return null;
    }
}
