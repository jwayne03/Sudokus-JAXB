package Main;

import exception.MyException;
import model.history.History;
import model.sudoku.Sudoku;
import model.user.User;
import persistence.FileManagement;
import worker.Worker;

import javax.xml.bind.JAXBException;
import java.util.*;

public class Manager implements Runnable {

    private static Manager manager;
    private FileManagement fileManagement;
    private Worker worker;
    private List<User> userList = null;
    private List<Sudoku> sudokuList;
    private List<History> historyList;

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
                userList = fileManagement.loadDataUser();
                historyList = fileManagement.loadDataHistory();
            }
            userList = fileManagement.loadDataUser();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
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

    public void registerUser() {
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
        try {
            String username = worker.askString("Introduce your username: ");
            String password = worker.askString("Introduce your password: ");

            checkUserIfExist(username, password);

            if (!checkUserIfExist(username, password)) {
                throw new MyException(MyException.WRONG_USER_OR_PASSWORD);
            } else {
                userLogged(username);
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
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

    private void userLogged(String username) {
        boolean exit = false;

        System.out.println("\nHello! " + username);

        try {
            while (!exit) {
                userMenuLogged();

                int option = worker.askInt("Introduce an option:");

                switch (option) {
                    case 1:
                        modifyPassword(username);
                        break;
                    case 2:
                        getRandomNotPlayerSudoku(username);
                        break;
                    case 3:
                        getAverageTime(username);
                        break;
                    case 0:
                        System.out.println("You gonna be redirected to the main menu");
                        exit = true;
                        break;
                    default:
                        throw new MyException(MyException.WRONG_OPTION);
                }
            }
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }

    private void modifyPassword(String username) {
        try {
            String oldPassword = worker.askString("Introduce your password: ");
            if (!checkOldPassword(username, oldPassword)) {
                throw new MyException(MyException.WRONG_PASSWORD);
            } else {
                String newPassword = worker.askString("Introduce your new password: ");
                String verifyPassword = worker.askString("Verify your password: ");

                if (oldPassword.equals(newPassword) && oldPassword.equalsIgnoreCase(newPassword)) {
                    throw new MyException(MyException.PASSWORD_CANT_BE_SAME_AS_OLD);
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
        } catch (MyException e) {
            System.out.println(e.getMessage());
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
                System.out.println("2 - Back to menu");

                int option = worker.askInt("Have you finished the sudoku?");

                if (option == 1) {
                    addSudokuToHistory(username, rand);
                    exit = true;
                }

                if (option == 2) {
                    exit = true;
                }

                if (option != 1 && option != 2) {
                    System.out.println("You need to introduce [1 - Yes], [2 - Back to menu]");
                }
            }
        } else {
            System.out.println("You already played this sudoku");
        }
    }

    private boolean isSudokuPlayed(String username) {
        for (History history : historyList) {
            if (history.getUsername().equalsIgnoreCase(username)) {
                for (Sudoku sudoku : sudokuList) {
                    if (history.getId() != sudoku.getId()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void addSudokuToHistory(String username, int rand) {
        History history = new History();
        int min = worker.askInt("Minutes: ", 0, (int) Double.MAX_VALUE);
        int sec = worker.askInt("Seconds: ", 0, (int) Double.MAX_VALUE);
        int time = min * 60 + sec;

        history.setId(sudokuList.get(rand).getId());
        history.setUsername(username);
        history.setTime(time);
        history.setLevel(sudokuList.get(rand).getLevel());
        history.setDescription(sudokuList.get(rand).getDescription());
        history.setProblem(sudokuList.get(rand).getProblem());
        history.setSolved(sudokuList.get(rand).getSolved());

        historyList.add(history);
        fileManagement.saveDataHistoryXML(historyList);

        System.out.println("You resolved the problem in " + min + " minutes and " + sec + " seconds");
    }

    private void getAverageTime(String username) {
        System.out.println("Your average time is: " + average(username) + " seconds");
    }

    private double average(String username) {
        int count = 0;
        double average = 0;
        for (History history : historyList) {
            if (username.equalsIgnoreCase(history.getUsername())) {
                average += history.getTime();
                count++;
            }
        }

        return average / count;
    }

    private void rankingAverageTime() {
        Comparator<History> comparator = Comparator.comparing(History::getTime);
        System.out.println("Top ranking of Sudoku");
        historyList.stream().sorted(comparator).forEach(System.out::println);
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
        System.out.println("3. Get average time: ");
        System.out.println("0. Exit");
        System.out.println("---------------------------------------------------------");
    }
}
