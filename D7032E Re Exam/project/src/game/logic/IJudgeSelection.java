package game.logic;

import player.Player;
import java.util.List;

/**
 * Interface for selecting the judge in the game.
 */
public interface IJudgeSelection {
    /**
     * Selects the judge from the list of players.
     * @param players List of players in the game.
     * @return The selected judge.
     */
    Player selectJudge(List<Player> players);
}
