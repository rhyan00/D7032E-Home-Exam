package game.state;

import java.util.ArrayList;
import java.util.List;

/**
 * The GameContext class serves as the mediator for managing the game state,
 * including played apples and other interactions between players.
 */
public class GameContext {
    private final List<PlayedApple> playedApples = new ArrayList<>();

    public void addPlayedApple(PlayedApple playedApple) {
        playedApples.add(playedApple);
    }

    public PlayedApple getPlayedApple(int index) {
        if (index < 0 || index >= playedApples.size()) {
            throw new IndexOutOfBoundsException("Invalid index for played apples.");
        }
        return playedApples.get(index);
    }

    public List<PlayedApple> getAllPlayedApples() {
        return new ArrayList<>(playedApples); // Defensive copy
    }

    public void clearPlayedApples() {
        playedApples.clear();
    }
}
