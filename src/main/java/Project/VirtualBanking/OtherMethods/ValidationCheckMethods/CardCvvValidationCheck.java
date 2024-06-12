package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CardCvvValidationCheck {

    public static void cardCvvValidationCheck(String cardCvv) {
        if (!cardCvv.matches("^\\d{3}$")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CVV musi składać się z 3 cyfr");
        }
    }
}
