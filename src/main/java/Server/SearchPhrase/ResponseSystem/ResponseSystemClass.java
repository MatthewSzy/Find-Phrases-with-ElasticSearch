package Server.SearchPhrase.ResponseSystem;

import Server.LoginToTheServer.LoginToTheServerThread;
import Server.SearchPhrase.SearchSystem.SearchSystemClass;
import org.elasticsearch.client.transport.TransportClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ResponseSystemClass {
    final private Socket clientSocket;
    final private List<String> loggedUser;
    final private TransportClient client;

    public ResponseSystemClass(Socket clientSocket, List<String> loggedUser, TransportClient client) {
        this.clientSocket = clientSocket;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    public String serverResponse(String actionType) throws IOException { return response(actionType); }
    private String response(String actionType) throws IOException {
        if(actionType.equals("EXIT")) {
            DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
            String userLogin = dataReceiver.readUTF();
            loggedUser.remove(userLogin);
            return "Exit";
        }
        else if(actionType.equals("LOGOUT")) {
            DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
            String userLogin = dataReceiver.readUTF();
            loggedUser.remove(userLogin);
            System.out.println(loggedUser);
            Thread loginToTheServerThread = new LoginToTheServerThread(clientSocket,loggedUser, client);
            loginToTheServerThread.start();
            return "Return";
        }
        else if(actionType.equals("SEARCH")) {
            SearchSystemClass searchSystemClass = new SearchSystemClass(clientSocket, client);
            searchSystemClass.searchPhrase();
        }
        return "Error";
    }
}
