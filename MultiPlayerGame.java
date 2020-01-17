public class MultiPlayerGame extends Game {

    private Player[] players;
    public MultiPlayerGame(Board board, int numPlayers) {
        this.board = board;
        this.players = board.initPlayers(numPlayers);
    }

    public Player play() {
        int curPlayer = 0;
        int nextPlayer = 1;
        String input = "";
        //boolean gameOver = false;
        while(true) {
            board.display();
            System.out.println("Player "+ (curPlayer+1) + " make your move: ");
            input = scanner.nextLine();
            players[curPlayer].makeMove(board,input);
            nextPlayer = (curPlayer + 1) % players.length;
            if(!players[nextPlayer].hasMove(board)) {
                return players[curPlayer];
            }
            curPlayer = nextPlayer;
        }
        //return new Player(0,0,"This should never happen");
    }
}
