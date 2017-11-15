package game;

import cards.Card;

import java.io.Serializable;

public interface APIClient {
    enum Operation implements Serializable {
        TAKE,
        PASS
    }

    Card take();
    void pass();
    State nextEvent();
}
