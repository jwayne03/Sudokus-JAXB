package model.history;

import model.sudoku.Sudoku;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(
        name = "",
        propOrder = {
                "username",
                "time",
                "sudoku",
                "problem",
                "solved"
        })

public class History {
    @XmlElement(required = true)
    protected String username;

    protected int time;

    @XmlAttribute(name = "id")
    protected int id;

    @XmlAttribute(name = "level")
    protected int level;

    @XmlElement(required = true)
    protected Sudoku sudoku;

    @XmlElement(required = true)
    protected String problem;

    @XmlElement(required = true)
    protected String solved;

    @XmlAttribute(name = "description")
    protected String description;

    public History() {
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSolved() {
        return solved;
    }

    public void setSolved(String solved) {
        this.solved = solved;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Sudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public String toString() {
        return "  __________________________\n" +
                "| " + username + " ====> " + time + "s" + " |";
    }
}
