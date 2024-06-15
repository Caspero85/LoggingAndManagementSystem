package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.OtherMethods.EncryptionMethods.EncryptionMethods;
import Project.VirtualBanking.models.dtos.PaymentInfoDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class PaymentInfo {

    @Id
    @GeneratedValue
    private Integer paymentInfoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentID", referencedColumnName = "parentID")
    private Parent parent;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false)
    private String cardExpirationDate;

    @Column(nullable = false)
    private String cardCvv;

    private LocalDate paymentMethodAddedDate;

    private Boolean active;

    private String details;

    @OneToMany(mappedBy = "paymentInfo", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<SubscriptionPayment> subscriptionPayment;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "keyId", referencedColumnName = "keyId")
    private EncryptionKey encryptionKey;

    public PaymentInfo() {
    }

    public PaymentInfo(Parent parent, String cardNumber, String cardHolderName, String cardExpirationDate,
                       String cardCvv, String details, EncryptionKey encryptionKey) {
        this.parent = parent;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCvv = cardCvv;
        this.paymentMethodAddedDate = LocalDate.now();
        this.active = true;
        this.details = details;
        this.encryptionKey = encryptionKey;
    }

    public Integer getPaymentInfoId() {
        return paymentInfoId;
    }
    public void setPaymentInfoId(Integer paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public Parent getParent() {
        return parent;
    }
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getCardNumber() {
        return EncryptionMethods.decryptData(cardNumber, encryptionKey.getEncryptionKey());
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = EncryptionMethods.encryptData(cardNumber, encryptionKey.getEncryptionKey());
    }

    public String getCardHolderName() {
        return EncryptionMethods.decryptData(cardHolderName, encryptionKey.getEncryptionKey());
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = EncryptionMethods.encryptData(cardHolderName, encryptionKey.getEncryptionKey());
    }

    public String getCardExpirationDate() {
        return EncryptionMethods.decryptData(cardExpirationDate, encryptionKey.getEncryptionKey());
    }
    public void setCardExpirationDate(String expirationDate) {
        this.cardExpirationDate = EncryptionMethods.encryptData(expirationDate, encryptionKey.getEncryptionKey());
    }

    public String getCardCvv() {
        return EncryptionMethods.decryptData(cardCvv, encryptionKey.getEncryptionKey());
    }
    public void setCardCvv(String cvv) {
        this.cardCvv = EncryptionMethods.encryptData(cvv, encryptionKey.getEncryptionKey());
    }

    public LocalDate getPaymentMethodAddedDate() {
        return paymentMethodAddedDate;
    }
    public void setPaymentMethodAddedDate(LocalDate paymentMethodAddedDate) {
        this.paymentMethodAddedDate = paymentMethodAddedDate;
    }

    public Boolean isActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public List<SubscriptionPayment> getSubscriptionPayment() {
        return subscriptionPayment;
    }
    public void setSubscriptionPayment(List<SubscriptionPayment> subscriptionPayment) {
        this.subscriptionPayment = subscriptionPayment;
    }

    public EncryptionKey getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public static void encryptPaymentMethod(PaymentInfo paymentInfo, PaymentInfoDto paymentInfoDto) {
        paymentInfo.setCardNumber(paymentInfoDto.getCardNumber());
        paymentInfo.setCardHolderName(paymentInfoDto.getCardHolderName());
        paymentInfo.setCardExpirationDate(paymentInfoDto.getCardExpirationDate());
        paymentInfo.setCardCvv(paymentInfoDto.getCardCvv());
    }

    public static PaymentInfo fromDto(PaymentInfoDto paymentInfoDto, Parent parent, EncryptionKey encryptionKey) {
        PaymentInfo paymentInfo = new PaymentInfo(
                parent,
                paymentInfoDto.getCardNumber(),
                paymentInfoDto.getCardHolderName(),
                paymentInfoDto.getCardExpirationDate(),
                paymentInfoDto.getCardCvv(),
                paymentInfoDto.getDetails(),
                encryptionKey
        );
        encryptPaymentMethod(paymentInfo, paymentInfoDto);
        return paymentInfo;
    }
}
