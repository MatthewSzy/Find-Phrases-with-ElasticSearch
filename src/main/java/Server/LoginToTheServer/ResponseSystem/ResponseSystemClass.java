package Server.LoginToTheServer.ResponseSystem;

import Server.SearchPhrase.SearchPhraseThread;
import org.elasticsearch.client.transport.TransportClient;

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

    public String serverResponse(String resultMessage) throws IOException { return response(resultMessage); }
    private String response(String resultMessage) throws IOException {
        DataOutputStream dataSend = new DataOutputStream(clientSocket.getOutputStream());
        if(resultMessage.equals("Logged in")) {
            dataSend.writeUTF(resultMessage);
            Thread searchPhraseThread = new SearchPhraseThread(clientSocket, loggedUser, client);
            searchPhraseThread.start();
            return "Break";
        }
        else if(resultMessage.equals("Exit")) {
            return "Return";
        }
        else {
            dataSend.writeUTF(resultMessage);
            dataSend.flush();
        }
        return "Error";
    }
}
