public class ComputerGame extends Game {
    private Player player;
    private AI ai;

    public ComputerGame(Board b, AIalg<Board,Player,String> alg) {
        //this.player = board.initPlayers(1)[0];
        //this.ai = ai;
        this.board = b;
        Player[] init = board.initPlayers(2);
        this.player = init[0];
        this.ai = new AI(init[1].getRow(),init[1].getCol());
        this.ai.setAlg(alg);
        this.ai.setSymbol("c");
    }

    public Player play() {
        String input = "";
        while(true) {
            board.display();
            System.out.println("Player 1 make your move");
            input = scanner.nextLine();
            player.makeMove(board,input);
            if(!ai.hasMove(board)) {
                return player;
            }
            System.out.println("The computer does: " + ai.makeMove(board));
            if(!player.hasMove(board)) {
                return ai;
            }
        }
    }
}
