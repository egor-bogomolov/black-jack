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

    public void takeCard() throws IOException, ClassNotFoundException {
        boardState = server.take();
        if (boardState.getMyPoints() >= 21) {
            stopPlayingAndWait();
        }
    }

    public void stopPlayingAndWait() throws IOException, ClassNotFoundException {
        boardState = server.pass();
        while(!hasFinished()) {
            getStateUpdate();
        }
    }

    private void getStateUpdate() throws IOException, ClassNotFoundException {
        boardState = server.nextEvent();
    }
}
