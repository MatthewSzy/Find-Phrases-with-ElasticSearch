package Resource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "usersInfo")
public class UsersInfo {
    @XmlElement(name = "userInfo")
    private List<UserInfo> usersInfo;

    public UsersInfo() {}

    public void setUsersInfo(List<UserInfo> usersInfo) {
        this.usersInfo = usersInfo;
    }

    public List<UserInfo> getUsersInfo() {
        return usersInfo;
    }
}
