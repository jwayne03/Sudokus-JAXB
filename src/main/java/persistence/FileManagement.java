package persistence;

import model.history.Histories;
import model.history.History;
import model.sudoku.Sudoku;
import model.sudoku.Sudokus;
import model.user.User;
import model.user.Users;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {

    private static final String SEPARATOR = File.separator;
    private static final String FOLDER_DATA = "data";
    private static final String FILE_SUDOKUS = "sudokus";
    private static final String FILE_USERS = "users";
    private static final String FILE_HISTORY = "history";

    private final Sudokus SUDOKUS;

    public FileManagement() {
        SUDOKUS = new Sudokus();
    }

    public boolean ifExists() {
        File file = new File(route() + FILE_SUDOKUS + ".txt");
        return file.exists();
    }

    public String route() {
        String route = System.getProperty("user.dir") + SEPARATOR + FOLDER_DATA;
        File folder = new File(route);
        if (!folder.exists()) folder.mkdir();
        return route + SEPARATOR;
    }

    public List<Sudoku> readSudoku() {
        File file = new File(route() + FILE_SUDOKUS + ".txt");
        Sudoku sudoku = null;
        List<Sudoku> sudokuList = new ArrayList<>();
        if (file.exists()) {
            try {
                BufferedReader read = new BufferedReader(new FileReader(file));
                String line;
                int count = 1;
                while ((line = read.readLine()) != null) {
                    String[] data = line.split(" ");
                    if (data.length > 1) {
                        sudoku = new Sudoku();
                        sudokuList.add(sudoku);
                        sudoku.setId(count);
                        sudoku.setLevel(Integer.parseInt(data[1]));
                        sudoku.setDescription(data[2]);
                        count++;
                    } else {
                        if (sudoku.getProblem() == null) {
                            sudoku.setProblem(data[0]);
                        } else {
                            sudoku.setSolved(data[0]);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The file doesn't exist");
        }

        return sudokuList;
    }

    public List<Sudoku> loadDataSudoku() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Sudokus.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Sudokus sudoku = (Sudokus) unmarshaller.unmarshal(new File(route() + FILE_SUDOKUS + ".xml"));
        List<Sudoku> sudokus = sudoku.getSudokus();
        return sudokus;
    }

    public List<User> loadDataUser() throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Users users = (Users) unmarshaller.unmarshal(new File(route() + FILE_USERS + ".xml"));
        List<User> userList = users.getUsers();
        return userList;
    }

    public void saveDataSudokusXML(List<Sudoku> sudokuList) {
        try {
            File file = new File(route() + FILE_SUDOKUS + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Sudokus.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Sudokus sudoku = new Sudokus();
            sudoku.setSudokus(sudokuList);
            marshaller.marshal(sudoku, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveDataUserXML(List<User> userList) {
        try {
            File file = new File(route() + FILE_USERS + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Users users = new Users();
            users.setUsers(userList);
            marshaller.marshal(users, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void saveDataHistoryXML(List<History> histories) {
        try {
            File file = new File(route() + FILE_HISTORY + ".xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Histories.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Histories histories1 = new Histories();
            histories1.setHistory(histories);
            marshaller.marshal(histories1, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
