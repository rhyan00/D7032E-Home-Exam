package player;

import game.state.PlayedApple;
import card.Card;
import java.util.List;

public abstract class Player {
    protected IPlayerState playerState;  // Now uses an interface for state management
    protected IPlayerAction playerAction;  // Gameplay behavior

    public Player(IPlayerState playerState, IPlayerAction playerAction) {
        this.playerState = playerState;
        this.playerAction = playerAction;
    }

    public abstract void play();
    public abstract PlayedApple judge();
    public abstract List<Card> getHand();

    public IPlayerState getPlayerState() {
        return playerState;
    }
}
