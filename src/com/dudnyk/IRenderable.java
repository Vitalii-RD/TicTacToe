package com.dudnyk;

public interface IRenderable {
  void showInit(int firstPlayer);
  void showFilled();
  void showPlayer(int player);
  void showWin(int winner);
  void showDraw();
  boolean isRestart();
  void printTable(int[][] table);
  int[] getPosition(int player, int tableSize);
}
