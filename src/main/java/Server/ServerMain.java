package Server;

import Server.ElasticSearchConnection.ElasticSearchConnectionClass;
import Server.LoginToTheServer.LoginToTheServerThread;
import org.elasticsearch.client.transport.TransportClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ServerMain {
    private static List<String> loggedUser = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try {
            ElasticSearchConnectionClass elasticSearchConnectionClass = new ElasticSearchConnectionClass();
            TransportClient client = elasticSearchConnectionClass.createConnection();

            ServerSocket serverSocket = new ServerSocket(5100);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                Thread loginToTheServerThread = new LoginToTheServerThread(clientSocket, loggedUser, client);
                loginToTheServerThread.start();
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
