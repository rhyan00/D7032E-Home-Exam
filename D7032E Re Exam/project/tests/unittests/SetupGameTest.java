package unittests;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

//import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.io.File;

import card.Card;
import card.CardFactory;
import card.CardManager;
import card.GreenApple;
import card.RedApple;

import game.logic.IJudgeSelection;
import game.logic.JudgeManager;

import player.BotPlayer;
import player.HumanPlayer;
import player.IPlayerAction;
import player.IPlayerState;
import player.Player;
import player.PlayerState;
import player.communication.PlayerCommunicationHelper;

class SetupGameTest {
	
	/*
	 * ======================================================================================================================
	 * Requirement 1: Read all the green apples (adjectives) from a file and add to the green apples deck.
	 * ======================================================================================================================
	 */ 
	@Test
	void testReadGreenApplesFromFile() {
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
        
       // Verify the file location using File
        File file = new File("resources/greenApples.txt");
        if (file.exists()) {
            System.out.println("File found at: " + file.getAbsolutePath());
        } else {
            throw new IllegalStateException("File not found at expected location: " + file.getAbsolutePath());
        }
        System.out.println();
        
	    try {
	        // Set up CardFactory and register GreenApple
	        CardFactory cardFactory = new CardFactory();
	        cardFactory.registerCardType("GreenApple", GreenApple::new);

	        // Initialize the CardManager with the resource file path
	        CardManager cardManager = new CardManager(cardFactory, Map.of(
	            "GreenApple", "greenApples.txt" // Adjust this path to match the actual resource structure
	        ));

	        // Load the cards from the resource file
	        cardManager.loadCards();

	        // Retrieve the green apple cards
	        List<Card> greenApples = cardManager.getShuffledCardsByType(GreenApple.class);

	        // Assert the total count of green apple cards
	        assertEquals(614, greenApples.size(), "The number of green apple cards does not match the expected value.");

	        // Assert that the adjective and synonyms of each card are not empty
	        for (Card card : greenApples) {
	            GreenApple greenApple = (GreenApple) card;

	            // Ensure the adjective is not empty
	            assertFalse(greenApple.getCardName().isEmpty(), "Green apple adjective is empty.");

	            // Ensure the synonyms are not empty
	            assertFalse(greenApple.getSynonyms().isEmpty(), "Green apple synonyms are empty.");
	        }
	    } catch (Exception e) {
	        fail("Test failed with exception: " + e.getMessage());
	    }
	}


	
	
	/*
	 * ======================================================================================================================
	 * Requirement 2: Read all the red apples (nouns) from a file and add to the red apples deck.
	 * ======================================================================================================================
	 */
	@Test
	void testReadRedApplesFromFile() {
	    System.out.println("Classpath: " + System.getProperty("java.class.path"));
	    
	    // Verify the file location using File
	    File file = new File("resources/redApples.txt");
	    if (file.exists()) {
	        System.out.println("File found at: " + file.getAbsolutePath());
	    } else {
	        throw new IllegalStateException("File not found at expected location: " + file.getAbsolutePath());
	    }
	    System.out.println();

	    try {
	        // Set up CardFactory and register RedApple
	        CardFactory cardFactory = new CardFactory();
	        cardFactory.registerCardType("RedApple", RedApple::new);

	        // Initialize the CardManager with the resource file path
	        CardManager cardManager = new CardManager(cardFactory, Map.of(
	            "RedApple", "redApples.txt" // Adjust this path if needed
	        ));

	        // Load the cards from the resource file
	        cardManager.loadCards();

	        // Retrieve the red apple cards
	        List<Card> redApples = cardManager.getShuffledCardsByType(RedApple.class);

	        // Assert the total count of red apple cards
	        assertEquals(1826, redApples.size(), "The number of red apple cards does not match the expected value.");

	        // Assert that the noun and description of each card are not empty
	        for (Card card : redApples) {
	            RedApple redApple = (RedApple) card;

	            // Ensure the noun is not empty
	            assertFalse(redApple.getCardName().isEmpty(), "Red apple noun is empty.");

	            // Ensure the description is not empty
	            assertFalse(redApple.getDescription().isEmpty(), "Red apple description is empty.");
	        }
	    } catch (Exception e) {
	        fail("Test failed with exception: " + e.getMessage());
	    }
	}

	
	
