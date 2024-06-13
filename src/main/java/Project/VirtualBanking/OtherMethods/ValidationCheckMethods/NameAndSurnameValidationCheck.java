package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NameAndSurnameValidationCheck {

    public static void nameValidationCheck(String nameAndSurname) {
        if (!nameAndSurname.matches("^[\\p{L} .'-]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowe imię");
        }
    }

    public static void surnameValidationCheck(String nameAndSurname) {
        if (!nameAndSurname.matches("^[\\p{L} .'-]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowe nazwisko");
        }
    }
}
