package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.*;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.entities.Parent;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class ParentValidationCheck {

    public static void saveParentValidationCheck(ParentDto parentDto, List<Parent> parents) {
        for (Parent parentToCheck : parents) {
            if (parentToCheck.getEmailAddress().equals(parentDto.getEmailAddress())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany adres e-mail jest już zajęty");
            }
            if (parentToCheck.getPhoneNumber().equals(parentDto.getPhoneNumber())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podany numer telefonu jest już zajęty");
            }
        }

        NameAndSurnameValidationCheck.nameValidationCheck(parentDto.getName());
        NameAndSurnameValidationCheck.surnameValidationCheck(parentDto.getSurname());
        EmailAddressValidationCheck.emailAddressValidationCheck(parentDto.getEmailAddress());
        PhoneNumberValidationCheck.phoneNumberValidationCheck(parentDto.getPhoneNumber());
        PasswordValidationCheck.passwordValidationCheck(parentDto.getPassword());
        DateOfBirthValidationCheck.dateOfBirthValidationCheck(parentDto.getDateOfBirth());
    }

    public static void editParentValidationCheck(ParentDto parentDto, List<Parent> parents, Parent parent) {
        if(!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }

        saveParentValidationCheck(parentDto, parents);
    }
}
