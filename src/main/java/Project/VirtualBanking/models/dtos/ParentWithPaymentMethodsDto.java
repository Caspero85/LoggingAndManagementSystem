package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.Parent;

import java.util.List;
import java.util.stream.Collectors;

public class ParentWithPaymentMethodsDto {

    private ParentDto parentDto;
    private List<PaymentMethodDto> paymentMethodsDto;

    public ParentWithPaymentMethodsDto() {
    }

    public ParentWithPaymentMethodsDto(ParentDto parentDto, List<PaymentMethodDto> paymentMethodsDto) {
        this.parentDto = parentDto;
        this.paymentMethodsDto = paymentMethodsDto;
    }

    public ParentDto getParentDto() {
        return parentDto;
    }
    public void setParentDto(ParentDto parentDto) {
        this.parentDto = parentDto;
    }

    public List<PaymentMethodDto> getPaymentMethodsDto() {
        return paymentMethodsDto;
    }
    public void setPaymentMethodsDto(List<PaymentMethodDto> paymentMethodsDto) {
        this.paymentMethodsDto = paymentMethodsDto;
    }

    public static ParentWithPaymentMethodsDto fromEntity(Parent parent){
        return new ParentWithPaymentMethodsDto(
                ParentDto.fromEntity(parent),
                parent.getPaymentMethods().stream().map(paymentMethod -> PaymentMethodDto.fromEntity(paymentMethod))
                        .collect(Collectors.toList())
        );
    }
}
