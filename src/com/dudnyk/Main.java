package com.dudnyk;

public class Main {
    public static void main(String[] args) {
        ConsoleRenderer render = new ConsoleRenderer(new String[] {" ", "X", "Y"});
        TicTacToeGame game = new TicTacToeGame(render);
        game.play();
    }
}
