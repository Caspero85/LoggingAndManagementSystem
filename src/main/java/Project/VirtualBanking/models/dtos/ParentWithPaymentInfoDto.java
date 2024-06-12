package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.Parent;

import java.util.List;
import java.util.stream.Collectors;

public class ParentWithPaymentInfoDto {

    private ParentDto parentDto;
    private List<PaymentInfoDto> paymentMethodsDto;

    public ParentWithPaymentInfoDto() {
    }

    public ParentWithPaymentInfoDto(ParentDto parentDto, List<PaymentInfoDto> paymentMethodsDto) {
        this.parentDto = parentDto;
        this.paymentMethodsDto = paymentMethodsDto;
    }

    public ParentDto getParentDto() {
        return parentDto;
    }
    public void setParentDto(ParentDto parentDto) {
        this.parentDto = parentDto;
    }

    public List<PaymentInfoDto> getPaymentMethodsDto() {
        return paymentMethodsDto;
    }
    public void setPaymentMethodsDto(List<PaymentInfoDto> paymentMethodsDto) {
        this.paymentMethodsDto = paymentMethodsDto;
    }

    public static ParentWithPaymentInfoDto fromEntity(Parent parent){
        return new ParentWithPaymentInfoDto(
                ParentDto.fromEntity(parent),
                parent.getPaymentMethods().stream().map(paymentMethod -> PaymentInfoDto.fromEntity(paymentMethod))
                        .collect(Collectors.toList())
        );
    }
}
