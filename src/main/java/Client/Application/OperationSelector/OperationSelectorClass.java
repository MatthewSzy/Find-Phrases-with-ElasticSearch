package Client.Application.OperationSelector;

import Client.Application.LogoutSystem.LogoutSystemClass;
import Client.Application.SearchSystem.SearchSystemClass;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class OperationSelectorClass {
    final private Socket clientSocket;
    final private Scanner clientScanner;
    final private String userLogin;

    public OperationSelectorClass(Socket clientSocket, String userLogin) {
        this.clientSocket = clientSocket;
        this.clientScanner = new Scanner(System.in);
        this.userLogin = userLogin;
    }

    public String operationSelection() throws IOException { return selection(); }
    private String selection() throws IOException {
        operationDisplay();
        String choice = clientScanner.nextLine();

        if(choice.equals("0")) {
            DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
            dataSender.writeUTF("EXIT");
            dataSender.writeUTF(userLogin);
            dataSender.flush();
            return "Exit";
        }
        else if(choice.equals("1")) return logoutOperation();
        else if(choice.equals("2")) return searchOperation();
        else return "Wrong choice!";
    }

    public String logoutOperation() throws IOException { return logout(); }
    private String logout() throws IOException {
        LogoutSystemClass logoutSystemClass = new LogoutSystemClass(clientSocket, userLogin);
        return logoutSystemClass.sendData();
    }

    public String searchOperation() throws IOException { return search(); }
    private String search() throws IOException {
        SearchSystemClass searchSystemClass = new SearchSystemClass(clientSocket, clientScanner);
        return searchSystemClass.getData();
    }

    public void operationDisplay() { display(); }
    private void display() {
        System.out.println("/******************************OPERATION SELECTOR******************************/");
        System.out.print("WHAT YOU WANT TO DO?\n" +
                "   0 - Exit\n" +
                "   1 - Logout\n" +
                "   2 - Search Phrase\n" +
                "       Enter the number: ");
    }
}
