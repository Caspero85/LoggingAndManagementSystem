package Project.VirtualBanking.models.entities;

import Project.VirtualBanking.Encryption.EncryptEntities.EncryptPaymentMethod;
import Project.VirtualBanking.models.dtos.PaymentMethodDto;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PaymentMethod {

    @Id
    @GeneratedValue
    private Integer paymentInfoID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentID", referencedColumnName = "parentID")
    private Parent parent;

    @Column(nullable = false)
    private String cardNumber;

    @Column(nullable = false)
    private String cardHolderName;

    @Column(nullable = false)
    private String cardHolderUsername;

    @Column(nullable = false)
    private String expirationDate;

    @Column(nullable = false)
    private String cvv;

    private LocalDate paymentMethodAddedDate;

    private boolean active;

    private String details;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "keyID", referencedColumnName = "keyID")
    private EncryptionKey encryptionKey;

    public PaymentMethod() {
    }

    public PaymentMethod(Parent parent, String cardNumber, String cardHolderName, String cardHolderUsername,
                         String expirationDate, String cvv, String details, EncryptionKey encryptionKey) {
        this.parent = parent;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardHolderUsername = cardHolderUsername;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.paymentMethodAddedDate = LocalDate.now();
        this.active = true;
        this.details = details;
        this.encryptionKey = encryptionKey;
    }

    public Integer getPaymentInfoID() {
        return paymentInfoID;
    }
    public void setPaymentInfoID(Integer paymentInfoID) {
        this.paymentInfoID = paymentInfoID;
    }

    public Parent getParent() {
        return parent;
    }
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardHolderUsername() {
        return cardHolderUsername;
    }
    public void setCardHolderUsername(String cardHolderUsername) {
        this.cardHolderUsername = cardHolderUsername;
    }

    public String getExpirationDate() {
        return expirationDate;
    }
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }
    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public LocalDate getPaymentMethodAddedDate() {
        return paymentMethodAddedDate;
    }
    public void setPaymentMethodAddedDate(LocalDate paymentMethodAddedDate) {
        this.paymentMethodAddedDate = paymentMethodAddedDate;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public EncryptionKey getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public static PaymentMethod fromDto(PaymentMethodDto paymentMethodDto, Parent parent, EncryptionKey encryptionKey) {
        PaymentMethod paymentMethod = new PaymentMethod(
                parent,
                paymentMethodDto.getCardNumber(),
                paymentMethodDto.getCardHolderName(),
                paymentMethodDto.getCardHolderUsername(),
                paymentMethodDto.getExpirationDate(),
                paymentMethodDto.getCvv(),
                paymentMethodDto.getDetails(),
                encryptionKey
        );
        EncryptPaymentMethod.encryptPaymentMethod(paymentMethod, paymentMethod.getEncryptionKey().getEncryptionKey());
        return paymentMethod;
    }
}
