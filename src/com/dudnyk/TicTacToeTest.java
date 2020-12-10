package com.dudnyk;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TicTacToeTest {
  private TicTacToe game;

  @Before
  public void setUp() {
    game = new TicTacToe(3);
  }

  @Test
  public void WhenCreated__getState__returnsAVAILABLE_TURN() {
    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.AVAILABLE_TURN, state);
  }

  @Test
  public void WhenHorizontalLine__getState__returnsWin() {
    game.executeTurn(1, 1);
    game.executeTurn(1, 2);
    game.executeTurn(1, 3);
    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.WIN, state);
  }

  @Test
  public void WhenDiagonalLine__getState__returnsWin() {
    game.executeTurn(1, 1);
    game.executeTurn(2, 2);
    game.executeTurn(3, 3);

    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.WIN, state);
  }

  @Test
  public void WhenDraw__getState__returnsDraw() {
    // X Y X
    // Y Y X
    // X X Y

    //player X
    game.executeTurn(1, 1);
    game.executeTurn(1, 3);
    game.executeTurn(2, 3);
    game.executeTurn(3, 1);
    game.executeTurn(3, 2);

    //player Y
    game.nextTurn();
    game.executeTurn(1, 2);
    game.executeTurn(2, 1);
    game.executeTurn(2, 2);
    game.executeTurn(3, 3);

    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.DRAW, state);
  }

  @Test
  public void WhenFilled_getState__returns_ALREADY_FILLED() {
    game.executeTurn(1, 1);
    game.executeTurn(1, 1);
    int state = game.getState(1, 1);

    Assert.assertEquals(TicTacToe.ALREADY_FILLED, state);
  }

  @Test
  public void WhenInvoked__nextTurn__changes_CurrentPlayer() {
    game.nextTurn();

    Assert.assertEquals("O", game.getCurrentPlayer());
  }

  @Test
  public void WhenFirstInvoked__getCurrentPlayer__returns_X() {
    Assert.assertEquals("X", game.getCurrentPlayer());
  }

  @After
  public void TearDown() {
    game = null;
  }
}