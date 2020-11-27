package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"user"}
)
@XmlRootElement(
        name = "users"
)
public class Users {

    protected List<Users> users;
    private User user;

    public Users() {

    }

    public List<Users> getUsers() {
        if (this.users == null) this.users = new ArrayList<Users>();
        return this.users;
    }
}
