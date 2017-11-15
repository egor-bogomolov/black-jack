package game;

import cards.Card;

import java.io.Serializable;

public final class State implements Serializable {
    public Card[][] cards;
    public int[] points;
    public Card[] openDealerCards;
    public int currentTurn;
    // -1 indicates that the game is going on
    private int winner = -1;
    private int myNumber;

    public int getMyPoints() {
        return points[myNumber];
    }

    public boolean hasFinished() {
        return winner != -1;
    }

    public int getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) return false;

        State other = (State) obj;
        // TODO(atonkikh)
        return true;
    }
}
