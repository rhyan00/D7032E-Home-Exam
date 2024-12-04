package player;

import game.state.GameContext;
import game.state.PlayedApple;
import card.Card;
import java.util.List;
import java.util.Random;

public class BotPlayerAction implements IPlayerAction {

    private final GameContext gameContext;

    public BotPlayerAction(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    @Override
    public void play(List<Card> hand, int playerID) {
        try {
            Thread.sleep(new Random().nextInt(500));  // Simulate some delay for the bot
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Get the card name (String) and pass to PlayedApple
        PlayedApple playedApple = new PlayedApple(playerID, hand.get(0).getCardName());  // Choose first card's name
        hand.remove(0);
        gameContext.addPlayedApple(playedApple);
    }

    @Override
    public PlayedApple judge(List<Card> hand, int playerID) {
        return gameContext.getPlayedApple(0);  // Bot judges the first apple
    }
}
