package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;

public class CardExpirationDateValidationCheck {

    public static void cardExpirationDateValidationCheck(String cardExpirationDate) {
        if (!cardExpirationDate.matches("^\\d{2}/\\d{2}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data ważności karty musi być w formacie MM/RR");
        }

        String[] expirationDate = cardExpirationDate.split("/");
        int month = Integer.parseInt(expirationDate[0]);
        int year = Integer.parseInt(expirationDate[1]);

        if (month < 1 || month > 12) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny miesiąc ważności karty");
        }
        int currentYear = Year.now().getValue() % 100;
        if (year < currentYear || year > currentYear + 50) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Niepoprawny rok ważności karty");
        }
    }
}