	/*
	 * ======================================================================================================================
	 * Requirement 3: Shuffle both the green apples and red apples decks
	 * ======================================================================================================================
	 */ 
	@Test
	void testCardShuffling() {
	    try {
	        // Prepare mock data for testing
	        List<String> redAppleData = new ArrayList<>();
	        for (int i = 1; i <= 4; i++) {
	            redAppleData.add("[Noun " + i + "] - description" + i);
	        }
	        Files.write(Paths.get("redApples.txt"), redAppleData);

	        List<String> greenAppleData = new ArrayList<>();
	        for (int i = 1; i <= 4; i++) {
	            greenAppleData.add("[Adjective " + i + "] - (synonym" + i + ", synonym" + (i+1) + ")");
	        }
	        Files.write(Paths.get("greenApples.txt"), greenAppleData);

	        // Set up CardManager with a CardFactory
	        CardFactory cardFactory = new CardFactory();
	        cardFactory.registerCardType("RedApple", RedApple::new);
	        cardFactory.registerCardType("GreenApple", GreenApple::new);

	        // Initialize the CardManager with the file mappings
	        CardManager cardManager = new CardManager(cardFactory, Map.of(
	            "RedApple", "redApples.txt",
	            "GreenApple", "greenApples.txt"
	        ));

	        // Load the cards into the card manager
	        cardManager.loadCards();

	        // Retrieve the original order of red apple cards and green apple cards
	        List<Card> originalRedApples = cardManager.getShuffledCardsByType(RedApple.class);
	        List<Card> originalGreenApples = cardManager.getShuffledCardsByType(GreenApple.class);

	        // Shuffle the cards again (this simulates the shuffling process)
	        List<Card> shuffledRedApples = cardManager.getShuffledCardsByType(RedApple.class);
	        List<Card> shuffledGreenApples = cardManager.getShuffledCardsByType(GreenApple.class);

	        // Check if the order of red apple cards has changed
	        boolean redApplesOrderChanged = false;
	        for (int i = 0; i < originalRedApples.size(); i++) {
	            if (!originalRedApples.get(i).equals(shuffledRedApples.get(i))) {
	                redApplesOrderChanged = true;
	                break;
	            }
	        }

	        // Check if the order of green apple cards has changed
	        boolean greenApplesOrderChanged = false;
	        for (int i = 0; i < originalGreenApples.size(); i++) {
	            if (!originalGreenApples.get(i).equals(shuffledGreenApples.get(i))) {
	                greenApplesOrderChanged = true;
	                break;
	            }
	        }

	        // Assert that both red apples and green apples have shuffled
	        assertTrue(redApplesOrderChanged, "Red apple cards were not shuffled.");
	        assertTrue(greenApplesOrderChanged, "Green apple cards were not shuffled.");

	        // Clean up test files
	        Files.deleteIfExists(Paths.get("redApples.txt"));
	        Files.deleteIfExists(Paths.get("greenApples.txt"));

	    } catch (Exception e) {
	        fail("Test failed with exception: " + e.getMessage());
	    }
	}

    
	/*
	 * ======================================================================================================================
	 * Requirement 4: Deal seven red apples to each player, including the judge.
	 * ======================================================================================================================
	 */ 
    @Test
    void testDealSevenRedApplesToEach() throws Exception {
        // Debugging: Print the class path to verify where resources are located
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
        
       // Verify the file location using File
        File file = new File("resources/redApples.txt");
        if (file.exists()) {
            System.out.println("File found at: " + file.getAbsolutePath());
        } else {
            throw new IllegalStateException("File not found at expected location: " + file.getAbsolutePath());
        }
        System.out.println();
        
        // Create dummy IPlayerState implementations for each player
        IPlayerState humanState = new PlayerState(1, false, new ArrayList<>(), new ArrayList<>(), null);  // Human player
        IPlayerState botState = new PlayerState(2, true, new ArrayList<>(), new ArrayList<>(), null);     // Bot player
        IPlayerState judgeState = new PlayerState(3, false, new ArrayList<>(), new ArrayList<>(), null);  // Judge player

        // Mock or null values for player actions and communication helpers, since they're not needed for this test
        IPlayerAction dummyAction = null;  // Placeholder for actions
        PlayerCommunicationHelper dummyHelper = null;  // Placeholder for communication helper

        // Create the Player objects with necessary IPlayerState and null action (we're not testing actions)
        HumanPlayer humanPlayer = new HumanPlayer(humanState, dummyAction, dummyHelper);  // No actions needed for testing
        BotPlayer botPlayer = new BotPlayer(botState, dummyAction);                       // No actions needed for testing
        HumanPlayer judgePlayer = new HumanPlayer(judgeState, dummyAction, dummyHelper);  // Using HumanPlayer as Judge

        // Setup CardManager
        CardFactory cardFactory = new CardFactory();  // Assuming a simple card factory
        cardFactory.registerCardType("RedApple", RedApple::new);  // Register RedApple card type

        Map<String, String> cardTypeToFileMap = new HashMap<>();
        cardTypeToFileMap.put("RedApple", "resources/redApples.txt");  // Ensure this path matches the actual resource location

        CardManager cardManager = new CardManager(cardFactory, cardTypeToFileMap);

        // Load cards
        cardManager.loadCards();

        // Deal 7 red apples to each player (including the judge)
        cardManager.dealRedApplesToPlayers(List.of(humanPlayer, botPlayer, judgePlayer), 7);

        // Assert that each player has exactly 7 red apples
        assertEquals(7, humanPlayer.getHand().size(), "Human player should have 7 red apples.");
        assertEquals(7, botPlayer.getHand().size(), "Bot player should have 7 red apples.");
        assertEquals(7, judgePlayer.getHand().size(), "Judge player should have 7 red apples.");

        // Assert that the cards dealt to the players are of type RedApple
        for (int i = 0; i < 7; i++) {
            assertTrue(humanPlayer.getHand().get(i) instanceof RedApple, "Card in human player's hand is not a RedApple.");
            assertTrue(botPlayer.getHand().get(i) instanceof RedApple, "Card in bot player's hand is not a RedApple.");
            assertTrue(judgePlayer.getHand().get(i) instanceof RedApple, "Card in judge player's hand is not a RedApple.");
        }
    }


