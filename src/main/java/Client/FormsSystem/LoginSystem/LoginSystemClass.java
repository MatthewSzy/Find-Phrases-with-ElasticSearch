package Client.FormsSystem.LoginSystem;

import Client.Application.ApplicationThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class LoginSystemClass {
    final private Socket clientSocket;
    final private Scanner clientScanner;

    public LoginSystemClass(Socket clientSocket, Scanner clientScanner) {
        this.clientSocket = clientSocket;
        this.clientScanner = clientScanner;
    }

    public String getData() throws IOException { return get();}
    private String get() throws IOException {
        while(true) {
            System.out.println("/******************************LOGIN******************************/");
            System.out.print("Enter Login(0 - Back): ");
            String userLogin = clientScanner.nextLine();
            if(userLogin.equals("0")) return "Back";

            System.out.print("Enter Password(0 - Back): ");
            String userPassword = clientScanner.nextLine();
            if(userPassword.equals("0")) return "Back";

            String returnMessage = sendData(userLogin, userPassword);
            if(returnMessage.equals("Logged in")) {
                Thread applicationThread = new ApplicationThread(clientSocket, userLogin);
                applicationThread.start();
                return "Logged in";
            }
            else {
                System.out.println(returnMessage);
            }
        }
    }

    public String sendData(String userLogin, String userPassword) throws IOException { return send(userLogin, userPassword); }
    private String send(String userLogin, String userPassword) throws IOException {
        DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
        dataSender.writeUTF("LOGIN");
        dataSender.writeUTF(userLogin);
        dataSender.writeUTF(userPassword);
        dataSender.flush();

        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String returnMessage = dataReceiver.readUTF();
        return returnMessage;
    }
}
