package game.logic;

/**
 * Interface for defining rules of the game.
 * This allows flexibility for future variations of the game.
 */
public interface IGameRules {
    int getWinningGreenApples(int playerCount);
}
