package Main;

import model.history.Histories;
import model.sudoku.Sudoku;
import model.user.User;
import persistence.FileManagement;
import worker.Worker;

import javax.xml.bind.JAXBException;
import java.util.List;

public class Manager implements Runnable {

    private static Manager manager;
    private FileManagement fileManagement;
    private List<User> userList = null;
    private List<Sudoku> sudokuList;
    public Histories histories;
    private User user;

    private Manager() {
        fileManagement = new FileManagement();
//        userList = new ArrayList<>();
        isFileExists();
    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    private void isFileExists() {
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

    @Override
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
                    rankingAverageTime();
                    break;
                case 0:
                    System.out.println("You chose exit,\n Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("You need to introduce an option");
                    break;
            }
        }
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
        fileManagement.saveDataUserXML(userList);
    }

    private void login() {
        String username = Worker.askString("Introduce your username: ");
        String password = Worker.askString("Introduce your password: ");

        checkUserIfExist(username, password);

        if (!checkUserIfExist(username,password)) {
            System.out.println("Username or password are incorrect\n");
        } else {
            userLogged(username);
        }
    }

    private boolean checkUserIfExist(String username, String password) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(password)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void userLogged(String user) {
        boolean exit = false;

        System.out.println("\nHello! " + user);

        while (!exit) {
            userMenuLogged();

            int option = Worker.askInt("Introduce an option:");

            switch (option) {
                case 1:
                    modifyPassword();
                    break;
                case 2:
                    getRandomNotPlayerSudoku();
                    break;
                case 3:
                    addSudokuToHistory();
                    break;
                case 4:
                    getAverageTime();
                    break;
                case 0:
                    System.out.println("You gonna be redirected to the main menu");
                    exit = true;
                    break;
                default:
                    System.out.println("You need to introduce an option");
                    break;
            }
        }
    }

    private void modifyPassword() {
        String password = Worker.askString("Introduce your password: ");
        String newPassword = Worker.askString("Introduce your new password: ");
        String verifyPassword = Worker.askString("Verify your password: ");
    }

    private void getRandomNotPlayerSudoku() {

    }

    private void addSudokuToHistory() {

    }

    private void getAverageTime() {

    }

    private void rankingAverageTime() {

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
}
