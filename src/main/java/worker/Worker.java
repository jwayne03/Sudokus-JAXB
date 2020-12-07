package worker;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author John Wayne Carreon
 */

public class Worker {

    public static String askString(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine();
                if (answer.equals("")) {
                    System.out.println("You must write something");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } while (answer.equals(""));
        return answer;
    }

    public static String askStringToLowerCase(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine().toLowerCase();
                if (answer.equals("")) System.out.println("You must write something");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (answer.equals(""));
        return answer;
    }

    public static String askStringToUpperCase(String message) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            try {
                System.out.println(message);
                answer = read.readLine().toUpperCase();
                if (answer.equals("")) System.out.println("You must write something");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } while (answer.equals(""));
        return answer;
    }

    public static String askString(String message, String a, String b) {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        do {
            answer = askString(message);
            if (!answer.equalsIgnoreCase(a) && !answer.equalsIgnoreCase(b)) {
                System.out.println("ERROR! Write " + a + " or " + b + " please");
            }
        } while (!answer.equalsIgnoreCase(a) && !answer.equalsIgnoreCase(b));
        return answer;
    }

    public static String askString(String message, ArrayList<String> wordsAccepted) {
        String answer;
        boolean isWordOk;
        do {
            for (String word : wordsAccepted) {
                System.out.println(word);
            }
            answer = Worker.askString(message);
            isWordOk = wordIsOk(answer, wordsAccepted);
            if (!isWordOk) {
                System.out.println("Wrong answer!");
            }
        } while (!isWordOk);
        return answer;
    }

    private static boolean wordIsOk(String word, ArrayList<String> wordsAccepted) {
        for (String w : wordsAccepted) {
            if (w.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public static int askInt(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = 0;
        boolean error;
        do {
            try {
                System.out.println(message);
                num = Integer.parseInt(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error input / output.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please, write integer number.");
                error = true;
            }
        } while (error);
        return num;
    }

    public static int askInt(String message, int min, int max) {
        int num;
        do {
            num = askInt(message);
            if (num < min || num > max) {
                System.out.println("Unavailable option. Choose between STARTER(1), MAIN(2), DESSERT(3)");
            }
        } while (num < min || num > max);
        return num;
    }

    public static double askDouble(String message) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double num = 0;
        boolean error;
        do {
            try {
                System.out.println(message);
                num = Double.parseDouble(br.readLine());
                error = false;
            } catch (IOException ex) {
                System.out.println("Error input / output.");
                error = true;
            } catch (NumberFormatException ex) {
                System.out.println("Please, write double number.");
                error = true;
            }
        } while (error);
        return num;
    }

    public static double askDouble(String message, int min, int max) {
        double num;
        do {
            num = askDouble(message);
            if (num < min || num > max) {
                System.out.println("Error, the number must be between " + min + " and " + max);
            }
        } while (num < min || num > max);
        return num;
    }
}
