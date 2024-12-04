package card;

import exception.CardParsingException;

public class RedApple extends Card implements ICardFunctionalities {
    private final String description;

    public RedApple(String data) {
        super(parseNoun(data));
        this.description = parseDescription(data);
    }

    private static String parseNoun(String data) {
        int startIndex = data.indexOf('[');
        int endIndex = data.indexOf(']');
        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            return data.substring(startIndex + 1, endIndex).trim();
        }
        throw new CardParsingException("Invalid red apple data format: " + data);
    }

    private static String parseDescription(String data) {
        int startIndex = data.indexOf(']') + 1; // Start after the closing bracket
        if (startIndex < data.length()) {
            // Skip any spaces after the "]"
            while (startIndex < data.length() && Character.isWhitespace(data.charAt(startIndex))) {
                startIndex++;
            }
            if (startIndex < data.length() && data.charAt(startIndex) == '-') {
                startIndex++; // Skip the '-' itself
                // Skip any spaces after the "-"
                while (startIndex < data.length() && Character.isWhitespace(data.charAt(startIndex))) {
                    startIndex++;
                }
                return data.substring(startIndex).trim(); // Extract the description
            }
        }
        throw new CardParsingException("Invalid red apple data format: " + data);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void displayCard() {
        System.out.println("Red Apple Card: " + getCardName() + " - " + description);
    }

    @Override
    public void handleCard() {
        System.out.println("Handling Red Apple: " + getCardName());
    }
}





//package card;
//
//public class RedApple extends Card implements ICardFunctionalities {
//    private final String description;
//
//    public RedApple(String data) {
//        super(parseNoun(data));
//        this.description = parseDescription(data);
//    }
//
//    private static String parseNoun(String data) {
//        int startIndex = data.indexOf('[');
//        int endIndex = data.indexOf(']');
//        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
//            return data.substring(startIndex + 1, endIndex).trim();
//        }
//        throw new IllegalArgumentException("Invalid red apple data format: " + data);
//    }
//
//    private static String parseDescription(String data) {
//        int startIndex = data.indexOf(']') + 1; // Start after the closing bracket
//        if (startIndex < data.length()) {
//            // Skip any spaces after the "]"
//            while (startIndex < data.length() && Character.isWhitespace(data.charAt(startIndex))) {
//                startIndex++;
//            }
//            if (startIndex < data.length() && data.charAt(startIndex) == '-') {
//                startIndex++; // Skip the '-' itself
//                // Skip any spaces after the "-"
//                while (startIndex < data.length() && Character.isWhitespace(data.charAt(startIndex))) {
//                    startIndex++;
//                }
//                return data.substring(startIndex).trim(); // Extract the description
//            }
//        }
//        throw new IllegalArgumentException("Invalid red apple data format: " + data);
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    @Override
//    public void displayCard() {
//        System.out.println("Red Apple Card: " + getCardName() + " - " + description);
//    }
//
//    @Override
//    public void handleCard() {
//        System.out.println("Handling Red Apple: " + getCardName());
//    }
//}
