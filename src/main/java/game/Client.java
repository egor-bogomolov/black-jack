package game;

import cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Client {

    private APIClient server;
    private boolean finished = false;
    private List<Card> myCards = new ArrayList<>();
    private int myPoints = 0;

    public Client(APIClient server) {
        this.server = server;
    }

    public boolean hasFinished() {
        return finished;
    }

    public void stopPlaying() {
        server.pass();
    }

    public void takeCard() {
        Card card = server.take();
        myCards.add(card);
        myPoints = GameSession.computePoints(myCards);
    }



}
