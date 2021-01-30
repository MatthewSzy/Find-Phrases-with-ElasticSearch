package Server.LoginToTheServer.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidator {
    private LoginValidator() {}
    public static boolean checkLoginIsValid(String login) {

        String loginRegex = "^(?=.{5,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";
        Pattern pattern = Pattern.compile(loginRegex);
        Matcher matcher = pattern.matcher(login);

        return matcher.matches();
    }
}
