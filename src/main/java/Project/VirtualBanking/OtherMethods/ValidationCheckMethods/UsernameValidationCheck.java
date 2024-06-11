package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UsernameValidationCheck {

    public static void usernameValidationCheck(String username) {
        if (username.length() < 5 || username.length() > 20) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nazwa użytkownika musi zawierać od 5 do 20 znaków"
            );
        }

        if (!username.matches("^[\\w-.]+$")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nazwa użytkownika może zawierać tylko litery alfabetu łacińskiego, cyfry, podkreślenia, kropki i myślniki"
            );
        }
    }
}
