package model;

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

    public Users createUsers() {
        return new Users();
    }

    public User createUser() {
        return new User();
    }
}
