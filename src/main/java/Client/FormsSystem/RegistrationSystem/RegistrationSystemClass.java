package Client.FormsSystem.RegistrationSystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class RegistrationSystemClass {
    final private Socket clientSocket;
    final private Scanner clientScanner;

    public RegistrationSystemClass(Socket clientSocket, Scanner clientScanner) {
        this.clientSocket = clientSocket;
        this.clientScanner = clientScanner;
    }

    public String getData() throws IOException { return get();}
    private String get() throws IOException {
        while(true) {
            System.out.println("/******************************REGISTRATION******************************/");
            System.out.print("Enter Login(0 - Back): ");
            String userLogin = clientScanner.nextLine();
            if(userLogin.equals("0")) return "Back";

            System.out.print("Enter Password(0 - Back): ");
            String userPassword = clientScanner.nextLine();
            if(userPassword.equals("0")) return "Back";

            System.out.print("Repeat Password(0 - Back): ");
            String userRepeatPassword = clientScanner.nextLine();
            if(userRepeatPassword.equals("0")) return "Back";

            String returnMessage = sendData(userLogin, userPassword, userRepeatPassword);
            if(returnMessage.equals("User registered")) return "Registration successful!";
            else {
                System.out.println(returnMessage);
            }
        }
    }

    public String sendData(String userLogin, String userPassword, String userRepeatPassword) throws IOException { return send(userLogin, userPassword, userRepeatPassword); }
    private String send(String userLogin, String userPassword, String userRepeatPassword) throws IOException {
        DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
        dataSender.writeUTF("REGISTRATION");
        dataSender.writeUTF(userLogin);
        dataSender.writeUTF(userPassword);
        dataSender.writeUTF(userRepeatPassword);
        dataSender.flush();

        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String returnMessage = dataReceiver.readUTF();
        return returnMessage;
    }
}
