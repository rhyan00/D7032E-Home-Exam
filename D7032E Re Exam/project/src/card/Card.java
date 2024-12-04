package card;

/**
 * This class is the main class that all cards should extend
 */
public abstract class Card {
	protected String cardName;
	
	public Card(String cardName) {
		this.cardName = cardName;
	}
	
	public String getCardName() {
		return cardName;
	}
	
    // Abstract method, to be implemented by subclasses
    public abstract void handleCard();
    
    // This is an abstract method that will be implemented by subclasses to provide specific functionalities.
    public abstract void displayCard();
}
