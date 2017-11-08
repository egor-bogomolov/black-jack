package cards;

public class Card {

    public enum Value {
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
        ACE(11) {
            @Override
            public int getPoints(int sum) {
                if (sum + 11 > 21) {
                    return 1;
                } else {
                    return 11;
                }
            }
        };

        private int points;
        Value(int points) {
            this.points = points;
        }

        public int getPoints(int sum) {
            return points;
        }
    }

    public enum Suit {
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

    public int getPoints(int sum) {
        return value.getPoints(sum);
    }

    public Value getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;

        Card card = (Card) o;

        if (suit != card.suit) return false;
        return value == card.value;
    }

    @Override
    public int hashCode() {
        int result = suit.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }
}