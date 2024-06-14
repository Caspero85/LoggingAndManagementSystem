package Project.VirtualBanking.services;

import Project.VirtualBanking.models.dtos.SubscriptionDto;
import Project.VirtualBanking.models.dtos.SubscriptionPaymentDto;
import Project.VirtualBanking.models.entities.*;
import Project.VirtualBanking.repositories.ChildRepository;
import Project.VirtualBanking.repositories.SubscriptionRepository;
import Project.VirtualBanking.repositories.SubscriptionTypeRepository;
import org.springframework.stereotype.Service;

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
            SubscriptionDto subscriptionDto, SubscriptionPaymentDto subscriptionPaymentDto,
            Integer childId, Integer subscriptionTypeId
    ) {
        Child child = childRepository.findById(childId).orElseThrow();
        SubscriptionType subscriptionType = subscriptionTypeRepository.findById(subscriptionTypeId).orElseThrow();
        PaymentInfo paymentInfo = child.getParent().getPaymentInfo().stream()
                .filter(paymentInfoActive -> paymentInfoActive.isActive()).findFirst().orElseThrow();

        Subscription subscription = Subscription.fromDto(subscriptionDto, child, subscriptionType);

        SubscriptionPayment subscriptionPayment = subscriptionPaymentService.saveSubscriptionPayment(
                subscriptionPaymentDto, subscription, paymentInfo
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

    public SubscriptionDto deactivateSubscription(Integer subscriptionId) {
        Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
        subscription.setActive(false);
        return SubscriptionDto.fromEntity(subscriptionRepository.save(subscription));
    }

    public void deleteSubscription(Integer subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }
}
