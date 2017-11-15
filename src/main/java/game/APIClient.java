package game;

import cards.Card;

import java.io.*;
import java.net.Socket;

public class APIClient {
    public static final int PORT = 6789;

    public enum Operation implements Serializable {
        TAKE,
        PASS
    }
    private Socket socket;
    private ObjectOutputStream os;
    private ObjectInputStream is;

    /**
     * Connects to the Server via TCP. Returns the initial state of the game.
     */
    public State connect() throws IOException, ClassNotFoundException {
        socket = new Socket("localhost", PORT);
        os = new ObjectOutputStream(socket.getOutputStream());
        is = new ObjectInputStream(socket.getInputStream());
        return nextEvent();
    }

    public void close() throws IOException {
        socket.close();
    }

    public State take() throws IOException, ClassNotFoundException {
        os.writeObject(Operation.TAKE);
        return nextEvent();
    }

    public State pass() throws IOException {
        os.writeObject(Operation.PASS);
        return nextEvent();
    }

    State nextEvent() throws IOException, ClassNotFoundException {
        return (State)is.readObject();
    }
}
