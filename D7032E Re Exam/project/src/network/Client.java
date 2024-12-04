package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

import exception.ClientConnectionException;

/**
 * Represents the logic for connecting a remote player to the game server.
 * Handles communication with the server.
 */
public class Client {
    private Socket socket;
    private ObjectOutputStream outToServer;
    private ObjectInputStream inFromServer;

    public Client(String ipAddress, int port) throws ClientConnectionException {
        try {
            // Connect to the server
            socket = new Socket(ipAddress, port);
            outToServer = new ObjectOutputStream(socket.getOutputStream());
            inFromServer = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            throw new ClientConnectionException("Failed to connect to the server.", e);
        }
    }

    public void start() throws ClassNotFoundException, IOException {
        String nextMessage;
        Scanner scanner = new Scanner(System.in);

        try {
            while ((nextMessage = (String) inFromServer.readObject()) != null) {
                System.out.println(nextMessage);

                if (nextMessage.contains("Type") || nextMessage.contains("keep") || nextMessage.contains("choose")) {
                    String response = scanner.nextLine();
                    outToServer.writeObject(response);
                }

                if (nextMessage.contains("winner")) {
                    break;
                }
            }
        } finally {
            scanner.close();
            closeConnections();
        }
    }

    public void closeConnections() throws IOException {
        if (socket != null) socket.close();
        if (outToServer != null) outToServer.close();
        if (inFromServer != null) inFromServer.close();
    }
}
