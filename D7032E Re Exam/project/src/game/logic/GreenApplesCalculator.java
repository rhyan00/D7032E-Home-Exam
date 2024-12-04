package game.logic;

import java.util.Map;

public class GreenApplesCalculator {
    private final Map<Integer, Integer> winningRules = Map.of(
    		/* 
    		 * winning requirements based on
    		 * the standard Apples to Apples game
    		 * (make adjustment here to the map for future changes in the winning requirements)
    		*/
            4, 8,
            5, 7,
            6, 6,
            7, 5
        );

        public int calculate(int playerCount) {
            if (playerCount < 4) {
                throw new IllegalArgumentException("The game requires at least 4 players.");
            }
            return winningRules.getOrDefault(playerCount, 4); // Default to 4 for 8+ players
        }
}
