package game.logic;

import game.scoring.ScoringManager;
import game.state.GameContext;
import player.Player;

import java.util.List;
import java.util.function.Consumer;

/**
 * Factory to create instances of GameLogic with specified rules.
 */
public class GameLogicFactory {

    public static GameLogic createStandardGameLogic(GameContext gameContext, List<Player> players) {
        IGameRules standardRules = new StandardGameRules();
        ScoringManager scoringManager = new ScoringManager(standardRules); // Create ScoringManager
        Consumer<String> output = System.out::println; // Use console output by default
        IJudgeSelection judgeSelectionStrategy = new RandomJudgeSelection(); // Default random judge selection strategy

        return new GameLogic(gameContext, players, scoringManager, output, judgeSelectionStrategy); // Pass judge selection strategy
    }
}
