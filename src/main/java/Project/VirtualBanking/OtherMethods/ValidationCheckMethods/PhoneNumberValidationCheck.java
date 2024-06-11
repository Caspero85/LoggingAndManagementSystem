package Project.VirtualBanking.OtherMethods.ValidationCheckMethods;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class PhoneNumberValidationCheck {

    public static void phoneNumberValidationCheck(String phoneNumber) {
        if (!phoneNumber.matches("\\d{9}")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numer telefonu musi składać się z 9 cyfr");
        }
    }
}
