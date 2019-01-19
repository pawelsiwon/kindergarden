package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator;

import java.math.BigDecimal;

abstract public class PaymentDecorator implements BasicFee {
    private BasicFee customizedPayment;

    public PaymentDecorator(BasicFee customizedPayment) {
        this.customizedPayment = customizedPayment;
    }

    @Override
    public BigDecimal getCost() {
        return customizedPayment.getCost();
    }

    @Override
    public String getDescription() {
        return customizedPayment.getDescription();
    }
}
