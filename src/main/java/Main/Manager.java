package Main;

import exception.MyException;
import model.history.Histories;
import model.sudoku.Sudoku;
import model.user.User;
import persistence.FileManagement;
import worker.Worker;

import javax.xml.bind.JAXBException;
import java.util.List;

public class Manager implements Runnable {

    private static Manager manager;
    private List<User> userList;
    private List<Sudoku> sudokuList;
    public Histories histories;
    private User user;

    private Manager() {
        FileManagement fileManagement = new FileManagement();
        try {
            if (!fileManagement.ifExists()) {
                sudokuList = fileManagement.readSudoku();
                fileManagement.saveDataSudokusXML(sudokuList);
            } else {
                sudokuList = fileManagement.loadDataSudoku();
            }
            userList = fileManagement.loadDataUser();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void run() {

        boolean exit = false;

            while (!exit) {
                menu();

                int option = Worker.askInt("Introduce an option: ");

                switch (option) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.println("You need to introduce an option");
                }
            }
    }

    private void menu() {
        System.out.println("-------------------- Welcome to Sudokus --------------------");
        System.out.println("1 - Register user\n"
                + "2 - Login\n"
                + "3 - Ranking average time users\n");
    }

    private void userMenuLogged() {
        System.out.println("---------------------------------------------------------");
        System.out.println("1. Modify your password: ");
        System.out.println("2. Get a random not played sudoku: ");
        System.out.println("3. Add sudoku to history: ");
        System.out.println("4. Get average time: ");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------------------------");
    }

    private void registerUser() {
        User user = new User();

        System.out.println("You have chose to register, here we go.");
        String fullname = Worker.askString("Introduce your fullname");
        String username = Worker.askString("Introduce your username: ");
        String password = Worker.askString("Introduce your password: ");

        user.setFullname(fullname);
        user.setUsername(username);
        user.setPassword(password);
        userList.add(new User(fullname, username, password));
        saveDataXML(userList);
    }

    private void saveDataXML(List<User> userList) {
        FileManagement.saveDataUserXML(userList);
    }

    private void login() {
        String username = Worker.askString("Introduce your username: ");
        String password = Worker.askString("Introduce your password: ");

        userList.forEach(username1 -> {
            if (username1.getUsername().equalsIgnoreCase(username)
                    || username1.getPassword().equals(password)) userLogged();
            else System.err.println("Username or password are incorrect");
        });
    }

    private void userLogged() {
        boolean exit = false;
//        System.out.println("Hello " + user.getUsername() + " you are in Sudoku");


        while (!exit) {
            userMenuLogged();

            int option = Worker.askInt("Introduce an option");

            switch (option) {
                case 1:
                    modifyPassword();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    break;
            }
        }
    }

    private void modifyPassword() {
    }

    private void rankingAverageTime() {

    }

    private void init() {

    }
}
