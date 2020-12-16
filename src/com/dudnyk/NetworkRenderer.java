package com.dudnyk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class NetworkRenderer implements IRender{
    private PrintStream outputWriter;
    private BufferedReader bufferedReader;
    private Socket client;
    private String[] views;

    public NetworkRenderer(Socket client) throws IOException {
        this.views =  new String[] {" ", "X", "O"};
        this.client = client;
        this.outputWriter = new PrintStream(client.getOutputStream());
        this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    public NetworkRenderer(Socket client, String[] views) throws IOException {
        this(client);
        if (views.length > 2) {
            this.views = views;
        }
    }

    @Override
    public void showInit(Marks firstPlayer) {
        outputWriter.println("TIC TAC TOE");
        outputWriter.println("First goes " + views[firstPlayer.ordinal()]);
    }

    @Override
    public void showFilled() {
        outputWriter.println("This position is already taken.");
    }

    @Override
    public void showPlayer(Marks player) {
        outputWriter.println(views[player.ordinal()] + " player");
    }

    @Override
    public void showWin(Marks winner) {
        outputWriter.println("Winner: " + (winner != Marks.empty ? views[winner.ordinal()] : "None"));
    }

    @Override
    public void showDraw() {
        outputWriter.println("Draw. Try again.");
    }

    @Override
    public boolean isRestart() {
        outputWriter.println("+ restart");
        outputWriter.println("- finish");

        while (true) {
            try {
                outputWriter.print("-> ");
                String input = bufferedReader.readLine();
                outputWriter.println();
                if (input.equals("+")) return true;
                else if (input.equals("-")) return false;
                else throw new Exception("Wrong input");
            } catch (Exception e) {
                outputWriter.println(e.getMessage());
            }
        }
    }

    @Override
    public int[] getPosition(Marks player, int tableSize) {
        boolean isValidInput = false;
        int row = 0;
        int column = 0;

        while (!isValidInput) {
            outputWriter.print("Column and row for " + views[player.ordinal()] + ": ");
            try {
                String[] numbers = bufferedReader.readLine().split(" ");
                row = Integer.parseInt(numbers[0]);
                column = Integer.parseInt(numbers[1]);

                if ( !(0 < row && row <= tableSize) ||
                        !(0 < column && column <= tableSize) ) {
                    throw new Exception();
                } else isValidInput = true;
            } catch (Exception e) {
                outputWriter.println("Input should contain 2 separate digits in range [1; 3]. Try again");
            }
        }
        return new int[] {row-1, column-1};
    }

    @Override
    public void printTable(Marks[][] table) {
        int tableSize = table.length;
        outputWriter.println();

        for (int i = 0; i < tableSize; i++) {
            if (i > 0) {
                int numOfDashes = tableSize  * 3 + tableSize - 1;
                for (int j = 0; j < numOfDashes; j++) {
                    outputWriter.print((j+1) % 4 == 0 ? "+" : "-");
                }
            }

            outputWriter.println();

            for (int j = 0; j < tableSize; j++) {
                if (j > 0)
                    outputWriter.print("|");
                outputWriter.printf(" %s ", views[table[i][j].ordinal()]);
            }
            outputWriter.println();
        }

        outputWriter.println();
    }
}
