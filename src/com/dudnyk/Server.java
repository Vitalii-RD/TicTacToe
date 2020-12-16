package com.dudnyk;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 4000;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while (true)
            waitForClient(serverSocket);
    }

    private static void waitForClient(ServerSocket serverSocket) throws IOException {
        System.out.println("Waiting for a client ...");
        Socket client = serverSocket.accept();
        System.out.printf("Client connected to %s. %s:%s\n",
                client.getLocalPort(),
                client.getInetAddress().getHostAddress(),
                client.getPort());

        NetworkRenderer render = new NetworkRenderer(client);
        TicTacToeGame game = new TicTacToeGame(render);
        game.play();
    }
}
