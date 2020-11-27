package model.sudoku;

import model.sudoku.Sudoku;
import model.sudoku.Sudokus;
import model.user.User;
import model.user.Users;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {

    }

    public Sudokus createSudokus() {
        return new Sudokus();
    }

    public Sudoku createSudoku() {
        return new Sudoku();
    }
}
