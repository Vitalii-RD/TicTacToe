package com.dudnyk;

public class TicTacToeGame {

  private TicTacToe ticTacToe;
  private IRender renderer;

  public TicTacToeGame(IRender renderer) {
      this.ticTacToe = new TicTacToe();
      this.renderer = renderer;
  }

  public void play() {
    renderer.showInit(ticTacToe.getCurrentPlayer());
    boolean isPlaying = true;

    while (isPlaying){
      int[] position = renderer.getPosition(ticTacToe.getCurrentPlayer(), ticTacToe.getTableSize());
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
      } else if (state == TicTacToe.WIN) {
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
