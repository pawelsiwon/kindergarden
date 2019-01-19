package pl.kielce.tu.przedszkole.przedszkole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.kielce.tu.przedszkole.przedszkole.model.Payment;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findAllByChildId(Long childId);
    List<Payment> findAllByPersonId(Long payerId);
    List<Payment> findAllByPersonIdAndChildId(Long payerId, Long childId);
}

