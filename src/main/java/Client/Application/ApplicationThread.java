package Client.Application;

import Client.Application.OperationSelector.OperationSelectorClass;
import Client.FormsSystem.FormsSystemThread;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ForkJoinWorkerThread;

public class ApplicationThread extends Thread {
    final private Socket clientSocket;
    final private String userLogin;

    public ApplicationThread(Socket clientSocket, String userLogin) {
        this.clientSocket = clientSocket;
        this.userLogin = userLogin;
    }

    @Override
    public void run() {
        try {
            System.out.println("Login successful!");
            OperationSelectorClass operationSelectorClass = new OperationSelectorClass(clientSocket, userLogin);

            while(true) {
                String returnMessage = operationSelectorClass.operationSelection();

                if(returnMessage.equals("Exit")) return;
                else if(returnMessage.equals("Logout")) {
                    Thread formsSystemThread = new FormsSystemThread(clientSocket);
                    formsSystemThread.start();
                    break;
                }
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
