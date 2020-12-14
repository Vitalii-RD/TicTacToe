package com.dudnyk;

public interface IRender {
  void showInit(Marks firstPlayer);
  void showFilled();
  void showPlayer(Marks player);
  void showWin(Marks winner);
  void showDraw();
  boolean isRestart();
  void printTable(Marks[][] table);
  int[] getPosition(Marks player, int tableSize);
}
