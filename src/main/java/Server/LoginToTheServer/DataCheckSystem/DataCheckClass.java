package Server.LoginToTheServer.DataCheckSystem;

import Server.LoginToTheServer.LoginSystem.LoginSystemClass;
import Server.LoginToTheServer.RegistrationSystem.RegistrationSystemClass;
import Server.LoginToTheServer.ReminderSystem.ReminderSystemClass;

import javax.xml.bind.JAXBException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class DataCheckClass {
    private final Socket clientSocket;
    private final List<String> loggedUser;
    public DataCheckClass(Socket clientSocket, List<String> loggedUser) {
        this.clientSocket = clientSocket;
        this.loggedUser = loggedUser;
    }

    public String checkData() throws IOException, JAXBException { return check(); }
    private String check() throws IOException, JAXBException {
        DataInputStream dataReceive = new DataInputStream(clientSocket.getInputStream());
        String actionType = dataReceive.readUTF();
        if(actionType.equals("LOGIN")) return loginDataCheck();
        else if(actionType.equals("REGISTRATION")) return registerDataCheck();
        else if(actionType.equals("REMIND")) return remindDataCheck();
        else if(actionType.equals("EXIT")) return "Exit";
        return "Error";
    }

    public String loginDataCheck() throws IOException, JAXBException { return loginCheck(); }
    private String loginCheck() throws IOException, JAXBException {
        LoginSystemClass loginSystemClass = new LoginSystemClass(clientSocket, loggedUser);
        return loginSystemClass.getData();
    }

    public String registerDataCheck() throws JAXBException, IOException { return registerCheck(); }
    private String registerCheck() throws JAXBException, IOException {
        RegistrationSystemClass registrationSystemClass = new RegistrationSystemClass(clientSocket);
        return registrationSystemClass.getData();
    }

    public String remindDataCheck() throws IOException, JAXBException { return remindCheck(); }
    private String remindCheck() throws IOException, JAXBException {
        ReminderSystemClass reminderSystemClass = new ReminderSystemClass(clientSocket);
        return reminderSystemClass.getData();
    }
}
