package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmailAddressValidationCheck {

    public static void emailAddressValidationCheck(String emailAddress) {
        if (!emailAddress.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]+$")) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Niepoprawny adres email");
        }
    }
}
