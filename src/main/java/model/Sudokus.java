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
        propOrder = {"sudoku"}
)

@XmlRootElement(
        name = "sudokus"
)
public class Sudokus {

    protected List<Sudokus> sudokus;
    private Sudoku sudoku;

    public Sudokus() {

    }

    public List<Sudokus> getSudoku() {
        if (this.sudokus == null) this.sudokus = new ArrayList<Sudokus>();
        return this.sudokus;
    }
}
