package pl.kielce.tu.przedszkole.przedszkole.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.kielce.tu.przedszkole.przedszkole.dto.LoginData;
import pl.kielce.tu.przedszkole.przedszkole.dto.PaymentActionDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Payment;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentService;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentServiceImpl;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentServiceParentProxy;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentServiceTeacherProxy;
import pl.kielce.tu.przedszkole.przedszkole.utils.PersonResolverUtils;

import java.util.List;

@Service
public class PaymentProxyDispatcher {

    private final PersonResolverUtils personResolverUtils;
    private final PaymentServiceImpl paymentServiceImpl;
    private final PaymentServiceTeacherProxy paymentServiceTeacherProxy;
    private final PaymentServiceParentProxy paymentServiceParentProxy;

    @Autowired
    public PaymentProxyDispatcher(PersonResolverUtils personResolverUtils,
                                  PaymentServiceImpl paymentServiceImpl,
                                  PaymentServiceTeacherProxy paymentServiceTeacherProxy,
                                  PaymentServiceParentProxy paymentServiceParentProxy) {
        this.personResolverUtils = personResolverUtils;
        this.paymentServiceImpl = paymentServiceImpl;
        this.paymentServiceTeacherProxy = paymentServiceTeacherProxy;
        this.paymentServiceParentProxy = paymentServiceParentProxy;
    }

    private PaymentService resolvePaymentInterface(Person person) {
        if (person.getRole().equalsIgnoreCase("ADMIN")) return paymentServiceImpl;
        else if(person.getRole().equalsIgnoreCase("TEACHER")) return paymentServiceTeacherProxy;
        else return paymentServiceParentProxy;
    }

    @Transactional
    public void addPayment(PaymentActionDto paymentActionDto) throws Exception {
        resolvePaymentInterface(personResolverUtils.resolvePersonByLogin(paymentActionDto.getLoginData().getLogin()))
                .addPayment(paymentActionDto);
    }
    @Transactional
    public void editPayment(PaymentActionDto paymentActionDto) throws Exception {
        resolvePaymentInterface(personResolverUtils.resolvePersonByLogin(paymentActionDto.getLoginData().getLogin()))
        .editPayment(paymentActionDto);
    }
    @Transactional
    public void deletePayment(PaymentActionDto paymentActionDto) throws Exception {
        resolvePaymentInterface(personResolverUtils.resolvePersonByLogin(paymentActionDto.getLoginData().getLogin()))
                .deletePayment(paymentActionDto);
    }
    public List<Payment> getPaymentByParentAndChildIds(PaymentActionDto paymentActionDto) {
        return resolvePaymentInterface(personResolverUtils.resolvePersonByLogin(paymentActionDto.getLoginData().getLogin()))
                .getPaymentsByParentAndChild(paymentActionDto);
    }

    public Payment getPaymentById(PaymentActionDto paymentActionDto) {
        return resolvePaymentInterface(personResolverUtils.resolvePersonByLogin(paymentActionDto.getLoginData().getLogin()))
                .getPaymentById(paymentActionDto);
    }

    public List<Payment> getAllPayments(LoginData loginData) {
        return resolvePaymentInterface(personResolverUtils.resolvePersonByLogin(loginData.getLogin())).getAllPayments(loginData);
    }
}
