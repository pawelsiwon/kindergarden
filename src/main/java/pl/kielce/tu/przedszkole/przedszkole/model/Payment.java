package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;
import pl.kielce.tu.przedszkole.przedszkole.service.PaymentService.PaymentDecorator.BasicFee;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the PAYMENT database table.
 * 
 */
@Data
@Entity
public class Payment implements Serializable, BasicFee {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "payment_seq")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="PAYMENT_DATE")
	private Date paymentDate;

	//bi-directional many-to-one association to Child
	@ManyToOne
	@NotNull
	private Child child;

	//bi-directional many-to-one association to Expense
//	@ManyToOne
//	private Expense expense;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@NotNull
	@JoinColumn(name="WHO_PAYED")
	private Person person;

	@NotNull
	private BigDecimal cost;

	@NotNull
	private String name;

	private String description;

	public Payment() {
		this.cost = new BigDecimal(0);
		this.description = "";
	}
}