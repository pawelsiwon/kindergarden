package pl.kielce.tu.przedszkole.przedszkole.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.kielce.tu.przedszkole.przedszkole.dto.PaymentActionDto;
import pl.kielce.tu.przedszkole.przedszkole.model.Child;
import pl.kielce.tu.przedszkole.przedszkole.model.Payment;
import pl.kielce.tu.przedszkole.przedszkole.model.Person;
import pl.kielce.tu.przedszkole.przedszkole.repository.ChildRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.PaymentRepository;
import pl.kielce.tu.przedszkole.przedszkole.repository.PersonRepository;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService{

    private final ChildRepository childRepository;
    private final PersonRepository personRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(ChildRepository childRepository,
                              PersonRepository personRepository,
                              PaymentRepository paymentRepository) {
        this.childRepository = childRepository;
        this.personRepository = personRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void addPayment(PaymentActionDto paymentActionDto) {
        Payment payment = new Payment();
        Child child = childRepository.getOne(paymentActionDto.getChildId());
        Person payer = personRepository.getOne(paymentActionDto.getPayerId());
        payment.setChild(child);
        payment.setPerson(payer);
        payment.setName(paymentActionDto.getName());

        BasicFee paymentBuild = new BasicPayment();

        paymentBuild = decoratePayment(paymentActionDto, paymentBuild);

        payment.setDescription(paymentBuild.getDescription());

        payment.setCost(paymentBuild.getCost());

        payment.setPaymentDate(new Date());

        paymentRepository.save(payment);
    }

    private BasicFee decoratePayment(PaymentActionDto paymentActionDto, BasicFee paymentBuild) {
        List<String> paymentIncludes = paymentActionDto.getPaymentIncludes();
        if(paymentIncludes.contains("INSURANCE_FEE")) {
            paymentBuild = new InsuranceFee(paymentBuild);
        }

        if(paymentIncludes.contains("MEALS_FEE")) {
            paymentBuild = new MealsFee(paymentBuild);
        }

        if(paymentIncludes.contains("SECOND_CHILD_BARGAIN")) {
            paymentBuild = new SecondChildBargain(paymentBuild);
        }
        return paymentBuild;
    }

    @Override
    public void editPayment(PaymentActionDto paymentActionDto) throws Exception {
        Optional<Payment> paymentOptional = paymentRepository.findById(paymentActionDto.getPaymentId());

        if(!paymentOptional.isPresent()) {
            throw new Exception("That payment does not exist.");
        }

        Payment payment = paymentOptional.get();

        BasicFee paymentBuild = new BasicPayment();
        paymentBuild = decoratePayment(paymentActionDto, paymentBuild);
        Child child = childRepository.getOne(paymentActionDto.getChildId());
        Person payer = personRepository.getOne(paymentActionDto.getPayerId());

        payment.setName(paymentActionDto.getName());
        payment.setPaymentDate(new Date());
        paymentRepository.save(payment);
    }

    @Override
    public void deletePayment(PaymentActionDto paymentActionDto) throws Exception {

        Optional<Payment> paymentOptional = paymentRepository.findById(paymentActionDto.getPaymentId());

        if(!paymentOptional.isPresent()) {
            throw new Exception("That payment does not exist.");
        }

        paymentRepository.delete(paymentOptional.get());
    }

    @Override
    public List<Payment> getPaymentsByParentAndChild(PaymentActionDto paymentActionDto) {
        return paymentRepository.findAllByPersonIdAndChildId(paymentActionDto.getPayerId(), paymentActionDto.getChildId());
    }

    @Override
    public Payment getPaymentById(PaymentActionDto paymentActionDto) {
        return paymentRepository.findById(paymentActionDto.getPaymentId()).orElse(null);
    }
}
