package Project.VirtualBanking.services;

import Project.VirtualBanking.Encryption.EncryptEntities.EncryptPaymentMethod;
import Project.VirtualBanking.models.dtos.PaymentMethodDto;
import Project.VirtualBanking.models.entities.EncryptionKey;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.models.entities.PaymentMethod;
import Project.VirtualBanking.repositories.EncryptionKeyRepository;
import Project.VirtualBanking.repositories.ParentRepository;
import Project.VirtualBanking.repositories.PaymentMethodRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentMethodService {

    PaymentMethodRepository paymentMethodRepository;
    ParentRepository parentRepository;
    EncryptionKeyRepository encryptionKeyRepository;

    public PaymentMethodService(PaymentMethodRepository paymentMethodRepository, ParentRepository ParentRepository,
                                EncryptionKeyRepository encryptionKeyRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.parentRepository = ParentRepository;
        this.encryptionKeyRepository = encryptionKeyRepository;
    }

    public PaymentMethodDto savePaymentMethod(PaymentMethodDto paymentMethodDto, Integer parentId) {
        Parent parent = parentRepository.findById(parentId).orElseThrow();
        parent.getPaymentMethods().forEach(paymentMethod -> paymentMethod.setActive(false));

        return PaymentMethodDto.fromEntity(paymentMethodRepository.save(PaymentMethod.fromDto(
                paymentMethodDto,
                parent,
                encryptionKeyRepository.save(new EncryptionKey())
        )));
    }

    public List<PaymentMethodDto> findAllPaymentMethods() {
        return paymentMethodRepository.findAll().stream()
                .map(paymentMethod -> PaymentMethodDto.fromEntity(paymentMethod)).collect(Collectors.toList());
    }

    public List<PaymentMethodDto> findAllActivePaymentMethods() {
        return paymentMethodRepository.findAll().stream().filter(paymentMethod -> paymentMethod.isActive())
                .map(paymentMethod -> PaymentMethodDto.fromEntity(paymentMethod)).collect(Collectors.toList());
    }

    public PaymentMethodDto findPaymentMethodById(Integer paymentMethodId) {
        return PaymentMethodDto.fromEntity(paymentMethodRepository.findById(paymentMethodId).orElseThrow());
    }

    public PaymentMethodDto editPaymentMethod(PaymentMethodDto paymentMethodDto, Integer paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
        BeanUtils.copyProperties(
                paymentMethodDto,
                paymentMethod,
                "paymentInfoID", "paymentMethodAddedDate", "active"
        );
        EncryptPaymentMethod.encryptPaymentMethod(paymentMethod, paymentMethod.getEncryptionKey().getEncryptionKey());
        return PaymentMethodDto.fromEntity(paymentMethodRepository.save(paymentMethod));
    }

    public PaymentMethodDto activatePaymentMethod(Integer paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
        paymentMethod.setActive(true);
        return PaymentMethodDto.fromEntity(paymentMethodRepository.save(paymentMethod));
    }

    public PaymentMethodDto deactivatePaymentMethod(Integer paymentMethodId) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).orElseThrow();
        paymentMethod.setActive(false);
        return PaymentMethodDto.fromEntity(paymentMethodRepository.save(paymentMethod));
    }

    public void deletePaymentMethod(Integer paymentMethodId) {
        paymentMethodRepository.deleteById(paymentMethodId);
    }
}
