package game.logic;

import game.scoring.ScoringManager;
import game.state.GameContext;
import game.state.PlayedApple;
import player.Player;
import java.util.List;
import java.util.function.Consumer;

/**
 * Handles core game mechanics, including player turns and rounds.
 */
public class GameLogic {
    private final GameContext gameContext;
    private final List<Player> players;
    private final ScoringManager scoringManager;
    private final Consumer<String> output; // Dependency for handling output
    private final JudgeManager judgeManager;

    public GameLogic(
            GameContext gameContext,
            List<Player> players,
            ScoringManager scoringManager,
            Consumer<String> output, // Inject output handler
            IJudgeSelection judgeSelectionStrategy // Inject judge selection strategy
    ) {
        this.gameContext = gameContext;
        this.players = players;
        this.scoringManager = scoringManager;
        this.output = output;
        this.judgeManager = new JudgeManager(players, judgeSelectionStrategy); // Initialize JudgeManager
    }

    public void startGame() {
        while (!isGameOver()) {
            playRound();
        }
        declareWinner();
    }

    private void playRound() {
        Player currentJudge = judgeManager.getCurrentJudge();
        output.accept("Player " + currentJudge.getPlayerState().getPlayerID() + " is the judge.");

        for (Player player : players) {
            if (!player.equals(currentJudge)) {
                int playerID = player.getPlayerState().getPlayerID();
                output.accept("Player " + playerID + " is playing...");
                player.play();
            }
        }

        PlayedApple winningApple = currentJudge.judge();
        gameContext.addPlayedApple(winningApple);
        output.accept("Round winner: " + winningApple);

        // Rotate judge for the next round
        judgeManager.rotateJudge();
    }

    private boolean isGameOver() {
        return players.stream().anyMatch(player -> scoringManager.hasPlayerWon(player));
    }

    private void declareWinner() {
        Player winner = players.stream()
                .max((p1, p2) -> Integer.compare(
                        scoringManager.getGreenApples(p1),
                        scoringManager.getGreenApples(p2)
                ))
                .orElse(null);

        if (winner != null) {
            int winnerID = winner.getPlayerState().getPlayerID();
            output.accept("The winner is Player " + winnerID + "!");
        }
    }
}
