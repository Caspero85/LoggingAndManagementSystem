package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.HowManyMonthsValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.TypeOfEducationValidationCheck;
import Project.VirtualBanking.models.dtos.SubscriptionTypeDto;
import Project.VirtualBanking.models.entities.SubscriptionType;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class SubscriptionTypeValidationCheck {

    public static void saveSubscriptionTypeValidationCheck(
            SubscriptionTypeDto subscriptionTypeDto,
            List<SubscriptionType> subscriptionTypes
    ) {
        for (SubscriptionType subscriptionType : subscriptionTypes) {
            if (subscriptionType.isActive()
                    && subscriptionType.getTypeOfEducation().equals(subscriptionTypeDto.getTypeOfEducation())
                    && subscriptionType.getHowManyMonths().equals(subscriptionTypeDto.getHowManyMonths())
            ) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Nie można utworzyć subskrypcji ponieważ istnieje już aktywna subskrypcja o podanym typie edukacji i długości trwania"
                );
            }
        }

        TypeOfEducationValidationCheck.typeOfEducationValidationCheck(subscriptionTypeDto.getTypeOfEducation());
        HowManyMonthsValidationCheck.howManyMonthsValidationCheck(subscriptionTypeDto.getHowManyMonths());
    }

    public static void editSubscriptionTypeValidationCheck(
            SubscriptionTypeDto subscriptionTypeDto,
            List<SubscriptionType> subscriptionTypes,
            SubscriptionType subscriptionType
    ) {
        if (!subscriptionType.isActive()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Nie można edytować subskrypcji, ponieważ, subskrypcja jest nieaktywna"
            );
        }

        for (SubscriptionType subscriptionTypeToCheck : subscriptionTypes) {
            if (!subscriptionTypeToCheck.getSubscriptionTypeId().equals(subscriptionTypeDto.getSubscriptionTypeId())
                    && subscriptionTypeToCheck.isActive()
                    && subscriptionTypeToCheck.getTypeOfEducation().equals(subscriptionTypeDto.getTypeOfEducation())
                    && subscriptionTypeToCheck.getHowManyMonths().equals(subscriptionTypeDto.getHowManyMonths())
            ) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Nie można edytować subskrypcji, ponieważ, istnieje już aktywna subskrypcja o podanym typie edukacji i długości trwania"
                );
            }
        }

        TypeOfEducationValidationCheck.typeOfEducationValidationCheck(subscriptionTypeDto.getTypeOfEducation());
        HowManyMonthsValidationCheck.howManyMonthsValidationCheck(subscriptionTypeDto.getHowManyMonths());
    }
}
