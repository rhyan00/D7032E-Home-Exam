package card;

import exception.CardManagerException;
import player.Player;
import player.IPlayerState;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class CardManager {
    private final CardFactory cardFactory;
    private final Map<String, String> cardTypeToFileMap;
    private final List<Card> cards = new ArrayList<>();

    public CardManager(CardFactory cardFactory, Map<String, String> cardTypeToFileMap) {
        this.cardFactory = cardFactory;
        this.cardTypeToFileMap = cardTypeToFileMap;
    }

    public void loadCards() throws CardManagerException {
        for (Map.Entry<String, String> entry : cardTypeToFileMap.entrySet()) {
            String cardType = entry.getKey();
            String filePath = entry.getValue();
            try {
                loadCardsFromFile(filePath, cardType);
            } catch (IOException e) {
                throw new CardManagerException("Error loading cards from file: " + filePath, e);
            }
        }
    }

    private void loadCardsFromFile(String filePath, String cardType) throws IOException, CardManagerException {
        InputStream fileStream = null;

        try {
            File file = new File(filePath);
            if (file.exists()) {
                fileStream = new FileInputStream(file); // Load from absolute path
            } else {
                fileStream = getClass().getClassLoader().getResourceAsStream(filePath); // Load from classpath
            }

            if (fileStream == null) {
                throw new CardManagerException(filePath + " file not found.");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(fileStream, StandardCharsets.UTF_8))) {
                List<String> lines = reader.lines().collect(Collectors.toList());
                for (String line : lines) {
                    try {
                        cards.add(cardFactory.createCard(line.trim(), cardType));
                    } catch (Exception e) {
                        System.err.println("Skipping invalid line: \"" + line + "\" - " + e.getMessage());
                    }
                }
            }
        } finally {
            if (fileStream != null) {
                fileStream.close();
            }
        }
    }

    public <T extends Card> List<Card> getShuffledCardsByType(Class<T> cardType) {
        List<Card> filteredCards = cards.stream()
                                        .filter(cardType::isInstance)
                                        .collect(Collectors.toList());
        Collections.shuffle(filteredCards);
        return filteredCards;
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void dealRedApplesToPlayers(List<Player> players, int numCards) {
        List<Card> redAppleCards = getShuffledCardsByType(Card.class); // Assuming Card.class represents RedApple
        int cardIndex = 0;

        for (Player player : players) {
            IPlayerState playerState = player.getPlayerState();
            for (int i = 0; i < numCards; i++) {
                if (cardIndex < redAppleCards.size()) {
                    playerState.getHand().add(redAppleCards.get(cardIndex));
                    cardIndex++;
                } else {
                    break; // Not enough cards
                }
            }
        }
    }
}
