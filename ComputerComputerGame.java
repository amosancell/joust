public class ComputerComputerGame extends Game{

    private AI ai1;
    private AI ai2;

    public ComputerComputerGame(Board board, AIalg<Board,Player,String> alg1, AIalg<Board,Player,String> alg2) {
        this.board = board;
        Player[] inits = this.board.initPlayers(2);
        ai1 = new AI(inits[0].getRow(),inits[0].getCol());
        ai2 = new AI(inits[1].getRow(),inits[1].getCol());
        ai1.setAlg(alg1);
        ai2.setAlg(alg2);
        ai1.setSymbol("c1");
        ai2.setSymbol("c2");
    }

    public AI getAi1() {
        return ai1;
    }
    public AI getAi2() {
        return ai2;
    }

    public Player play() {
        while(true) {
            board.display();
            System.out.println("ai1 does: " + ai1.makeMove(board));
            if(!ai2.hasMove(board)) {
                return ai1;
            }
            board.display();
            System.out.println("ai2 does: " + ai2.makeMove(board));
            if(!ai1.hasMove(board)) {
                return ai2;
            }
        }
    }

    public Player playNoPrint() {
        while(true) {
            if(!ai1.hasMove(board)) {
                return ai2;
            }
            ai1.makeMove(board);
            if(!ai2.hasMove(board)) {
                return ai1;
            }
            ai2.makeMove(board);
        }
    }
}
