package Server.LoginToTheServer.WriteXMLSystem;

import Resource.UsersInfo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class WriteXMLClass {
    private final String filePath = "src/main/java/Resource/UsersBase.xml";

    public WriteXMLClass(){}

    public void writeUserData(UsersInfo usersInfo) throws JAXBException { writeUser(usersInfo); }
    private void writeUser(UsersInfo usersInfo) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(UsersInfo.class);
        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(usersInfo, new File(filePath));
    }
}
