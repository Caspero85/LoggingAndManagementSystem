package Project.VirtualBanking.services;

import Project.VirtualBanking.OtherMethods.EntityValidationCheck.PaymentInfoValidationCheck;
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

    public PaymentInfoDto savePaymentMethod(PaymentInfoDto paymentInfoDto, Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();

        PaymentInfoValidationCheck.saveCreditCardValidationCheck(paymentInfoDto, parent);

        parent.getPaymentMethods().forEach(paymentMethod -> paymentMethod.setActive(false));

        return PaymentInfoDto.fromEntity(paymentInfoRepository.save(PaymentInfo.fromDto(
                paymentInfoDto,
                parent,
                encryptionKeyRepository.save(new EncryptionKey())
        )));
    }

    public List<PaymentInfoDto> findAllPaymentMethods() {
        return paymentInfoRepository.findAll().stream()
                .map(paymentMethod -> PaymentInfoDto.fromEntity(paymentMethod)).collect(Collectors.toList());
    }

    public List<PaymentInfoDto> findAllActivePaymentMethods() {
        return paymentInfoRepository.findAll().stream().filter(paymentMethod -> paymentMethod.isActive())
                .map(paymentMethod -> PaymentInfoDto.fromEntity(paymentMethod)).collect(Collectors.toList());
    }

    public PaymentInfoDto findPaymentMethodById(Integer paymentMethodId) {
        return PaymentInfoDto.fromEntity(paymentInfoRepository.findById(paymentMethodId).orElseThrow());
    }

    public PaymentInfoDto activatePaymentMethod(Integer paymentMethodId) {
        PaymentInfo paymentInfo = paymentInfoRepository.findById(paymentMethodId).orElseThrow();
        paymentInfo.setActive(true);
        return PaymentInfoDto.fromEntity(paymentInfoRepository.save(paymentInfo));
    }

    public PaymentInfoDto deactivatePaymentMethod(Integer paymentMethodId) {
        PaymentInfo paymentInfo = paymentInfoRepository.findById(paymentMethodId).orElseThrow();
        paymentInfo.setActive(false);
        return PaymentInfoDto.fromEntity(paymentInfoRepository.save(paymentInfo));
    }

    public void deletePaymentMethod(Integer paymentMethodId) {
        paymentInfoRepository.deleteById(paymentMethodId);
    }
}
