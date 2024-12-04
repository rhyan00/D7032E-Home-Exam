package player;

import game.state.PlayedApple;
import player.communication.PlayerCommunicationHelper;
import card.Card;
import java.util.List;

public class HumanPlayer extends Player {
    private final PlayerCommunicationHelper communicationHelper;

    public HumanPlayer(IPlayerState playerState, IPlayerAction playerAction, PlayerCommunicationHelper communicationHelper) {
        super(playerState, playerAction);
        this.communicationHelper = communicationHelper;
    }

    @Override
    public void play() {
        communicationHelper.sendMessage("Your turn to play!");
        playerAction.play(playerState.getHand(), playerState.getPlayerID());
    }

    @Override
    public PlayedApple judge() {
        communicationHelper.sendMessage("Your turn to judge!");
        return playerAction.judge(playerState.getHand(), playerState.getPlayerID());
    }
    
    @Override
    public List<Card> getHand() {
        return playerState.getHand();
    }

}
