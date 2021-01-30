package Resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {
        "login",
        "password"
})
public class UserInfo {
    @XmlElement
    private String login;

    @XmlElement
    private String password;

    public UserInfo() {}

    public UserInfo(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String toString() { return addToString(); }
    private String addToString() {
        return getLogin() + " " + getPassword();
    }
}
