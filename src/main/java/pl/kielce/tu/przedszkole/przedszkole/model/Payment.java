package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the PAYMENT database table.
 * 
 */
@Data
@Entity
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "payment_seq")
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="PAYMENT_DATE")
	private Date paymentDate;

	//bi-directional many-to-one association to Child
	@ManyToOne
	private Child child;

	//bi-directional many-to-one association to Expense
	@ManyToOne
	private Expense expense;

	//bi-directional many-to-one association to Person
	@ManyToOne
	@JoinColumn(name="WHO_PAYED")
	private Person person;

	public Payment() {
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Person getPerson() {
		return this.person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}