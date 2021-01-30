package Server.LoginToTheServer.ReminderSystem;

import Resource.UserInfo;
import Resource.UsersInfo;
import Server.LoginToTheServer.ReadXMLSystem.ReadXMLClass;

import javax.xml.bind.JAXBException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class ReminderSystemClass {
    private final Socket clientSocket;

    public ReminderSystemClass(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String getData() throws IOException, JAXBException { return get();}
    private String get() throws IOException, JAXBException {
        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String userLogin = dataReceiver.readUTF();

        ReadXMLClass readXMLClass = new ReadXMLClass();
        UsersInfo usersInfo = readXMLClass.readUsersData();

        return checkData(userLogin, usersInfo);
    }

    public String checkData(String userLogin, UsersInfo usersInfo) { return check(userLogin, usersInfo); }
    private String check(String userLogin, UsersInfo usersInfo) {
        List<UserInfo> usersInfoList = usersInfo.getUsersInfo();
        for(UserInfo x : usersInfoList) {
            if(x.getLogin().equals(userLogin)) {
                String receiveMessage = "";
                for(int i = 0; i < x.getPassword().length(); i++) {
                    if(i % 2 == 0) receiveMessage = receiveMessage + x.getPassword().charAt(i);
                    else receiveMessage = receiveMessage + "*";
                }
                return receiveMessage;
            }
        }
        return "This login does not exist";
    }
}
