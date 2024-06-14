package Project.VirtualBanking.services;

import Project.VirtualBanking.OtherMethods.EntityValidationCheck.SubscriptionTypeValidationCheck;
import Project.VirtualBanking.models.dtos.SubscriptionTypeDto;
import Project.VirtualBanking.models.entities.SubscriptionType;
import Project.VirtualBanking.repositories.SubscriptionTypeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionTypeService {

    SubscriptionTypeRepository subscriptionTypeRepository;

    public SubscriptionTypeService(SubscriptionTypeRepository subscriptionTypeRepository) {
        this.subscriptionTypeRepository = subscriptionTypeRepository;
    }

    public SubscriptionTypeDto saveSubscriptionType(SubscriptionTypeDto subscriptionTypeDto) {
        List<SubscriptionType> subscriptionTypes = subscriptionTypeRepository.findAll();

        SubscriptionTypeValidationCheck.saveSubscriptionTypeValidationCheck(subscriptionTypeDto, subscriptionTypes);

        return SubscriptionTypeDto.fromEntity(
                subscriptionTypeRepository.save(SubscriptionType.fromDto(subscriptionTypeDto))
        );
    }

    public List<SubscriptionTypeDto> findAllSubscriptionTypes() {
        return subscriptionTypeRepository.findAll().stream()
                .map(subscriptionType -> SubscriptionTypeDto.fromEntity(subscriptionType))
                .collect(Collectors.toList());
    }

    public List<SubscriptionTypeDto> findAllActiveSubscriptionTypes() {
        return subscriptionTypeRepository.findAll().stream()
                .filter(subscriptionType -> subscriptionType.isActive())
                .map(subscriptionType -> SubscriptionTypeDto.fromEntity(subscriptionType))
                .collect(Collectors.toList());
    }

    public SubscriptionTypeDto findSubscriptionTypeById(Integer subscriptionTypeId) {
        return SubscriptionTypeDto.fromEntity(subscriptionTypeRepository.findById(subscriptionTypeId).orElseThrow());
    }

    public SubscriptionTypeDto editSubscriptionTypeDetails(Integer subscriptionTypeId, SubscriptionTypeDto subscriptionTypeDto) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId).orElseThrow();
        subscriptionType.setDetails(subscriptionTypeDto.getDetails());
        return SubscriptionTypeDto.fromEntity(subscriptionTypeRepository.save(subscriptionType));
    }

    public SubscriptionTypeDto deactivateSubscriptionType(Integer subscriptionTypeId) {
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId).orElseThrow();
        subscriptionType.setActive(false);
        return SubscriptionTypeDto.fromEntity(subscriptionTypeRepository.save(subscriptionType));
    }

    public void deleteSubscriptionType(Integer subscriptionTypeId) {
        subscriptionTypeRepository.deleteById(subscriptionTypeId);
    }
}
