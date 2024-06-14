package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TypeOfEducationValidationCheck {

    public static void typeOfEducationValidationCheck(String typeOfEducation) {
        if(!typeOfEducation.matches("^[\\p{L} .-]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowa nazwa typu edukacji");
        }
    }
}
