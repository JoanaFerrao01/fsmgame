package pt.isec.pa.tinypac.model.characters.IndividualGhosts;

import pt.isec.pa.tinypac.model.characters.Ghost;
import pt.isec.pa.tinypac.model.data.Maze;

public class Blinky extends Ghost {
    public Blinky(Maze board, char name, int x, int y) {
        super(board, name, x, y);
    }

    /*
    Este fantasma desloca-se sempre em frente e quando chega a uma parede ou cruzamento
    deve sortear uma das direções possíveis, voltando para trás apenas se não existirem outras
    direções disponíveis;
     */
    @Override
    public void updateGhost() {
        if(!frontTileObstructed()){ //can go forward
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
    }

    /*
    W – Zona Warp. Devem existir duas destas células por labirinto. Quando o pac-man entra numa
    destas células, deverá aparecer de imediato na outra. Este efeito só acontece para o pac-man:
    os fantasmas tratam a zona Warp como uma parede;
     */

    private boolean isLeftFree(){

        return true;
    }
}
