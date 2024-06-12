package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardNumberValidationCheck {

    public static void cardNumberValidationCheck(String cardNumber) {
        String cardNumberWithoutSpaces = cardNumber.replaceAll("\\s", "");
        if (!cardNumberWithoutSpaces.matches("^\\d{16}$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numer karty musi składać się z 16 cyfr");
        }
    }
}
