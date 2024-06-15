package Project.VirtualBanking.services;

import Project.VirtualBanking.models.dtos.PaymentInfoDto;
import Project.VirtualBanking.models.dtos.SubscriptionDto;
import Project.VirtualBanking.models.dtos.SubscriptionPaymentDto;
import Project.VirtualBanking.models.entities.PaymentInfo;
import Project.VirtualBanking.models.entities.Subscription;
import Project.VirtualBanking.models.entities.SubscriptionPayment;
import Project.VirtualBanking.repositories.PaymentInfoRepository;
import Project.VirtualBanking.repositories.SubscriptionPaymentRepository;
import Project.VirtualBanking.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionPaymentService {

    SubscriptionPaymentRepository subscriptionPaymentRepository;
    SubscriptionRepository subscriptionRepository;
    PaymentInfoRepository paymentInfoRepository;

    public SubscriptionPaymentService(
            SubscriptionPaymentRepository subscriptionPaymentRepository,
            SubscriptionRepository subscriptionRepository,
            PaymentInfoRepository paymentInfoRepository
    ) {
        this.subscriptionPaymentRepository = subscriptionPaymentRepository;
        this.subscriptionRepository = subscriptionRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    public SubscriptionPayment saveSubscriptionPayment(Subscription subscription, PaymentInfo paymentInfo) {
        return subscriptionPaymentRepository.save(new SubscriptionPayment(subscription, paymentInfo));
    }

    public List<SubscriptionPaymentDto> findAllSubscriptionPayments() {
        return subscriptionPaymentRepository.findAll().stream()
                .map(subscriptionPayment -> SubscriptionPaymentDto.fromEntity(subscriptionPayment))
                .collect(Collectors.toList());
    }

    public SubscriptionPaymentDto findSubscriptionById(Integer subscriptionPaymentId) {
        return SubscriptionPaymentDto.fromEntity(subscriptionPaymentRepository.findById(subscriptionPaymentId).orElseThrow());
    }

    public SubscriptionPaymentDto editSubscriptionPaymentDetails(Integer subscriptionPaymentId, SubscriptionPaymentDto subscriptionPaymentDto) {
        SubscriptionPayment subscriptionPayment = subscriptionPaymentRepository.findById(subscriptionPaymentId).orElseThrow();
        subscriptionPayment.setDetails(subscriptionPaymentDto.getDetails());
        return SubscriptionPaymentDto.fromEntity(subscriptionPaymentRepository.save(subscriptionPayment));
    }

    public void deleteSubscriptionPayment(Integer subscriptionPaymentId) {
        subscriptionPaymentRepository.deleteById(subscriptionPaymentId);
    }

    /**
     * Subscription related methods
     */

    public SubscriptionDto findSubscriptionBySubscriptionPaymentId(Integer subscriptionPaymentId) {
        return SubscriptionDto.fromEntity(subscriptionPaymentRepository.findById(subscriptionPaymentId).orElseThrow().getSubscription());
    }

    /**
     * PaymentInfo related methods
     */

    public PaymentInfoDto findPaymentInfoBySubscriptionPaymentId(Integer subscriptionPaymentId) {
        return PaymentInfoDto.fromEntity(subscriptionPaymentRepository.findById(subscriptionPaymentId).orElseThrow().getPaymentInfo());
    }
}
