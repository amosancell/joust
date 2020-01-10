import java.util.Scanner;
import java.util.Arrays;

public class Runner {

    public static void main(String[] args) {

        // set up scanner to take user input
        Scanner scanner = new Scanner(System.in);

        // have the user enter a gamemode i.e against computer or 2 player
        System.out.println("Two Player or Against Computer? Enter \'p\' for 2 Player and \'c\' for computer");
        String gameMode = scanner.nextLine();
        while(!gameMode.equals("p") && !gameMode.equals("c") && !gameMode.equals("cc")) {
            System.out.println("The only valid entries are \'p\' for 2 player and \'c\' for against the computer. Try again");
            gameMode = scanner.nextLine();
        }

        // set up the board for the game and start with gameOver as false
        Board board = new Board(5,5);
        boolean gameOver = false;

        // simulation of computer vs computer
        if(gameMode.equals("cc")) {
            Player[] players = board.initPlayers(2);
            while(!gameOver) {
                board.display();
                System.out.println("ai 1 moves");
                players[0].makeMove(board,players[0].randomMove(board));
                if(!players[1].hasMove(board)) {
                    gameOver = false;
                    System.out.println("ai 1 wins");
                    break;
                }
                System.out.println("ai 2 moves");
                players[1].makeMove(board,players[1].randomMove(board));
                if(!players[0].hasMove(board)) {
                    gameOver = false;
                    System.out.println("ai 2 wins");
                    break;
                }
            }
        }

        else if(gameMode.equals("p")) {
            Player[] players = board.initPlayers(2);
            System.out.println("Player 1 goes first!");
            int curPlayer = 0;
            String input = "";
            while(!gameOver) {
                board.display();
                System.out.println("Player " + (curPlayer +1) + " enter your move");
                input = scanner.nextLine();
                players[curPlayer].makeMove(board,input);
                curPlayer = (curPlayer + 1) % 2;
                if(!players[curPlayer].hasMove(board)) {
                    gameOver = true;
                }
            }
            board.display();
            System.out.println("Player " + (curPlayer+1) + " wins!!");

        }
        else {
            System.out.println("computer");
            Player player = new Player(0,0,"1");
            Player ai = new Player(board.getnRows()-1,board.getnCols()-1,"c");
            board.setCell(player.getRow(),player.getCol(),player.getSymbol());
            board.setCell(ai.getRow(),ai.getCol(),ai.getSymbol());

            String input = "";
            while(!gameOver) {
                board.display();
                System.out.println("Player 1 make your move");
                input = scanner.nextLine();
                player.makeMove(board,input);
                if(!ai.hasMove(board)) {
                    gameOver = true;
                    board.display();
                    System.out.println("Congratulations, you beat the computer!");
                    break;
                }
                String aiMove = ai.randomMove(board);
                System.out.println("The computer does: " + aiMove);
                ai.makeMove(board,aiMove);
                if(!player.hasMove(board)) {
                    gameOver = true;
                    board.display();
                    System.out.println("You lose, the computer beat you!");
                    break;
                }
            }
        }
    }

}
