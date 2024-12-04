package player;

import exception.GameplayInputException;
import exception.InputInvalidException;
import game.state.GameContext;
import game.state.PlayedApple;
import card.Card;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

public class HumanPlayerAction implements IPlayerAction {

    private final GameContext gameContext;

    public HumanPlayerAction(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    @Override
    public void play(List<Card> hand, int playerID) {
        System.out.println("Choose a red apple to play:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println("[" + i + "]   " + hand.get(i).getCardName());  // Display the card name
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            int choice = InputInvalidException.validateCardChoice(input, hand.size());  // Validating the card choice

            PlayedApple playedApple = new PlayedApple(playerID, hand.get(choice).getCardName());  // Use getCardName() to get the card's name
            hand.remove(choice);
            gameContext.addPlayedApple(playedApple);

        } catch (GameplayInputException e) {
            System.out.println(e.getMessage());
            play(hand, playerID);  // Retry the action
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    @Override
    public PlayedApple judge(List<Card> hand, int playerID) {
        System.out.println("Choose which red apple wins:");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input = br.readLine();
            int choice = InputInvalidException.validateJudgmentChoice(input, gameContext.getAllPlayedApples().size());  // Validating the judgment choice

            return gameContext.getPlayedApple(choice);

        } catch (GameplayInputException e) {
            System.out.println(e.getMessage());
            return judge(hand, playerID);  // Retry the action
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }
}
