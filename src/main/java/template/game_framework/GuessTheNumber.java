package template.game_framework;

import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber extends Game {

    private int secretNumber;
    private boolean gameOver;
    private int winner;

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void initializeGame(int numberOfPlayers) {
        Random random = new Random();

        secretNumber = random.nextInt(100) + 1;
        gameOver = false;
        winner = -1;

        System.out.println("Guess the number between 1 and 100!");
        System.out.println("There are " + numberOfPlayers + " players.");
    }

    @Override
    public boolean endOfGame() {
        return gameOver;
    }

    @Override
    public void playSingleTurn(int player) {
        System.out.print("Player " + (player + 1) + ", enter your guess: ");

        int guess = scanner.nextInt();

        if (guess < secretNumber) {
            System.out.println("Too low!");
        } else if (guess > secretNumber) {
            System.out.println("Too high!");
        } else {
            System.out.println("Correct!");

            gameOver = true;
            winner = player;
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("Player " + (winner + 1) + " wins!");
    }
}

