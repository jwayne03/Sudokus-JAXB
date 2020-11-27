package Main;

public class Manager implements Runnable {

    private static Manager manager;

    private Manager() {

    }

    public static Manager getInstance() {
        if (manager == null) manager = new Manager();
        return manager;
    }

    public void run() {
        System.out.println("Heyy quetal");
    }
}
