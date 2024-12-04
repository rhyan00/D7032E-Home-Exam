package player;

import game.state.PlayedApple;
import card.Card;
import java.util.List;

public interface IPlayerAction {
    void play(List<Card> hand, int playerID);
    PlayedApple judge(List<Card> hand, int playerID);
}
