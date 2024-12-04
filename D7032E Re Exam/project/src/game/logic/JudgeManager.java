package game.logic;

import exception.JudgeManagerException;
import player.Player;
import java.util.List;

/**
 * Manages the judge role and transitions in the game.
 */
public class JudgeManager {
    private final List<Player> players;
    private final IJudgeSelection judgeSelectionStrategy; // Dependency injection for the strategy
    private int currentJudgeIndex;

    public JudgeManager(List<Player> players, IJudgeSelection judgeSelectionStrategy) {
        if (players == null || players.isEmpty()) {
            throw new JudgeManagerException("Player list cannot be null or empty.");
        }
        this.players = players;
        this.judgeSelectionStrategy = judgeSelectionStrategy;
        try {
            this.currentJudgeIndex = players.indexOf(judgeSelectionStrategy.selectJudge(players)); // Initialize with random judge
            if (currentJudgeIndex == -1) {
                throw new JudgeManagerException("Selected judge does not exist in the player list.");
            }
        } catch (Exception e) {
            throw new JudgeManagerException("Error initializing JudgeManager with the provided strategy.", e);
        }
    }

    /**
     * Gets the current judge.
     */
    public Player getCurrentJudge() {
        return players.get(currentJudgeIndex);
    }

    /**
     * Advances the judge role to the next player in the list.
     */
    public void rotateJudge() {
        currentJudgeIndex = (currentJudgeIndex + 1) % players.size();
    }
}
