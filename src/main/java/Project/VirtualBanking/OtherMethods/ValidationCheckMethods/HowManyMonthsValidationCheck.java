package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class HowManyMonthsValidationCheck {

    public static void howManyMonthsValidationCheck(int howManyMonths) {
        if(howManyMonths < 1 || howManyMonths > 12) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nieprawidłowa liczba miesięcy, liczba miesięcy musi być z zakresu od 1 do 12 miesięcy");
        }
    }
}
