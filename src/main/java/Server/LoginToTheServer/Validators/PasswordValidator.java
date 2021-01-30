package Server.LoginToTheServer.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private PasswordValidator() {}
    public static boolean checkPasswordIsValid(String password) {

        String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}
