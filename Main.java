public class Main {

    public static void main(String[] args) {
        /*
        // code for plaing a multiplayer game:
        Game multiGame = new MultiPlayerGame(new Board(4,4),2);
        Player winner = multiGame.play();
        System.out.println("Congratulations, player " + winner.getSymbol() + " wins!");
        */
        Game compGame = new ComputerGame(Player::randomMove);
    }
}