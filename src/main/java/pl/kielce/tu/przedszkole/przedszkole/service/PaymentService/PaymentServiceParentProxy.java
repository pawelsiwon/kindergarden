package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.PaymentActionDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Payment;

import javax.naming.OperationNotSupportedException;
import java.util.List;
import java.util.logging.Logger;

@Service
public class PaymentServiceParentProxy implements PaymentService{

    private final Logger logger = Logger.getLogger(PaymentServiceTeacherProxy.class.getName());
    private final PaymentServiceImpl paymentServiceImpl;

    @Autowired
    public PaymentServiceParentProxy(PaymentServiceImpl paymentServiceImpl) {
        this.paymentServiceImpl = paymentServiceImpl;
    }

    @Override
    public void addPayment(PaymentActionDto paymentActionDto) throws Exception {
        throw new OperationNotSupportedException("Parent cannot do payment manipulation");
    }

    @Override
    public void editPayment(PaymentActionDto paymentActionDto) throws Exception {
        throw new OperationNotSupportedException("Parent cannot do payment manipulation");
    }

    @Override
    public void deletePayment(PaymentActionDto paymentActionDto) throws Exception {
        throw new OperationNotSupportedException("Parent cannot do payment manipulation");
    }

    @Override
    public List<Payment> getPaymentsByParentAndChild(PaymentActionDto paymentActionDto) {
        return paymentServiceImpl.getPaymentsByParentAndChild(paymentActionDto);
    }

    @Override
    public Payment getPaymentById(PaymentActionDto paymentActionDto) {
        return paymentServiceImpl.getPaymentById(paymentActionDto);
    }

    @Override
    public List<Payment> getAllPayments(LoginData loginData) {
        return paymentServiceImpl.getAllPayments(loginData);
    }
}
