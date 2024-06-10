package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.PaymentMethod;

import java.time.LocalDate;

public class PaymentMethodDto {

    private Integer paymentInfoID;
    private String cardNumber;
    private String cardHolderName;
    private String cardHolderUsername;
    private String expirationDate;
    private String cvv;
    private LocalDate paymentMethodAddedDate;
    private boolean active;
    private String details;

    public PaymentMethodDto() {
    }

    public PaymentMethodDto(Integer paymentInfoID, String cardNumber, String cardHolderName,
                            String cardHolderUsername, String expirationDate, String cvv,
                            LocalDate paymentMethodAddedDate, boolean active, String details) {
        this.paymentInfoID = paymentInfoID;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardHolderUsername = cardHolderUsername;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.paymentMethodAddedDate = paymentMethodAddedDate;
        this.active = active;
        this.details = details;
    }

    public Integer getPaymentInfoID() {
        return paymentInfoID;
    }
    public void setPaymentInfoID(Integer paymentInfoID) {
        this.paymentInfoID = paymentInfoID;
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

    public static PaymentMethodDto fromEntity(PaymentMethod paymentMethod) {
        return new PaymentMethodDto(
                paymentMethod.getPaymentInfoID(),
                paymentMethod.getCardNumber(),
                paymentMethod.getCardHolderName(),
                paymentMethod.getCardHolderUsername(),
                paymentMethod.getExpirationDate(),
                paymentMethod.getCvv(),
                paymentMethod.getPaymentMethodAddedDate(),
                paymentMethod.isActive(),
                paymentMethod.getDetails()
        );
    }
}
