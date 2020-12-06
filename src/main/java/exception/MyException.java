package exception;

import java.util.Arrays;
import java.util.List;

public class MyException extends Exception {

    public static final int WRONG_USER_OR_PASSWORD = 0;

    private int value;

    public MyException(int value) {
        this.value = value;
    }

    private List<String> message = Arrays.asList(
            "The username or password are incorrect"
    );

    @Override
    public String getMessage() {
        return message.get(value);
    }
}
