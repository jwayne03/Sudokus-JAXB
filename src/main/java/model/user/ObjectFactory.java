package model.user;

import model.sudoku.Sudoku;
import model.sudoku.Sudokus;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {

    }

    public Users createUsers() {
        return new Users();
    }

    public User createUser() {
        return new User();
    }
}
