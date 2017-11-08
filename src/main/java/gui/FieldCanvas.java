package gui;

import cards.Card;
import game.GameSession;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

class FieldPane extends Pane {
    private static final Image cards = new Image("cards.png");
    private static final double cardImageWidth = cards.getWidth() / 13;
    private static final double cardImageHeight = cards.getHeight() / 4;

    private GameSession gameSession = new GameSession();

    private final Canvas canvas;

    public FieldPane(double width, double height) {
        canvas = new Canvas(width, height);
        getChildren().add(canvas);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        final double x = snappedLeftInset();
        final double y = snappedTopInset();
        final double w = snapSize(getWidth()) - x - snappedRightInset();
        final double h = snapSize(getHeight()) - y - snappedBottomInset();
        canvas.setLayoutX(x);
        canvas.setLayoutY(y);
        canvas.setWidth(w);
        canvas.setHeight(h);
        canvas.getGraphicsContext2D().clearRect(0, 0, w, h);
        redrawCards();
    }

    private void drawCard(Card card, double offsetX, double offsetY, double width, double height) {
        canvas.getGraphicsContext2D().drawImage(
                cards,
                card.getValue().ordinal() * cardImageWidth,
                card.getSuit().ordinal() * cardImageHeight,
                cardImageWidth,
                cardImageHeight,
                offsetX,
                offsetY,
                width,
                height);
    }

    void playerTurnTake() {
        gameSession.takeCard();
        redrawCards();
    }

    void playerTurnPass() {
        gameSession.playAsDealer();
        redrawCards();
    }

    private void redrawCards() {
        double cardCanvasWidth = canvas.getWidth() / 5;
        double cardCanvasHeight = canvas.getHeight() / 2;

        double curX = 0;
        double curY = 0;

        for (Card card: gameSession.getCards(GameSession.Turn.PLAYER)) {
            drawCard(card, curX, curY, cardCanvasWidth, cardCanvasHeight);
            curX += cardCanvasWidth / 2;
        }

        curY += cardCanvasHeight;
        curX = 0;

        for (Card card: gameSession.getCards(GameSession.Turn.DEALER)) {
            drawCard(card, curX, curY, cardCanvasWidth, cardCanvasHeight);
            curX += cardCanvasWidth / 2;
        }
    }
}
