package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardExpirationDateValidationCheck {

    public static void cardExpirationDateValidationCheck(String cardExpirationDate) {
        if (!cardExpirationDate.matches("^\\d{2}/\\d{2}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data ważności karty musi być w formacie MM/RR");
        }
    }
}
