package game;

import cards.Card;

import java.io.Serializable;

public final class State implements Serializable {
    public Card[][] cards;
    public Card[] openDealerCards;
    public int currentTurn;
    // -1 indicates that the game is going on
    public int winner = -1;

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) return false;

        State other = (State) obj;
        // TODO(atonkikh)
        return true;
    }
}
