package game.state;

public class PlayedApple {
    private final int playerID;
    private final String redApple;

    public PlayedApple(int playerID, String redApple) {
        this.playerID = playerID;
        this.redApple = redApple;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getRedApple() {
        return redApple;
    }

    @Override
    public String toString() {
        return "Player " + playerID + " played: " + redApple;
    }
}
