package Client.Application.SearchSystem;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SearchSystemClass {
    final private Socket clientSocket;
    final private Scanner clientScanner;

    public SearchSystemClass(Socket clientSocket, Scanner clientScanner) {
        this.clientSocket = clientSocket;
        this.clientScanner = clientScanner;
    }

    public String getData() throws IOException { return get();}
    private String get() throws IOException {
        while(true) {
            System.out.println("/******************************SEARCH PHRASE******************************/");
            System.out.print("Enter Phrase(0 - Back): ");
            String phrase = clientScanner.nextLine();
            if(phrase.equals("0")) return "Back";

            sendData(phrase);
        }
    }

    public void sendData(String phrase) throws IOException { send(phrase); }
    private void send(String phrase) throws IOException {
        DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
        dataSender.writeUTF("SEARCH");
        dataSender.writeUTF(phrase);
        dataSender.flush();

        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        for(int i = 0; i < 10 ; i++) {
            String title = dataReceiver.readUTF();
            String author = dataReceiver.readUTF();
            System.out.printf("%-2d Title: %-40s Author: %-40s\n",(i+1), title, author);
        }
    }
}
