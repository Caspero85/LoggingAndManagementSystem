package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

public class DateOfBirthValidationCheck {

        public static void eighteenYearValidationCheck(LocalDate dateOfBirth) {
            LocalDate today = LocalDate.now();
            LocalDate eighteenYearsAgo = today.minusYears(18);
            if (dateOfBirth.isAfter(eighteenYearsAgo)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rodzic musi mieć co najmniej 18 lat");
            }
        }
}
