package player.communication;

public class PlayerCommunicationHelper {

    private final IPlayerCommunication communication;

    public PlayerCommunicationHelper(IPlayerCommunication communication) {
        this.communication = communication;
    }

    public void sendMessage(String message) {
        communication.sendMessage(message);
    }

    public String receiveInput() {
        return communication.receiveInput();
    }
}
