package app.planningApp.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class Validator {

    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$");

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private static final Pattern CONTAINS_ONLY_CHARACTERS =
            Pattern.compile("^[a-zA-Z]{2,}$");

    public static boolean isPasswordValid(String password){
        return VALID_PASSWORD_REGEX.matcher(password).find();
    }

    public static boolean containsOnlyLetters(String word){
        return CONTAINS_ONLY_CHARACTERS.matcher(word).find();
    }

    public static boolean isEmailValid(String email) {
        return VALID_EMAIL_ADDRESS_REGEX.matcher(email).find();
    }
}
