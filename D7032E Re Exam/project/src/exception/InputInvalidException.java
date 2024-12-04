package exception;

public class InputInvalidException {

    public static int validateCardChoice(String input, int maxChoice) throws GameplayInputException {
        try {
            int choice = Integer.parseInt(input);
            if (choice < 0 || choice >= maxChoice) {
                throw new GameplayInputException("Choice is out of range. Please select a valid card.");
            }
            return choice;
        } catch (NumberFormatException e) {
            throw new NumberFormatInputException("Input is not a valid number.", e);
        }
    }

    public static int validateJudgmentChoice(String input, int maxChoice) throws GameplayInputException {
        return validateCardChoice(input, maxChoice);
    }
}
