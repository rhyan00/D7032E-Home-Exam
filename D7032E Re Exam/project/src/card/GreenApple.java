package card;

import exception.CardParsingException;
import java.util.Arrays;
import java.util.List;

public class GreenApple extends Card implements ICardFunctionalities {
    private final List<String> synonyms;

    public GreenApple(String data) {
        super(parseAdjective(data));
        this.synonyms = parseSynonyms(data);
    }

    private static String parseAdjective(String data) {
        int startIndex = data.indexOf('[');
        int endIndex = data.indexOf(']');
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return data.substring(startIndex + 1, endIndex).trim();
        }
        throw new CardParsingException("Invalid green apple data format: " + data);
    }

    private static List<String> parseSynonyms(String data) {
        int startIndex = data.indexOf('(');
        int endIndex = data.indexOf(')');
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            String synonymsData = data.substring(startIndex + 1, endIndex).trim();
            return Arrays.asList(synonymsData.split(",\\s*"));
        }
        return List.of(); // No synonyms provided
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    @Override
    public void displayCard() {
        System.out.println("Green Apple Card: " + getCardName() + " (Synonyms: " + synonyms + ")");
    }

    @Override
    public void handleCard() {
        System.out.println("Handling Green Apple: " + getCardName());
    }
}



//package card;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class GreenApple extends Card implements ICardFunctionalities {
//    private final List<String> synonyms;
//
//    public GreenApple(String data) {
//        super(parseAdjective(data));
//        this.synonyms = parseSynonyms(data);
//    }
//
//    private static String parseAdjective(String data) {
//        int startIndex = data.indexOf('[');
//        int endIndex = data.indexOf(']');
//        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
//            return data.substring(startIndex + 1, endIndex).trim();
//        }
//        throw new IllegalArgumentException("Invalid green apple data format: " + data);
//    }
//
//    private static List<String> parseSynonyms(String data) {
//        int startIndex = data.indexOf('(');
//        int endIndex = data.indexOf(')');
//        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
//            String synonymsData = data.substring(startIndex + 1, endIndex).trim();
//            return Arrays.asList(synonymsData.split(",\\s*"));
//        }
//        return List.of(); // No synonyms provided
//    }
//
//    public List<String> getSynonyms() {
//        return synonyms;
//    }
//
//    @Override
//    public void displayCard() {
//        System.out.println("Green Apple Card: " + getCardName() + " (Synonyms: " + synonyms + ")");
//    }
//
//    @Override
//    public void handleCard() {
//        System.out.println("Handling Green Apple: " + getCardName());
//    }
//}
