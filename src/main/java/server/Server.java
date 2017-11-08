package server;

import game.APIClient;
import game.GameSession;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private static final int REQUIRED_NUMBER_OF_PLAYERS = 2;

    private int port = 8080;
    private String hostname = "localhost";

    private boolean isRunning;

    private ServerSocket serverSocket;

    private int numberOfPlayers;

    private List<Socket> playerSockets;

    private GameSession gameSession;


    public void start() {
        try {
            serverSocket = new ServerSocket(port);

        } catch (IOException e) {
            e.printStackTrace();
        }

        isRunning = true;
        new Thread(new ConnectionHandler()).start();
    }

    private class ConnectionHandler implements Runnable {

        @Override
        public void run() {
            while (numberOfPlayers < REQUIRED_NUMBER_OF_PLAYERS) {
                try {
                    Socket socket = serverSocket.accept();
                    playerSockets.add(socket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            createGameSession();
            playerSockets.forEach(playerSocket -> new Thread(new PlayerHandler(playerSocket)).start());
        }
    }

    private class PlayerHandler implements Runnable {
        private DataInputStream inputStream;
        private DataOutputStream outputStream;

        public PlayerHandler(Socket socket) {
            try {
                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                try {
                    String operationString = inputStream.readUTF();
                    APIClient.Operation operation = APIClient.Operation.valueOf(operationString);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void createGameSession() {
        gameSession = new GameSession();
    }

    public void stop() {
        isRunning = false;
    }
}
