package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService;


import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.PaymentActionDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Payment;

import java.util.List;

public interface PaymentService {
    void addPayment(PaymentActionDto paymentActionDto) throws Exception;

    void editPayment(PaymentActionDto paymentActionDto) throws Exception;

    void deletePayment(PaymentActionDto paymentActionDto) throws Exception;

    List<Payment> getPaymentsByParentAndChild(PaymentActionDto paymentActionDto);

    Payment getPaymentById(PaymentActionDto paymentActionDto);

    List<Payment> getAllPayments(LoginData loginData);
}
