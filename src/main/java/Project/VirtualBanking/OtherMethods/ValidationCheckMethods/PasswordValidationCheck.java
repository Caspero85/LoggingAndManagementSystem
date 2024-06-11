package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PasswordValidationCheck {

    public static void passwordValidationCheck(String password) {
        if (password.length() < 16) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Hasło musi mieć co najmniej 16 znaków"
            );
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Hasło musi zawierać co najmniej jedną dużą literę"
            );
        }
        if (!password.matches(".*[a-z].*")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Hasło musi zawierać co najmniej jedną małą literę"
            );
        }
        if (!password.matches(".*[0-9].*")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Hasło musi zawierać co najmniej jedną cyfrę"
            );
        }
        if (!password.matches(".*[!@#$%^&*()].*")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Hasło musi zawierać co najmniej jeden znak specjalny (!@#$%^&*())"
            );
        }
    }
}
