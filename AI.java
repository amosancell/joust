import java.util.function.*;
public class AI extends Player {
    private Function<Board,String> alg;

    public AI(int row, int col) {
        super(row,col,"c");
    }

    public void setAlg(Function<Board,String> alg) {
        this.alg = alg;
    }

    public String makeMove(Board board) {
        String move = alg.apply(board);
        super.makeMove(board,move);
        return move;
    }
}
