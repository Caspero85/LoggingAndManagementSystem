package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardHolderNameValidationCheck {

    public static void cardHolderNameValidationCheck(String cardHolderName) {
        if (!cardHolderName.matches("^[\\p{L} .'-]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nieprawidłowa nazwa posiadacza karty");
        }
    }
}
