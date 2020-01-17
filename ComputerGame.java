import java.util.function.*;

public class ComputerGame extends Game {
    private Player player;
    private AI ai;

    public ComputerGame(Function<Board,String> alg) {
        //this.player = board.initPlayers(1)[0];
        //this.ai = ai;
        Player[] init = board.initPlayers(2);
        this.player = init[0];
        this.ai = new AI(init[1].getRow(),init[1].getCol());
        this.ai.setAlg(alg);
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
