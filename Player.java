import java.util.Arrays;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Player {

    private int row;
    private int col;
    private int[] position; // never used
    private String symbol;

    public Player() {

    }

    public Player(int row, int col, String symbol) {
        this.row = row;
        this.col = col;
        this.position = new int[2];
        this.position[0] = row;
        this.position[1] = col;
        this.symbol = symbol;
    }

    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public int[] getPosition() {
        return position;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public void setPosition(int[] position) {
        this.position = position;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    /* a valid direction is:
        only 2 values, only made of r,l,u,d
        1st value can be any of r,l,u,d
        2nd value can be: if 1st was r or l  ---> u or d
                          if 1st was u or d  ---> r or l
    */
    public static boolean validDirection(String direction) {
        if(direction.length() != 2) {
            return false;
        }
        String firstChar = direction.substring(0,1);
        if(!(firstChar.equals("r") || firstChar.equals("l") || firstChar.equals("u") || firstChar.equals("d"))) {
            return false;
        }
        String secondChar = direction.substring(1,2);
        switch(firstChar) {
            case "r": case "l":
                return secondChar.equals("u") || secondChar.equals("d");
            case "u": case "d":
                return secondChar.equals("r") || secondChar.equals("l");
            default :
                return false;
        }
    }

    public static String reverseMove(String direction) {
        HashMap<String,String> opps = new HashMap<>();
        opps.put("r","l");
        opps.put("l","r");
        opps.put("d","u");
        opps.put("u","d");
        return opps.get(direction.substring(0,1)) + opps.get(direction.substring(1,2));
    }


    public static int[] convertDir(String direction) {
        int[] result = new int[2];
        switch(direction.substring(0,1)) {
            case "r":
                result[1] = 2;
                break;
            case "l":
                result[1] = -2;
                break;
            case "u":
                result[0] = -2;
                break;
            case "d":
                result[0] = 2;
                break;
        }
        switch(direction.substring(1,2)) {
            case "r":
                result[1] = 1;
                break;
            case "l":
                result[1] = -1;
                break;
            case "u":
                result[0] = -1;
                break;
            case "d":
                result[0] = 1;
                break;
        }
        return result;
    }

    public boolean validMove(Board board, String direction) {
        if(!validDirection(direction)) {
            return false;
        }
        int[] move = convertDir(direction);
        int[] newPos = {row + move[0], col + move[1]};
        if (newPos[0] < 0 || newPos[0] > board.getnRows() - 1 || newPos[1] < 0 || newPos[1] > board.getnCols() - 1) {
            //System.out.println("out of bounds");
            return false;
        } else if (board.getUsed()[newPos[0]][newPos[1]]) {
            //System.out.println("already used");
            return false;
        }
        return true;
    }

    // checks if a player can make a move
    public boolean hasMove(Board board) {
        String[] posMoves = {"ru","rd","ur","ul","lu","ld","dr","dl"};
        for(String move : posMoves) {
            if(validMove(board,move)) {
                return true;
            }
        }
        return false;
    }

    /* changes the board to match the move
        * board is the board that will be modified
        * direction is a string of the form "xy" where x is the direction to move 2 and y the direction to move 1
            * For example: "ru" means move 2 places right and 1 place up
            *              "dl" means move 2 places down and 1 place left
            * if direction is not a valid entry, the board will remain unchanged
    */
    public void makeMove(Board board, String direction) {
        int[] move = convertDir(direction);
        int[] newPos = {row + move[0], col + move[1]};
        if(validMove(board,direction)) {
            board.setCell(newPos[0],newPos[1],symbol);
            board.setCell(row,col," ");
            row = newPos[0];
            col = newPos[1];
            board.setCellUsed(row,col,true);
        }
        else {
            System.out.println("not a valid move");
        }
    }

    public void undoMove(Board board, String direction) {
        int[] opp = convertDir(reverseMove(direction));
        int[] newPos = {row + opp[0],col + opp[1]};
        board.setCell(newPos[0],newPos[1],symbol);
        board.setCell(row,col," ");
        board.setCellUsed(row,col,false);
        row = newPos[0];
        col = newPos[1];
    }

    // count the number of valid moves
    public int countPossibleMoves(Board board) {
        String[] posMoves = {"ru","rd","ur","ul","lu","ld","dr","dl"};
        List<String> validMoves = new ArrayList<String>();
        for(String move : posMoves) {
            if(validMove(board,move)) {
                validMoves.add(move);
            }
        }
        return validMoves.size();
    }

    ///// make the different move choosing methods for the AI

    // a function that returns a random valid move. Used for lowest AI level
    public String randomMove(Board board) {
        String[] posMoves = {"ru","rd","ur","ul","lu","ld","dr","dl"};
        List<String> validMoves = new ArrayList<String>();
        for(String move : posMoves) {
            if(validMove(board,move)) {
                validMoves.add(move);
            }
        }
        return validMoves.get((int) (Math.random()*validMoves.size()));
    }

    // make the move that decreases the other player's next options (if possible)
    public String decNextMove(Board board, Player p2) {
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
    }

    public double evaluateAlgorithm(int numRuns) {
        return 0.0;
    }

}