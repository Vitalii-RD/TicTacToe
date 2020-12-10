package com.dudnyk;

import java.util.Scanner;

public class TicTacToeGame {

  private static final Scanner sc = new Scanner(System.in);
  private TicTacToe ticTacToe;
  private IRenderable renderer;

  public TicTacToeGame(IRenderable renderer) {
      this.ticTacToe = new TicTacToe();
      this.renderer = renderer;
  }

  public void play() {
    renderer.showInit(ticTacToe.getCurrentPlayer());
    boolean isPlaying = true;

    while (isPlaying){
      int[] position = ticTacToe.getPosition();
      int state = ticTacToe.getState(position[0], position[1]);

      if (state == TicTacToe.ALREADY_FILLED) {
        renderer.showFilled();
        continue;
      }

      ticTacToe.executeTurn(position[0], position[1]);
      renderer.printTable(ticTacToe.getCloneTable());

      if (state == TicTacToe.AVAILABLE_TURN) {
        renderer.showPlayer(ticTacToe.getCurrentPlayer());
        continue;
      }

      if (state == TicTacToe.WIN) {
        renderer.showWin(ticTacToe.getWinner());
      } else if (state == TicTacToe.DRAW) {
        renderer.showDraw();
      }

      if (renderer.isRestart()) restart();
      else isPlaying = false;
    }
  }

  private void restart() {
    ticTacToe = new TicTacToe();
  }
}
