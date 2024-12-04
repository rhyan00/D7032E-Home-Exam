package player;

import game.state.GameContext;
import java.util.List;
import card.Card;

public interface IPlayerState {
    int getPlayerID();
    boolean isBot(); 
    List<Card> getHand();
    GameContext getGameContext();
    List<String> getGreenApples();
}
