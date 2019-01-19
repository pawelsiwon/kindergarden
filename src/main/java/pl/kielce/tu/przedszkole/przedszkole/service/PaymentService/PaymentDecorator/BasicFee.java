package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator;

import java.math.BigDecimal;

public interface BasicFee {

    BigDecimal getCost();

    String getDescription();
}
