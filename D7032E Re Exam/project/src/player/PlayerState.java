package player;

import game.state.GameContext;
import card.Card;
import java.util.List;

public class PlayerState implements IPlayerState {  // Implementing the IPlayerState interface
    private final int playerID;
    private final boolean isBot; 
    private final List<Card> hand;
    private final List<String> greenApples;
    private final GameContext gameContext;

    // Constructor
    public PlayerState(int playerID, boolean isBot, List<Card> hand, List<String> greenApples, GameContext gameContext) {
        this.playerID = playerID;
        this.isBot = isBot;
        this.hand = hand;
        this.greenApples = greenApples;
        this.gameContext = gameContext;
    }

    // Implementing the methods from IPlayerState interface
    @Override
    public int getPlayerID() {
        return playerID;
    }

    @Override
    public boolean isBot() {
        return isBot;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public GameContext getGameContext() {
        return gameContext;
    }

    @Override
    public List<String> getGreenApples() {
        return greenApples;
    }
}
