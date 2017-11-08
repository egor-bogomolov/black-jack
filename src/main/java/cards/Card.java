package cards;

import java.io.Serializable;

public final class Card implements Serializable {

    public enum Value implements Serializable {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(2),
        QUEEN(3),
        KING(4),
        ACE(11);

        private int points;
        Value(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }

    public enum Suit implements Serializable {
        HEARTS,
        DIAMONDS,
        CLUBS,
        SPADES
    }

    private final Suit suit;
    private final Value value;

    public Card(Value value, Suit suit) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getPoints() {
        return value.getPoints();
    }

    public Value getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        return suit == card.suit && value == card.value;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}