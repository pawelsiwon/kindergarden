package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator;

import java.math.BigDecimal;

public class InsuranceFee extends PaymentDecorator {
    public InsuranceFee(BasicFee customizedPayment) {
        super(customizedPayment);

    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(35));
    }

    @Override
    public String getDescription() {
        return super.getDescription()+"Insurance, ";
    }
}
