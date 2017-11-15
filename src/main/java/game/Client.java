package game;

import cards.Card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        server.pass();
        while(!hasFinished()) {
            getStateUpdate();
        }
    }

    private void getStateUpdate() throws IOException, ClassNotFoundException {
        boardState = server.nextEvent();
    }
}
