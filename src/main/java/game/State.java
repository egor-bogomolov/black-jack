package game;

import cards.Card;

import java.io.Serializable;
import java.util.Arrays;

public final class State implements Serializable {
    public Card[][] cards;
    public int[] points;
    public Card[] openDealerCards;
    public int currentTurn;
    // -1 indicates that the game is going on
    public int winner = -1;
    public int myNumber;

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
        return currentTurn == other.currentTurn &&
                Arrays.equals(openDealerCards, other.openDealerCards) &&
                Arrays.deepEquals(cards, other.cards) &&
                Arrays.equals(points, other.points) &&
                winner == other.winner &&
                myNumber == other.myNumber;
    }
}
