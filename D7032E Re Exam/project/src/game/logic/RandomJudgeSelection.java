package game.logic;

import player.Player;
import java.util.List;
import java.util.Random;

/**
 * Random judge selection strategy.
 */
public class RandomJudgeSelection implements IJudgeSelection {
    
    private final Random random = new Random();
    
    @Override
    public Player selectJudge(List<Player> players) {
        if (players == null || players.isEmpty()) {
            throw new IllegalArgumentException("Player list cannot be null or empty.");
        }
        int randomIndex = random.nextInt(players.size());
        return players.get(randomIndex); // Select a random player
    }
}
