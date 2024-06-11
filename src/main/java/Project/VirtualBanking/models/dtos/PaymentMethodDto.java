package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.PaymentMethod;

import java.time.LocalDate;

public class PaymentMethodDto {

    private Integer paymentInfoID;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;
    private LocalDate paymentMethodAddedDate;
    private boolean active;
    private String details;

    public PaymentMethodDto() {
    }

    public PaymentMethodDto(Integer paymentInfoID, String cardNumber, String cardHolderName,
                            String cardExpirationDate, String cardCvv, LocalDate paymentMethodAddedDate,
                            boolean active, String details) {
        this.paymentInfoID = paymentInfoID;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCvv = cardCvv;
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

    public String getCardExpirationDate() {
        return cardExpirationDate;
    }
    public void setCardExpirationDate(String cardExpirationDate) {
        this.cardExpirationDate = cardExpirationDate;
    }

    public String getCardCvv() {
        return cardCvv;
    }
    public void setCardCvv(String cardCvv) {
        this.cardCvv = cardCvv;
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
                paymentMethod.getCardExpirationDate(),
                paymentMethod.getCardCvv(),
                paymentMethod.getPaymentMethodAddedDate(),
                paymentMethod.isActive(),
                paymentMethod.getDetails()
        );
    }
}
