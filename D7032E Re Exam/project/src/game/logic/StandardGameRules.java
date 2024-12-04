package game.logic;

/**
 * Standard rules implementation for the game Apples to Apples.
 */
public class StandardGameRules implements IGameRules {

    private final GreenApplesCalculator calculator = new GreenApplesCalculator();

    @Override
    public int getWinningGreenApples(int playerCount) {
        return calculator.calculate(playerCount);
    }
}
