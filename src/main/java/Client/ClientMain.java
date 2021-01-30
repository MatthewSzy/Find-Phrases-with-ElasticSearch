package Client;

import Client.FormsSystem.FormsSystemThread;

import java.io.IOException;
import java.net.Socket;

public class ClientMain {
    private static Socket clientSocket;

    public static void main(String[] args) {
        try {
            clientSocket = new Socket("localhost", 5100);
            Thread loginToTheServerThread = new FormsSystemThread(clientSocket);
            loginToTheServerThread.start();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
