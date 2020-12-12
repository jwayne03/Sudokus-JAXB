package model.sudoku;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {"problem", "solved"}
)
public class Sudoku {

    @XmlElement(required = true)
    protected String problem;

    @XmlElement(required = true)
    protected String solved;

    @XmlAttribute(name = "level")
    protected Integer level;

    @XmlAttribute(name = "description")
    protected String description;

    @XmlAttribute(name = "id")
    protected int id;

    public Sudoku() {
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSolved() {
        return solved;
    }

    public void setSolved(String solved) {
        this.solved = solved;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
