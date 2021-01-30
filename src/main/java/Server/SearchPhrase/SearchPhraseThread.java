package Server.SearchPhrase;

import Server.SearchPhrase.ResponseSystem.ResponseSystemClass;
import org.elasticsearch.client.transport.TransportClient;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class SearchPhraseThread extends Thread {
    final private Socket clientSocket;
    final private List<String> loggedUser;
    final private TransportClient client;

    public SearchPhraseThread(Socket clientSocket, List<String> loggedUser, TransportClient client) {
        this.clientSocket = clientSocket;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                DataInputStream dataReceive = new DataInputStream(clientSocket.getInputStream());
                String actionType = dataReceive.readUTF();

                ResponseSystemClass responseSystemClass = new ResponseSystemClass(clientSocket, loggedUser, client);
                String resultMessage = responseSystemClass.serverResponse(actionType);

                if(resultMessage.equals("Exit")) return;
                else if(resultMessage.equals("Return")) break;
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
