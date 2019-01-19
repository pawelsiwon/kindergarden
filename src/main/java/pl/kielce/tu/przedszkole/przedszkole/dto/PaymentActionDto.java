package pl.kielce.tu.przedszkole.przedszkole.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentActionDto {
    private LoginData loginData;

    private Long payerId;

    private Long childId;

    private String name;

    private List<String> paymentIncludes;

    private Long paymentId;
}
