package Main;

import model.history.Histories;
import model.sudoku.Sudokus;
import model.user.Users;
import persistence.FileManagement;

import java.io.File;

public class Manager implements Runnable {

    private static Manager manager;
    private Sudokus sudokus;
    private Users users;
    public Histories histories;

    private Manager() {

    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void run() {
        sudokus = FileManagement.readSudokusXML();
        users = FileManagement.readUsersXML();
        histories = FileManagement.readHistoriesXML();
    }

    private void init() {

    }
}
