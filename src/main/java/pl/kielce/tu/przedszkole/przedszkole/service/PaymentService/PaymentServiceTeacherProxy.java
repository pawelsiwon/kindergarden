package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.dto.PaymentActionDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Payment;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PaymentServiceTeacherProxy implements PaymentService {

    private final Logger logger = Logger.getLogger(PaymentServiceTeacherProxy.class.getName());
    private final PaymentServiceImpl paymentServiceImpl;

    @Autowired
    public PaymentServiceTeacherProxy(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @Override
    public void addPayment(PaymentActionDto paymentActionDto) throws Exception {
        logger.info(paymentActionDto.getLoginData().getLogin()+"added new payment data.");
        paymentServiceImpl.addPayment(paymentActionDto);
    }

    @Override
    public void editPayment(PaymentActionDto paymentActionDto) throws Exception {
        logger.info(paymentActionDto.getLoginData().getLogin()+"added edited payment data.");
        paymentServiceImpl.editPayment(paymentActionDto);
    }

    @Override
    public void deletePayment(PaymentActionDto paymentActionDto) throws Exception {
        logger.info(paymentActionDto.getLoginData().getLogin()+"deleted some payment data.");
        paymentServiceImpl.deletePayment(paymentActionDto);
    }

    @Override
    public List<Payment> getPaymentsByParentAndChild(PaymentActionDto paymentActionDto) {
        return paymentServiceImpl.getPaymentsByParentAndChild(paymentActionDto);
    }

    @Override
    public Payment getPaymentById(PaymentActionDto paymentActionDto) {
        return paymentServiceImpl.getPaymentById(paymentActionDto);
    }
}
