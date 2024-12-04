package player;

import game.state.PlayedApple;
import card.Card;
import java.util.List;

public class BotPlayer extends Player {

    public BotPlayer(IPlayerState playerState, IPlayerAction playerAction) {
        super(playerState, playerAction);
    }

    @Override
    public void play() {
        playerAction.play(playerState.getHand(), playerState.getPlayerID());
    }

    @Override
    public PlayedApple judge() {
        return playerAction.judge(playerState.getHand(), playerState.getPlayerID());
    }
    
    @Override
    public List<Card> getHand() {
        return playerState.getHand();
    }

}
