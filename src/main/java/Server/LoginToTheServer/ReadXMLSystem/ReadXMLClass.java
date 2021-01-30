package Server.LoginToTheServer.ReadXMLSystem;

import Resource.UserInfo;
import Resource.UsersInfo;
import Server.LoginToTheServer.WriteXMLSystem.WriteXMLClass;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadXMLClass {
    private final String filePath = "src/main/java/Resource/UsersBase.xml";

    public ReadXMLClass(){}

    public UsersInfo readUsersData() throws JAXBException { return readUser(); }
    private UsersInfo readUser() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersInfo.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        UsersInfo usersInfo = (UsersInfo) unmarshaller.unmarshal(new File(filePath));
        return usersInfo;
    }
}
