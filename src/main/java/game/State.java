package game;

import cards.Card;

import java.io.Serializable;

public final class State implements Serializable {
    public Card[][] cards;
    public Card[] dealerCards;
    public boolean myTurn;
    public boolean finished;

    State(Card[][] cards, boolean myTurn) {
        this.cards = cards;
        this.myTurn = myTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) return false;

        State other = (State) obj;
        return myTurn == other.myTurn && java.util.Arrays.equals(cardCnts, other.cardCnts);
    }
}
