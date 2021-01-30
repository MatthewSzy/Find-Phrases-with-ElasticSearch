package Client.FormsSystem.ReminderSystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ReminderSystemClass {
    final private Socket clientSocket;
    final private Scanner clientScanner;

    public ReminderSystemClass(Socket clientSocket, Scanner clientScanner) {
        this.clientSocket = clientSocket;
        this.clientScanner = clientScanner;
    }

    public String getData() throws IOException { return get();}
    private String get() throws IOException {
        while(true) {
            System.out.println("/******************************PASSWORD REMIND******************************/");
            System.out.print("Enter Login(0 - Back): ");
            String userLogin = clientScanner.nextLine();
            if(userLogin.equals("0")) return "Back";

            String returnMessage = sendData(userLogin);
            if(returnMessage.equals("This login does not exist")) {
                System.out.println(returnMessage);
            }
            else return "Your Password: " + returnMessage;
        }
    }

    public String sendData(String userLogin) throws IOException { return send(userLogin); }
    private String send(String userLogin) throws IOException {
        DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
        dataSender.writeUTF("REMIND");
        dataSender.writeUTF(userLogin);
        dataSender.flush();

        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String returnMessage = dataReceiver.readUTF();
        return returnMessage;
    }
}
