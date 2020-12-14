package com.dudnyk;

public class Main {
    public static void main(String[] args) {
        ConsoleRenderer render = new ConsoleRenderer(new String[] {"?", "+", "-"});
        TicTacToeGame game = new TicTacToeGame(render);
        game.play();
    }
}
