import game.GameSession;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GameSessionTest {

    @Test
    public void whoseTurn() {
        GameSession gameSession = new GameSession();
        assertNotNull(gameSession.getTurn());
    }

    @Test
    public void cardsTest() {
        GameSession gameSession = new GameSession();
        assertNotNull(gameSession.getCards(GameSession.Turn.PLAYER));
        assertNotNull(gameSession.getCards(GameSession.Turn.DEALER));
    }


}
