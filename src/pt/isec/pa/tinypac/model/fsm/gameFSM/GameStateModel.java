package pt.isec.pa.tinypac.model.fsm.gameFSM;

import pt.isec.pa.tinypac.model.characters.Pacman;
import pt.isec.pa.tinypac.model.data.Maze;

public abstract class GameStateModel {
    protected GameManager gameManager;
    protected GameStates state;

    public GameStateModel(GameManager gameManager){
        this.gameManager = gameManager;
    }

    public abstract void enterState();      //ações a fazer quando se entra neste estado
    public abstract void updateState();     //ações a fazer quando este estado se atualiza //ns se uso
    public abstract void exitState();       //ações a fazer quando se sai deste estado

    public GameStates getState() {return state;}

    //start
    public abstract Maze enterState(int level, Pacman pacman) throws Exception;
}

