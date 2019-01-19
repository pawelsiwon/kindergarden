package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator;

import java.math.BigDecimal;

public class SecondChildBargain extends PaymentDecorator {
    public SecondChildBargain(BasicFee customizedPayment) {
        super(customizedPayment);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().multiply(new BigDecimal(0.90));
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }
}
