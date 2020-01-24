import java.util.ArrayList;
import java.util.List;
import java.util.function.*;
public class Algorithm {

    /*private Function<Board, String> alg;

    public Algorithm()*/

    public static String randomMove(Board board, Player p) {
        String[] posMoves = {"ru","rd","ur","ul","lu","ld","dr","dl"};
        List<String> validMoves = new ArrayList<String>();
        for(String move : posMoves) {
            if(p.validMove(board,move)) {
                validMoves.add(move);
            }
        }
        return validMoves.get((int) (Math.random()*validMoves.size()));
    }


    /*public String decNextMove(Board board, Player p2) {
        String[] posMoves = {"ru","rd","ur","ul","lu","ld","dr","dl"};
        String bestMove = "";
        int movesPossible = 8;
        for(String move : posMoves) {
            if(validMove(board,move)) {
                this.makeMove(board,move);
                if(p2.countPossibleMoves(board) < movesPossible) {
                    bestMove = move;
                    movesPossible = p2.countPossibleMoves(board);
                }
                this.undoMove(board,move);
            }
        }
        if(bestMove.equals("")) {
            return this.randomMove(board);
        }
        else {
            return bestMove;
        }
    }*/

    public static double evaluateFirstMove(Board b, AIalg<Board,Player,String> alg, int numGames) {
        int wins = 0;
        for(int i=0; i < numGames; i++) {
            ComputerComputerGame ccGame = new ComputerComputerGame(b,Algorithm::randomMove,alg);
            Player winner = ccGame.playNoPrint();
            if(winner.equals(ccGame.getAi2())) {
                wins++;
            }
            b.clear();
        }
        return ((double) wins) / numGames;
    }
    public static double evaluateSecondMove(Board b, AIalg<Board,Player,String> alg, int numGames) {
        int wins = 0;
        for(int i=0; i < numGames; i++) {
            ComputerComputerGame ccGame = new ComputerComputerGame(b,alg,Algorithm::randomMove);
            Player winner = ccGame.playNoPrint();
            if(winner.equals(ccGame.getAi2())) {
                wins++;
            }
            b.clear();
        }
        return ((double) wins) / numGames;
    }
}