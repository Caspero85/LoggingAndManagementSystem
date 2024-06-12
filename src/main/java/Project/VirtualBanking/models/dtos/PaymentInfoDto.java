package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.PaymentInfo;

import java.time.LocalDate;

public class PaymentInfoDto {

    private Integer paymentInfoID;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;
    private LocalDate paymentMethodAddedDate;
    private boolean active;
    private String details;

    public PaymentInfoDto() {
    }

    public PaymentInfoDto(Integer paymentInfoID, String cardNumber, String cardHolderName,
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

    public static PaymentInfoDto fromEntity(PaymentInfo paymentInfo) {
        return new PaymentInfoDto(
                paymentInfo.getPaymentInfoID(),
                paymentInfo.getCardNumber(),
                paymentInfo.getCardHolderName(),
                paymentInfo.getCardExpirationDate(),
                paymentInfo.getCardCvv(),
                paymentInfo.getPaymentMethodAddedDate(),
                paymentInfo.isActive(),
                paymentInfo.getDetails()
        );
    }
}
