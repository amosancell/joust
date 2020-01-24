public class Main {

    public static void main(String[] args) {
        /*   play multiplayer
        Game multiGame = new MultiPlayerGame(new Board(4,4),2);
        Player winner = multiGame.play();
        System.out.println("Congratulations, player " + winner.getSymbol() + " wins!");
        */
        /*   play versus a computer
        Game compGame = new ComputerGame(new Board(5,5),Algorithm::randomMove);
        Player winner = compGame.play();
        System.out.println(winner.getSymbol() + " is the winner!");*/
        /*   watch two computers play each other
        ComputerComputerGame ccGame = new ComputerComputerGame(new Board(5,5),Algorithm::randomMove,Algorithm::randomMove);
        Player winner = ccGame.play();
        System.out.println(winner.getSymbol() + " is the winner!");*/

        for(int i=3; i < 10; i++) {
            System.out.println("to go first on " + i + " x " + i + " board: " + Algorithm.evaluateFirstMove(new Board(i,i),Algorithm::randomMove,1000));
            System.out.println("to go second on " + i + " x " + i + " board: " + Algorithm.evaluateSecondMove(new Board(i,i),Algorithm::randomMove,1000));
        }

    }
}