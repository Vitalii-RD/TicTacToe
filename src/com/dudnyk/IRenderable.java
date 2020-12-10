package com.dudnyk;

public interface IRenderable {
  void showInit(String firstPlayer);
  void showFilled();
  void showPlayer(String player);
  void showWin(String winner);
  void showDraw();
  boolean isRestart();
  void printTable(int[][] table);
  int[] getPosition(String player, int tableSize);
}
