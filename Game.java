import java.util.Scanner;

public abstract class Game {

    Board board;
    Scanner scanner = new Scanner(System.in);

    abstract Player play();

}
