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

	//bi-directional many-to-one association to Parent
	@ManyToOne
	@JoinColumn(name="WHO_PAYED")
	private Parent parent;

	public Payment() {
	}

	public void setExpense(Expense expense) {
		this.expense = expense;
	}

	public Parent getParent() {
		return this.parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

}