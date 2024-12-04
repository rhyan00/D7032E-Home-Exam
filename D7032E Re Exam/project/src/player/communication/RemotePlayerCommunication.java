package player.communication;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * This class represents how a remote player can communicate with the game.
 * Implements the IPlayerCommunication interface
 */
public class RemotePlayerCommunication implements IPlayerCommunication {
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public RemotePlayerCommunication(Socket socket) {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sendMessage(String message) {
        try {
            outputStream.writeObject(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String receiveInput() {
        try {
            return (String) inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
