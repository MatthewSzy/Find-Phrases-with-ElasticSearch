package Server.LoginToTheServer.LoginSystem;

import Resource.UserInfo;
import Resource.UsersInfo;
import Server.LoginToTheServer.ReadXMLSystem.ReadXMLClass;

import javax.xml.bind.JAXBException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class LoginSystemClass {
    private final Socket clientSocket;
    private final List<String> loggedUser;

    public LoginSystemClass(Socket clientSocket, List<String> loggedUser) {
        this.clientSocket = clientSocket;
        this.loggedUser = loggedUser;
    }

    public String getData() throws IOException, JAXBException { return get();}
    private String get() throws IOException, JAXBException {
        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String userLogin = dataReceiver.readUTF();
        String userPassword = dataReceiver.readUTF();

        ReadXMLClass readXMLClass = new ReadXMLClass();
        UsersInfo usersInfo = readXMLClass.readUsersData();

        return checkData(userLogin, userPassword, usersInfo);
    }

    public String checkData(String userLogin, String userPassword, UsersInfo usersInfo) { return check(userLogin, userPassword, usersInfo); }
    private String check(String userLogin, String userPassword, UsersInfo usersInfo) {
        List<UserInfo> usersInfoList = usersInfo.getUsersInfo();
        for(UserInfo x : usersInfoList) {
            if(x.getLogin().equals(userLogin)) {
                if(x.getPassword().equals(userPassword)) {
                    for(String y : loggedUser) {
                        if(y.equals(x.getLogin())) return "Someone is already logged in to that account!";
                    }
                    loggedUser.add(userLogin);
                    return "Logged in";
                }
                else return "This password is incorrect";
            }
        }
        return "This login does not exist";
    }
}
