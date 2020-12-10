package com.dudnyk;

public class Main {
    public static void main(String[] args) {
        TicTacToeGame game = new TicTacToeGame(new ConsoleRenderer());
        game.play();
    }
}
