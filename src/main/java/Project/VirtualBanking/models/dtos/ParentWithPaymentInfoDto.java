package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.Parent;

import java.util.List;
import java.util.stream.Collectors;

public class ParentWithPaymentInfoDto {

    private ParentDto parentDto;
    private List<PaymentInfoDto> paymentsInfoDto;

    public ParentWithPaymentInfoDto() {
    }

    public ParentWithPaymentInfoDto(ParentDto parentDto, List<PaymentInfoDto> paymentsInfoDto) {
        this.parentDto = parentDto;
        this.paymentsInfoDto = paymentsInfoDto;
    }

    public ParentDto getParentDto() {
        return parentDto;
    }
    public void setParentDto(ParentDto parentDto) {
        this.parentDto = parentDto;
    }

    public List<PaymentInfoDto> getPaymentsInfoDto() {
        return paymentsInfoDto;
    }
    public void setPaymentsInfoDto(List<PaymentInfoDto> paymentsInfoDto) {
        this.paymentsInfoDto = paymentsInfoDto;
    }

    public static ParentWithPaymentInfoDto fromEntity(Parent parent){
        return new ParentWithPaymentInfoDto(
                ParentDto.fromEntity(parent),
                parent.getPaymentInfo().stream().map(paymentInfo -> PaymentInfoDto.fromEntity(paymentInfo))
                        .collect(Collectors.toList())
        );
    }
}
