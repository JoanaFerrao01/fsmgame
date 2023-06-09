import pt.isec.pa.tinypac.model.fsm.gameFSM.*;

public class Main {
    public static void main(String[] args) throws Exception {
        GameManager m = new GameManager();
        try { m.startGame(); }
        catch (Exception e) { throw new RuntimeException(e);}
    }
}