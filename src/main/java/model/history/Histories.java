package model.history;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"history"}
)
@XmlRootElement(
        name = "histories"
)
public class Histories {

    protected List<Histories> history;

    public Histories() {

    }

    public List<Histories> getHistory() {
        if (this.history == null) this.history = new ArrayList<Histories>();
        return this.history;
    }
}
