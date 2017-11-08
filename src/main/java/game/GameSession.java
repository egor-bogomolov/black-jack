package game;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;
import java.util.List;

import static game.GameSession.Turn.DEALER;
import static game.GameSession.Turn.PLAYER;

public class GameSession {
    public enum Turn {
        PLAYER,
        DEALER
    }

    private Turn turn;
    private final Deck deck;
    private final List<Card> playersCards;
    private final List<Card> dealersCards;
    private boolean isFinished = false;
    private int playersPoints;
    private int dealersPoints;

    public GameSession() {
        turn = PLAYER;
        deck = new Deck();
        playersCards = new ArrayList<>();
        dealersCards = new ArrayList<>();
        takeCard(PLAYER);
        takeCard(DEALER);
        takeCard(PLAYER);
        takeCard(DEALER);
    }

    public Turn getTurn() {
        return turn;
    }

    public List<Card> getCards(Turn turn) {
        if (turn == PLAYER)
            return playersCards;
        return dealersCards;
    }

    public int getDealersPoints() {
        return dealersPoints;
    }

    public int getPlayersPoints() {
        return playersPoints;
    }

    public void takeCard() {
        takeCard(PLAYER);
    }

    private void takeCard(Turn turn) {
        Card card = deck.getCard();
        if (turn == PLAYER) {
            playersCards.add(card);
            playersPoints += card.getPoints(playersPoints);
        } else {
            dealersCards.add(card);
            dealersPoints += card.getPoints(dealersPoints);
        }
    }

    public void playAsDealer() {
        while (dealersPoints < 17)
            takeCard(DEALER);
        isFinished = true;
    }

    public Turn getWinner() {
        if (playersPoints > 21)
            return DEALER;
        if (dealersPoints > 21)
            return PLAYER;
        if (playersPoints > dealersPoints)
            return PLAYER;
        return DEALER;
    }

}
