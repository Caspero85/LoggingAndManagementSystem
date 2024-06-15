package Project.VirtualBanking.services;

import Project.VirtualBanking.OtherMethods.EntityValidationCheck.SubscriptionValidationCheck;
import Project.VirtualBanking.models.dtos.ChildDto;
import Project.VirtualBanking.models.dtos.SubscriptionDto;
import Project.VirtualBanking.models.dtos.SubscriptionTypeDto;
import Project.VirtualBanking.models.entities.*;
import Project.VirtualBanking.repositories.ChildRepository;
import Project.VirtualBanking.repositories.SubscriptionRepository;
import Project.VirtualBanking.repositories.SubscriptionTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionService {

    SubscriptionRepository subscriptionRepository;
    ChildRepository childRepository;
    SubscriptionTypeRepository subscriptionTypeRepository;
    SubscriptionPaymentService subscriptionPaymentService;

    public SubscriptionService(
            SubscriptionRepository subscriptionRepository,
            ChildRepository childRepository,
            SubscriptionTypeRepository subscriptionTypeRepository,
            SubscriptionPaymentService subscriptionPaymentService
    ) {
        this.subscriptionRepository = subscriptionRepository;
        this.childRepository = childRepository;
        this.subscriptionTypeRepository = subscriptionTypeRepository;
        this.subscriptionPaymentService = subscriptionPaymentService;
    }

    public SubscriptionDto saveSubscription(
            Integer childId, Integer subscriptionTypeId
    ) {
        Child child = childRepository.findById(childId).orElseThrow();
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId).orElse(null);
        PaymentInfo paymentInfo = child.getParent().getPaymentInfo().stream()
                .filter(paymentInfoActive -> paymentInfoActive.isActive()).findFirst().orElse(null);

        SubscriptionValidationCheck.saveSubscriptionValidationCheck(child, paymentInfo, subscriptionType);

        Subscription subscription = subscriptionRepository.save(new Subscription(child, subscriptionType));

        SubscriptionPayment subscriptionPayment = subscriptionPaymentService.saveSubscriptionPayment(
                subscription, paymentInfo
        );

        /**
         * Future implementation of the payment logic
         */
        subscriptionPayment.setPaid(true);

        if(subscriptionPayment.isPaid()) {
            subscription.setActive(true);
            subscription.setRecursive(true);
        }

        return SubscriptionDto.fromEntity(subscriptionRepository.save(subscription));
    }

    public List<SubscriptionDto> findAllSubscriptions() {
        return subscriptionRepository.findAll().stream().map(subscription -> SubscriptionDto.fromEntity(subscription))
                .collect(Collectors.toList());
    }

    public List<SubscriptionDto> findAllActiveSubscriptions() {
        return subscriptionRepository.findAll().stream().filter(subscription -> subscription.isActive())
                .map(subscription -> SubscriptionDto.fromEntity(subscription)).collect(Collectors.toList());
    }

    public List<SubscriptionDto> findAllRecursiveSubscriptions() {
        return subscriptionRepository.findAll().stream().filter(subscription -> subscription.isRecursive())
                .map(subscription -> SubscriptionDto.fromEntity(subscription)).collect(Collectors.toList());
    }

    public SubscriptionDto findSubscriptionById(Integer subscriptionId) {
        return SubscriptionDto.fromEntity(subscriptionRepository.findById(subscriptionId).orElseThrow());
    }

    public SubscriptionDto editSubscriptionDetails(Integer subscriptionId, SubscriptionDto subscriptionDto) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        subscription.setDetails(subscriptionDto.getDetails());
        return SubscriptionDto.fromEntity(subscriptionRepository.save(subscription));
    }

    public SubscriptionDto recursiveOnSubscription(Integer subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElse(null);

        if (subscription == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Podana subskrypcja nie istnieje");
        }

        SubscriptionValidationCheck.recursiveOnSubscriptionValidationCheck(subscription);

        subscription.setRecursive(true);

        return SubscriptionDto.fromEntity(subscriptionRepository.save(subscription));
    }

    public SubscriptionDto recursiveOffSubscription(Integer subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        subscription.setRecursive(false);
        return SubscriptionDto.fromEntity(subscriptionRepository.save(subscription));
    }

    public SubscriptionDto deactivateSubscription(Integer subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();

        subscription.setActive(false);
        subscription.setRecursive(false);

        return SubscriptionDto.fromEntity(subscriptionRepository.save(subscription));
    }

    public void deleteSubscription(Integer subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    /**
     * Child related methods
     */

    public ChildDto findChildBySubscriptionId(Integer subscriptionId) {
        return ChildDto.fromEntity(subscriptionRepository.findById(subscriptionId).orElseThrow().getChild());
    }

    /**
     * SubscriptionType related methods
     */

    public SubscriptionTypeDto findSubscriptionTypeBySubscriptionId(Integer subscriptionId) {
        return SubscriptionTypeDto.fromEntity(subscriptionRepository.findById(subscriptionId).orElseThrow().getSubscriptionType());
    }
}
