package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator;

import java.math.BigDecimal;

public class BasicPayment implements BasicFee {
    private BigDecimal cost;
    private String description;

    public BasicPayment() {
        this.cost = new BigDecimal(0);
        this.description = "";
    }

    public BasicPayment(BigDecimal cost, String description) {

        this.cost = cost;
        this.description = description;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getDescription() {
        return "";
    }
}
