package game.scoring;

import game.logic.IGameRules;
import player.Player;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages the scoring logic for the game.
 */
public class ScoringManager {
    private final IGameRules gameRules;
    private final Map<Player, Integer> greenAppleCounts = new HashMap<>();

    public ScoringManager(IGameRules gameRules) {
        this.gameRules = gameRules;
    }

    public void awardGreenApple(Player player) {
        greenAppleCounts.put(player, greenAppleCounts.getOrDefault(player, 0) + 1);
    }

    public boolean hasPlayerWon(Player player) {
        int playerCount = greenAppleCounts.keySet().size();
        int winningCount = gameRules.getWinningGreenApples(playerCount);
        return greenAppleCounts.getOrDefault(player, 0) >= winningCount;
    }

    public int getGreenApples(Player player) {
        return greenAppleCounts.getOrDefault(player, 0);
    }
}
