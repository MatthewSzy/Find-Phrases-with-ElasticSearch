package Client.FormsSystem;

import Client.FormsSystem.OperationSelector.OperationSelectorClass;

import java.io.IOException;
import java.net.Socket;

public class FormsSystemThread extends Thread {
    final private Socket clientSocket;

    public FormsSystemThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            OperationSelectorClass operationSelectorClass = new OperationSelectorClass(clientSocket);

            while(true) {
                String returnMessage = operationSelectorClass.operationSelection();

                if(returnMessage.equals("Logged in")) break;
                else if(returnMessage.equals("Exit")) return;
                else if(!returnMessage.equals("Back")) System.out.println(returnMessage);
            }
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
