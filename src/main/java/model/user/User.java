package model.user;

import javax.xml.bind.annotation.*;

@XmlRootElement(
        name = "user"
)
@XmlType(
        propOrder = {"fullname", "username", "password"}
)
public class User {

    protected String fullname;
    protected String username;
    protected String password;

    public User() {

    }

    public User(String fullname, String username, String password) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
