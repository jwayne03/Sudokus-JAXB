package exception;

import java.util.Arrays;
import java.util.List;

public class MyException extends Exception {

    public static final int WRONG_USER_OR_PASSWORD = 0;
    public static final int WRONG_OPTION = 1;
    public static final int WRONG_PASSWORD = 2;
    public static final int PASSWORD_CANT_BE_SAME_AS_OLD = 3;

    private int value;

    public MyException(int value) {
        this.value = value;
    }

    private List<String> message = Arrays.asList(
            "The username or password are incorrect",
            "You need to introduce an option",
            "Incorrect password",
            "Your new password can't be the same as the old"
    );

    @Override
    public String getMessage() {
        return message.get(value);
    }
}
