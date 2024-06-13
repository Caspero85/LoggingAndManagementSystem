package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.PaymentInfo;

import java.time.LocalDate;

public class PaymentInfoDto {

    private Integer paymentInfoId;
    private Integer parentId;
    private String cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private String cardCvv;
    private LocalDate paymentMethodAddedDate;
    private Boolean active;
    private String details;

    public PaymentInfoDto() {
    }

    public PaymentInfoDto(Integer paymentInfoId, Integer parentId, String cardNumber, String cardHolderName,
                          String cardExpirationDate, String cardCvv, LocalDate paymentMethodAddedDate,
                          Boolean active, String details) {
        this.paymentInfoId = paymentInfoId;
        this.parentId = parentId;
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationDate = cardExpirationDate;
        this.cardCvv = cardCvv;
        this.paymentMethodAddedDate = paymentMethodAddedDate;
        this.active = active;
        this.details = details;
    }

    public Integer getPaymentInfoId() {
        return paymentInfoId;
    }
    public void setPaymentInfoId(Integer paymentInfoId) {
        this.paymentInfoId = paymentInfoId;
    }

    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public static PaymentInfoDto fromEntity(PaymentInfo paymentInfo) {
        return new PaymentInfoDto(
                paymentInfo.getPaymentInfoId(),
                paymentInfo.getParent().getParentId(),
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
