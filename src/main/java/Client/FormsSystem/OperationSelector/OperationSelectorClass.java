package Client.FormsSystem.OperationSelector;

import Client.FormsSystem.LoginSystem.LoginSystemClass;
import Client.FormsSystem.RegistrationSystem.RegistrationSystemClass;
import Client.FormsSystem.ReminderSystem.ReminderSystemClass;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class OperationSelectorClass {
    final private Socket clientSocket;
    final private Scanner clientScanner;

    public OperationSelectorClass(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.clientScanner = new Scanner(System.in);
    }

    public String operationSelection() throws IOException { return selection(); }
    private String selection() throws IOException {
        operationDisplay();
        String choice = clientScanner.nextLine();

        if(choice.equals("0")) {
            DataOutputStream dataSender = new DataOutputStream(clientSocket.getOutputStream());
            dataSender.writeUTF("EXIT");
            dataSender.flush();
            return "Exit";
        }
        else if(choice.equals("1")) return loginOperation();
        else if(choice.equals("2")) return registrationOperation();
        else if(choice.equals("3")) return reminderOperation();
        else return "Wrong choice!";
    }

    public String loginOperation() throws IOException { return login(); }
    private String login() throws IOException {
        LoginSystemClass loginSystemClass = new LoginSystemClass(clientSocket, clientScanner);
        return loginSystemClass.getData();
    }

    public String registrationOperation() throws IOException { return registration(); }
    private String registration() throws IOException {
        RegistrationSystemClass registrationSystemClass = new RegistrationSystemClass(clientSocket, clientScanner);
        return registrationSystemClass.getData();
    }

    public String reminderOperation() throws IOException { return reminder(); }
    private String reminder() throws IOException {
        ReminderSystemClass reminderSystemClass = new ReminderSystemClass(clientSocket, clientScanner);
        return reminderSystemClass.getData();
    }

    public void operationDisplay() { display(); }
    private void display() {
        System.out.println("/******************************OPERATION SELECTOR******************************/");
        System.out.print("WHAT YOU WANT TO DO?\n" +
                "   0 - Exit\n" +
                "   1 - Login\n" +
                "   2 - Registration\n" +
                "   3 - Password Reminder\n" +
                "       Enter the number: ");
    }
}
