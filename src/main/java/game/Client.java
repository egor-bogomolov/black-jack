package game;

import java.io.IOException;

public class Client {

    private APIClient server;
    private State boardState;

    public Client(APIClient server) {
        this.server = server;
    }

    public boolean hasFinished() {
        return boardState.hasFinished();
    }

    public State takeCard() throws IOException, ClassNotFoundException {
        boardState = server.take();
        if (boardState.getMyPoints() >= 21) {
            stopPlayingAndWait();
        }
        return boardState;
    }

    public void stopPlayingAndWait() throws IOException, ClassNotFoundException {
        boardState = server.pass();
        while(!hasFinished()) {
            getStateUpdate();
        }
        server.close();
    }

    private void getStateUpdate() throws IOException, ClassNotFoundException {
        boardState = server.nextEvent();
    }

    public State connect() throws IOException, ClassNotFoundException {
        boardState = server.connect();
        return boardState;
    }

}
