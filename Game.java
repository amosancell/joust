import java.util.Scanner;

public abstract class Game {

    public Board board;
    public Scanner scanner = new Scanner(System.in);

    abstract Player play();

}
