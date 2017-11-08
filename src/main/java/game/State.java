package game;

import java.io.Serializable;

public final class State implements Serializable {
    public int[] cardCnts;
    public boolean myTurn;

    State(int[] cardCnts, boolean myTurn) {
        this.cardCnts = cardCnts;
        this.myTurn = myTurn;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof State)) return false;

        State other = (State) obj;
        return myTurn == other.myTurn && java.util.Arrays.equals(cardCnts, other.cardCnts);
    }
}
