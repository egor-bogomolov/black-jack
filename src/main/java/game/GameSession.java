package game;

import cards.Card;
import cards.Deck;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static game.GameSession.Turn.DEALER;
import static game.GameSession.Turn.PLAYER;

public class GameSession {
    private final Deck deck;
    private final List<List<Card>> playersCards;
    private boolean isFinished = false;
    private int playersPoints;
    private int dealersPoints;

    public GameSession() {
        deck = new Deck();
        playersCards = new ArrayList<>();
        playersCards.add(deck.getCard());
        playersCards.add(deck.getCard());
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

    private void takeCard(int id) {
        Card card = deck.getCard();
        playersCards.get(id).add(card);
        playersPoints += card.getPoints(playersPoints);
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
