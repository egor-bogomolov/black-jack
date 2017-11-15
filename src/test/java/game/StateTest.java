package game;

import cards.Card;
import org.junit.Test;

import java.io.*;

import static cards.Card.Suit.HEARTS;
import static cards.Card.Suit.SPADES;
import static cards.Card.Value.KING;
import static cards.Card.Value.THREE;
import static cards.Card.Value.TWO;
import static org.junit.Assert.*;

public class StateTest {
    @Test
    public void serializationTest() throws Throwable {
        State state = new State();
        state.cards = new Card[][] {
                new Card[]{new Card(TWO, HEARTS), new Card(THREE, SPADES)},
                new Card[]{}
        };
        state.myNumber = 1;
        state.points = new int[]{5, 0};
        state.winner = -1;
        state.currentTurn = 0;
        state.openDealerCards = new Card[]{new Card(KING, SPADES)};

        byte[] bytes;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(state);
            bytes = baos.toByteArray();
        }

        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            State state2 = (State)ois.readObject();
            assertEquals(state, state2);
        }
    }
}