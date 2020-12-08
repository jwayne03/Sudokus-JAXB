package Main;

import model.history.History;
import model.sudoku.Sudoku;
import model.user.User;
import persistence.FileManagement;
import worker.Worker;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Manager implements Runnable {

    private static Manager manager;
    private FileManagement fileManagement;
    private Worker worker;
    private List<User> userList = null;
    private List<Sudoku> sudokuList;
    private List<History> histories = null;

    private Manager() {
        fileManagement = new FileManagement();
        worker = new Worker();
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

            int option = worker.askInt("Introduce an option: ");

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
        String fullname = worker.askString("Introduce your fullname");
        String username = worker.askString("Introduce your username: ");
        String password = worker.askString("Introduce your password: ");

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
        String username = worker.askString("Introduce your username: ");
        String password = worker.askString("Introduce your password: ");

        checkUserIfExist(username, password);

        if (!checkUserIfExist(username, password)) {
            System.out.println("Username or password are incorrect\n");
        } else {
            userLogged(username, password);
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

    private void userLogged(String username, String password) {
        boolean exit = false;

        System.out.println("\nHello! " + username);

        while (!exit) {
            userMenuLogged();

            int option = worker.askInt("Introduce an option:");

            switch (option) {
                case 1:
                    modifyPassword(username, password);
                    break;
                case 2:
                    getRandomNotPlayerSudoku(username);
                    break;
                case 3:
                    addSudokuToHistory(username, 0);
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

    private void modifyPassword(String username, String password) {
        String oldPassword = worker.askString("Introduce your password: ");
        if (!checkOldPassword(username, oldPassword)) {
            System.out.println("Incorrect password");
        } else {
            String newPassword = worker.askString("Introduce your new password: ");
            String verifyPassword = worker.askString("Verify your password: ");

            if (oldPassword.equals(newPassword) && oldPassword.equalsIgnoreCase(newPassword)) {
                System.out.println("Your new password can't be the same as the old");
            }

            if (newPassword.equals(verifyPassword)) {
                for (User user : userList) {
                    if (user.getUsername().equalsIgnoreCase(username)) {
                        user.setPassword(newPassword);
                        fileManagement.saveDataUserXML(userList);
                        System.out.println("Your password has been changed successfully");
                    }
                }
            }
        }
    }

    private boolean checkOldPassword(String username, String oldPassword) {
        for (User user : userList) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPassword().equals(oldPassword)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void getRandomNotPlayerSudoku(String username) {
        Random random = new Random();
        int rand = random.nextInt(100) + 1;

        if (isSudokuPlayed(username)) {

            boolean exit = false;

            while (!exit) {

                System.out.println(sudokuList.get(rand).getId());
                System.out.println(sudokuList.get(rand).getProblem());

                System.out.println("1 - Yes");

                int option = worker.askInt("Have you finished the sudoku?");

                if (option == 1) {
                    addSudokuToHistory(username, rand);
                }

                if (option != 1) System.out.println("You need to introduce [1 - Yes]");
            }
        } else {
            System.out.println("You already played this sudoku");
        }
    }

    private boolean isSudokuPlayed(String username) {
        for (History history : histories) {
            if (history.getUsername().equalsIgnoreCase(username)) {
                for (Sudoku sudoku : sudokuList) {
                    if (history.getId() != sudoku.getId()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void addSudokuToHistory(String username, int rand) {
        History history = new History();
        int min = worker.askInt("Minutes: ", 0, (int) Double.MAX_VALUE);
        int sec = worker.askInt("Seconds: ", 0, (int) Double.MAX_VALUE);
        int time = min * 60 + sec;

        history.setUsername(username);
        history.setTime(time);
        history.setLevel(rand);
        history.setDescription(sudokuList.get(rand).getDescription());
        history.setProblem(sudokuList.get(rand).getProblem());
        history.setSolved(sudokuList.get(rand).getSolved());

        fileManagement.saveDataHistoryXML(histories);
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
