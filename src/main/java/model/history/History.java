package model.history;

import model.sudoku.Sudoku;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
        name = "",
        propOrder = {
                "username",
                "time",
                "sudoku"
        }
)
public class History {
    @XmlElement(
            required = true
    )
    protected String username;
    protected int time;

    @XmlElement(
            required = true
    )
    protected Sudoku sudoku;

    public History() {

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
}
