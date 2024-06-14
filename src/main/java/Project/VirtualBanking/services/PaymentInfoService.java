package Project.VirtualBanking.services;

import Project.VirtualBanking.OtherMethods.EntityValidationCheck.PaymentInfoValidationCheck;
import Project.VirtualBanking.models.dtos.ParentDto;
import Project.VirtualBanking.models.dtos.PaymentInfoDto;
import Project.VirtualBanking.models.entities.EncryptionKey;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.models.entities.PaymentInfo;
import Project.VirtualBanking.repositories.EncryptionKeyRepository;
import Project.VirtualBanking.repositories.ParentRepository;
import Project.VirtualBanking.repositories.PaymentInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentInfoService {

    PaymentInfoRepository paymentInfoRepository;
    ParentRepository parentRepository;
    EncryptionKeyRepository encryptionKeyRepository;

    public PaymentInfoService(PaymentInfoRepository paymentInfoRepository, ParentRepository ParentRepository,
                              EncryptionKeyRepository encryptionKeyRepository) {
        this.paymentInfoRepository = paymentInfoRepository;
        this.parentRepository = ParentRepository;
        this.encryptionKeyRepository = encryptionKeyRepository;
    }

    public PaymentInfoDto savePaymentInfo(PaymentInfoDto paymentInfoDto, Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        List<PaymentInfo> paymentsInfo = paymentInfoRepository.findAll();

        PaymentInfoValidationCheck.saveCreditCardValidationCheck(paymentInfoDto, parent, paymentsInfo);

        parent.getPaymentInfo().forEach(paymentInfo -> paymentInfo.setActive(false));

        return PaymentInfoDto.fromEntity(paymentInfoRepository.save(PaymentInfo.fromDto(
                paymentInfoDto,
                parent,
                encryptionKeyRepository.save(new EncryptionKey())
        )));
    }

    public List<PaymentInfoDto> findAllPaymentsInfo() {
        return paymentInfoRepository.findAll().stream()
                .map(paymentInfo -> PaymentInfoDto.fromEntity(paymentInfo)).collect(Collectors.toList());
    }

    public List<PaymentInfoDto> findAllActivePaymentsInfo() {
        return paymentInfoRepository.findAll().stream().filter(paymentInfo -> paymentInfo.isActive())
                .map(paymentInfo -> PaymentInfoDto.fromEntity(paymentInfo)).collect(Collectors.toList());
    }

    public PaymentInfoDto findPaymentsInfoById(Integer paymentInfoId) {
        return PaymentInfoDto.fromEntity(paymentInfoRepository.findById(paymentInfoId).orElseThrow());
    }

    public PaymentInfoDto editPaymentInfoDetails(Integer paymentInfoId, PaymentInfoDto paymentInfoDto) {
        PaymentInfo paymentInfo = paymentInfoRepository.findById(paymentInfoId).orElseThrow();
        paymentInfo.setDetails(paymentInfoDto.getDetails());
        return PaymentInfoDto.fromEntity(paymentInfoRepository.save(paymentInfo));
    }

    public PaymentInfoDto deactivatePaymentInfo(Integer paymentInfoId) {
        PaymentInfo paymentInfo = paymentInfoRepository.findById(paymentInfoId).orElseThrow();

        paymentInfo.setActive(false);

        paymentInfo.getSubscriptionPayment().forEach(subscriptionPayment -> {
            subscriptionPayment.getSubscription().setActive(false);
            subscriptionPayment.getSubscription().setRecursive(false);
        });

        return PaymentInfoDto.fromEntity(paymentInfoRepository.save(paymentInfo));
    }

    public void deletePaymentInfo(Integer paymentInfoId) {
        PaymentInfo paymentInfo = paymentInfoRepository.findById(paymentInfoId).orElseThrow();

        paymentInfo.getSubscriptionPayment().forEach(subscriptionPayment -> {
            subscriptionPayment.getSubscription().setActive(false);
            subscriptionPayment.getSubscription().setRecursive(false);
        });

        PaymentInfoDto.fromEntity(paymentInfoRepository.save(paymentInfo));

        paymentInfoRepository.deleteById(paymentInfoId);
    }

    /**
     * Parent related methods
     */

    public ParentDto findParentByPaymentInfoId(Integer paymentInfoId) {
        return ParentDto.fromEntity(paymentInfoRepository.findById(paymentInfoId).orElseThrow().getParent());
    }
}
