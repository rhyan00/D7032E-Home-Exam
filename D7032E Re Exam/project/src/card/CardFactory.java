package card;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CardFactory {
    private final Map<String, Function<String, Card>> cardTypeRegistry = new HashMap<>();

    public void registerCardType(String type, Function<String, Card> creator) {
        cardTypeRegistry.put(type, creator);
    }

    public Card createCard(String data, String cardType) {
        Function<String, Card> creator = cardTypeRegistry.get(cardType);
        if (creator == null) {
            throw new IllegalArgumentException("Unknown card type: " + cardType);
        }
        return creator.apply(data);
    }
}
