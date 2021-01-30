package Server.LoginToTheServer.RegistrationSystem;

import Resource.UserInfo;
import Resource.UsersInfo;
import Server.LoginToTheServer.ReadXMLSystem.ReadXMLClass;
import Server.LoginToTheServer.Validators.LoginValidator;
import Server.LoginToTheServer.Validators.PasswordValidator;
import Server.LoginToTheServer.WriteXMLSystem.WriteXMLClass;

import javax.xml.bind.JAXBException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class RegistrationSystemClass {
    private final Socket clientSocket;

    public RegistrationSystemClass(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public String getData() throws JAXBException, IOException { return get();}
    private String get() throws JAXBException, IOException {
        DataInputStream dataReceiver = new DataInputStream(clientSocket.getInputStream());
        String userLogin = dataReceiver.readUTF();
        String userPassword = dataReceiver.readUTF();
        String userRepeatPassword = dataReceiver.readUTF();

        ReadXMLClass readXMLClass = new ReadXMLClass();
        UsersInfo usersInfo = readXMLClass.readUsersData();

        return checkData(userLogin, userPassword, userRepeatPassword, usersInfo);
    }

    public String checkData(String userLogin, String userPassword, String userRepeatPassword, UsersInfo usersInfo) throws JAXBException { return check(userLogin, userPassword, userRepeatPassword, usersInfo); }
    private String check(String userLogin, String userPassword, String userRepeatPassword, UsersInfo usersInfo) throws JAXBException {
        List<UserInfo> usersInfoList = usersInfo.getUsersInfo();
        for(UserInfo x : usersInfoList) {
            if(x.getLogin().equals(userLogin)) {
                return "This Login already exists";
            }
        }

        if(!LoginValidator.checkLoginIsValid(userLogin)) return "Login is invalid.\nIt must consist of only uppercase and lowercase letter, a number and its length cannot be shorter than 3 characters";
        else if(!userPassword.equals(userRepeatPassword)) return "The passwords must be identical";
        else if(!PasswordValidator.checkPasswordIsValid(userPassword)) return "The password is incorrect.\nIt must consist of at least one uppercase and lowercase letter, a number and a special character, and its length cannot be shorter than 8 characters";
        else {
            usersInfoList.add(new UserInfo(userLogin, userPassword));
            usersInfo.setUsersInfo(usersInfoList);
            WriteXMLClass writeXMLClass = new WriteXMLClass();
            writeXMLClass.writeUserData(usersInfo);
            return "User registered";
        }
    }
}
