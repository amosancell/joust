import java.util.function.*;
public class AI extends Player {
    private AIalg<Board,Player,String> alg;

    public AI(int row, int col) {
        super(row,col,"c");
    }

    public void setAlg(AIalg<Board,Player,String> alg) {
        this.alg = alg;
    }

    public String makeMove(Board board) {
        String move = alg.apply(board,this);
        super.makeMove(board,move);
        return move;
    }
}
