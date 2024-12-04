package main;

import card.*;
import player.*;
import player.communication.*;
import game.logic.*;
import game.state.*;

import java.util.*;

public class Apples2Apples {
    private final CardManager cardManager;
    private final List<Player> players;
    private final JudgeManager judgeManager;

    public Apples2Apples(CardManager cardManager, List<Player> players, JudgeManager judgeManager) {
        this.cardManager = cardManager;
        this.players = players;
        this.judgeManager = judgeManager;
    }

    public void setUpGame() {
        try {
            // Load all cards
            cardManager.loadCards();

            // Shuffle decks
            List<Card> greenApplesDeck = cardManager.getShuffledCardsByType(GreenApple.class);
            List<Card> redApplesDeck = cardManager.getShuffledCardsByType(RedApple.class);

            System.out.println("Total Green Apples loaded: " + greenApplesDeck.size());
            System.out.println("Total Red Apples loaded: " + redApplesDeck.size());

            // Deal seven red apples to each player
            for (Player player : players) {
                for (int i = 0; i < 7; i++) {
                    if (!redApplesDeck.isEmpty()) {
                        player.getPlayerState().getHand().add(redApplesDeck.remove(0));
                    } else {
                        System.err.println("Not enough red apple cards to deal. Player " +
                                player.getPlayerState().getPlayerID() + " may have fewer cards.");
                        break;
                    }
                }
                System.out.println("Player " + player.getPlayerState().getPlayerID() + 
                        " has " + player.getPlayerState().getHand().size() + " cards.");
            }

            // Randomize the judge using JudgeManager
            Player initialJudge = judgeManager.getCurrentJudge();
            System.out.println("*****************************************************");
            System.out.println("**                 NEW ROUND - JUDGE               **");
            System.out.println("*****************************************************");
            System.out.println("The judge for the first round is: Player " + initialJudge.getPlayerState().getPlayerID());
            System.out.println("Game setup is done!");

        } catch (Exception e) {
            System.err.println("Error setting up the game: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Initialize card factory and register card types
        CardFactory cardFactory = new CardFactory();
        cardFactory.registerCardType("RedApple", RedApple::new);
        cardFactory.registerCardType("GreenApple", GreenApple::new);

        Map<String, String> cardFiles = Map.of(
            "GreenApple", "greenApples.txt",
            "RedApple", "redApples.txt"
        );
        CardManager cardManager = new CardManager(cardFactory, cardFiles);

     // Create player states and actions
        IPlayerState botState1 = new MockPlayerState();
        IPlayerState humanState = new MockPlayerState();
        IPlayerState botState2 = new MockPlayerState();

        IPlayerAction botAction = new MockPlayerAction();
        IPlayerAction humanAction = new MockPlayerAction();

        // Use LocalPlayerCommunication for console interaction
        PlayerCommunicationHelper humanCommunication = new PlayerCommunicationHelper(new LocalPlayerCommunication());

        // Create players (example with bot and human players)
        List<Player> players = List.of(
            new BotPlayer(botState1, botAction),
            new HumanPlayer(humanState, humanAction, humanCommunication),
            new BotPlayer(botState2, botAction) // Ensure a new state for the second bot
        );


        // Initialize JudgeManager with RandomJudgeSelection
        JudgeManager judgeManager = new JudgeManager(players, new RandomJudgeSelection());

        // Create and set up the game
        Apples2Apples game = new Apples2Apples(cardManager, players, judgeManager);
        game.setUpGame();
    }
}

// Mock classes for demonstration purposes
class MockPlayerState implements IPlayerState {
    private final List<Card> hand = new ArrayList<>();

    @Override
    public int getPlayerID() {
        return new Random().nextInt(100); // Random player ID for example
    }

    @Override
    public boolean isBot() {
        return true;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public GameContext getGameContext() {
        return new GameContext();
    }

    @Override
    public List<String> getGreenApples() {
        return new ArrayList<>(); // Mock list of green apples
    }
}

class MockPlayerAction implements IPlayerAction {
    @Override
    public void play(List<Card> hand, int playerID) {
        // Mock play logic
    }

    @Override
    public PlayedApple judge(List<Card> hand, int playerID) {
        // Mock judge logic
        return new PlayedApple(playerID, hand.isEmpty() ? "None" : hand.get(0).getCardName());
    }
}
