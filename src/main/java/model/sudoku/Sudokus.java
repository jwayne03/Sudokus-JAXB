package model.sudoku;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "sudokus")
public class Sudokus {

    protected List<Sudoku> sudokus;
    public Sudokus() { }
    public List<Sudoku> getSudokus() { return sudokus; }
    public void setSudokus(List<Sudoku> sudokus) { this.sudokus = sudokus; }
}