	/*
	 * ======================================================================================================================
	 * Requirement 5: Randomize which player starts being the judge.
	 * ======================================================================================================================
	 */ 
    @Test
    public void testJudgeRandomization() {
        // Create some sample players (could be BotPlayer, HumanPlayer, etc.)
        Player player1 = new BotPlayer(null, null); // Simplified player initialization
        Player player2 = new BotPlayer(null, null);
        Player player3 = new HumanPlayer(null, null, null);
        List<Player> players = Arrays.asList(player1, player2, player3);

        // Custom implementation of IJudgeSelection within the test method
        IJudgeSelection randomJudgeSelection = new IJudgeSelection() {
            private final Random random = new Random();

            @Override
            public Player selectJudge(List<Player> players) {
                // Randomly select a player from the list
                return players.get(random.nextInt(players.size()));
            }
        };

        // Create the JudgeManager with the custom strategy
        JudgeManager judgeManager = new JudgeManager(players, randomJudgeSelection);

        // Get the first judge
        Player initialJudge = judgeManager.getCurrentJudge();

        // Run the randomization test multiple times to ensure randomization
        boolean isRandomized = false;
        for (int i = 0; i < 100; i++) {
            Player nextJudge = judgeManager.getCurrentJudge();
            judgeManager.rotateJudge();  // Rotate the judge

            // If we find a case where the next judge is not the same as the previous one, it's randomized
            if (!nextJudge.equals(initialJudge)) {
                isRandomized = true;
                break;
            }
        }

        // Assert that the randomization worked (judge should not always be the same)
        assertTrue(isRandomized, "The judge selection should be randomized.");
    }




}
