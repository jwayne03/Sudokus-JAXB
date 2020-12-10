package model.user;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

//@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class Users {

    protected List<User> users;

    @XmlElement(name = "user")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) { this.users = users; }
}
