package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the logic for setting up a game server, accepting client connections,
 * and managing them.
 */
public class Server {
    private ServerSocket serverSocket;
    private List<Socket> connectedClients;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        connectedClients = new ArrayList<>();
    }

    public void acceptClients(int numClients) throws IOException {
        for (int i = 0; i < numClients; i++) {
            Socket clientSocket = serverSocket.accept();
            connectedClients.add(clientSocket);
        }
    }

    public List<Socket> getConnectedClients() {
        return connectedClients;
    }

    public void disconnectAllClients() {
        for (Socket client : connectedClients) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        connectedClients.clear();
    }

    public void stopServer() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}
