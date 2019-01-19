package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator;

import java.math.BigDecimal;

public class MealsFee extends PaymentDecorator {
    public MealsFee(BasicFee customizedPayment) {
        super(customizedPayment);
    }

    @Override
    public BigDecimal getCost() {
        return super.getCost().add(new BigDecimal(180));
    }

    @Override
    public String getDescription() {
        return super.getDescription()+"Meals, ";
    }
}
