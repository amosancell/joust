import java.util.*;

public class Board {

    private int nRows;
    private int nCols;
    private String[][] board;
    private Boolean[][] used;

    public Board() {
        this.nRows = 8;
        this.nCols = 8;
        this.board = new String[this.nRows][this.nCols];
        this.used = new Boolean[this.nRows][this.nCols];
        for(int i=0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                this.board[i][j] = " ";
                this.used[i][j] = false;
            }
        }
    }

    public Board(int nRows,int nCols) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.board = new String[nRows][nCols];
        this.used = new Boolean[this.nRows][this.nCols];
        for(int i=0; i < this.nRows; i++) {
            for (int j = 0; j < this.nCols; j++) {
                this.board[i][j] = " ";
                this.used[i][j] = false;
            }
        }
    }

    public Board(int nRows, int nCols, String[][] board, Boolean[][] used) {
        this.nRows = nRows;
        this.nCols = nCols;
        this.board = board;
        this.used = used;
    }

    public Board copy() {
        String[][] bCopy = new String[board.length][board[0].length];
        for(int i=0; i < bCopy.length; i++) {
            bCopy[i] = board[i].clone();
        }
        Boolean[][] uCopy = new Boolean[used.length][used[0].length];
        for(int i=0; i < uCopy.length; i++) {
            uCopy[i] = used[i].clone();
        }
        return new Board(nRows,nCols,bCopy,uCopy);
    }

    // getters and setters of private instance variables
    public int getnRows() {
        return nRows;
    }
    public int getnCols() {
        return nCols;
    }
    public String[][] getBoard() {
        return board;
    }
    public Boolean[][] getUsed() {
        return used;
    }
    public void setnRows(int nRows) {
        this.nRows = nRows;
    }
    public void setnCols(int nCols) {
        this.nCols = nCols;
    }
    public void setBoard(String[][] board) {
        this.board = board;
    }
    public void setUsed(Boolean[][] used) {
        this.used = used;
    }

    //change the value of a cell on the board
    public void setCell(int row, int col, String val) {
        board[row][col] = val;
    }
    // changed the "used" status of a cell
    public void setCellUsed(int row, int col, boolean val) {
        used[row][col] = val;
    }


    // draws a line in console  roughly as long as the board should be
    public void drawLine() {
        for(int i=0; i < nCols; i++) {
            System.out.print("----");
        }
        System.out.println("");
    }

    // draws the board in console
    public void display() {
        drawLine();
        for(int i=0; i < nRows; i++) {
            System.out.print("|");
            for(int j=0; j < nCols; j++) {
                if(used[i][j] && board[i][j].equals(" ")) {
                    System.out.print(" x |");
                }
                else {
                    System.out.print(" " + board[i][j] + " |");
                }
            }
            System.out.println("");
            drawLine();
        }
    }

    public Player[] initPlayers(int numPlayers) {
        Player[] players = new Player[numPlayers];
        List<int[]> startPositions = new ArrayList<int[]>();
        for(int i=0; i < numPlayers; i++) {
            int startRow = (int) (Math.random()*nRows);
            int startCol = (int) (Math.random()*nCols);
            while(startPositions.indexOf(new int[]{startRow,startCol}) != -1) {
                startRow = (int) (Math.random()*nRows);
                startCol = (int) (Math.random()*nCols);
            }
            players[i] = new Player(startRow,startCol,Integer.toString(i+1));
            this.setCell(players[i].getRow(),players[i].getCol(),players[i].getSymbol());
        }
        return players;
    }



}