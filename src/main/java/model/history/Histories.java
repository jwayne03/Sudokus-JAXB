package model.history;


import model.user.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"history"})
@XmlRootElement(name = "histories")
public class Histories {

    protected List<History> history;
    public Histories() { }
    public List<History> getHistory() { return history; }
    public void setHistory(List<History> history) { this.history = history; }
}
