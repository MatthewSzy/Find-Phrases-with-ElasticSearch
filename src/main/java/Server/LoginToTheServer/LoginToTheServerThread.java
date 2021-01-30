package Server.LoginToTheServer;

import Server.LoginToTheServer.DataCheckSystem.DataCheckClass;
import Server.LoginToTheServer.ResponseSystem.ResponseSystemClass;
import org.elasticsearch.client.transport.TransportClient;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class LoginToTheServerThread extends Thread {
    final private Socket clientSocket;
    final private List<String> loggedUser;
    final private TransportClient client;

    public LoginToTheServerThread(Socket clientSocket, List<String> loggedUser, TransportClient client) {
        this.clientSocket = clientSocket;
        this.loggedUser = loggedUser;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                DataCheckClass checkClass = new DataCheckClass(clientSocket, loggedUser);
                String resultMessage = checkClass.checkData();

                ResponseSystemClass responseSystemClass = new ResponseSystemClass(clientSocket, loggedUser, client);
                resultMessage = responseSystemClass.serverResponse(resultMessage);

                if(resultMessage.equals("Break")) break;
                else if(resultMessage.equals("Return")) return;
            }

        }
        catch(IOException | JAXBException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
