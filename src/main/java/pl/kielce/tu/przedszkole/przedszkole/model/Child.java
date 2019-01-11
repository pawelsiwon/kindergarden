package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the CHILD database table.
 * 
 */
@Data
@Entity
public class Child implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	@Column(name="ADMISSION_DATE")
	private Date admissionDate;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private String city;

	private String comments;

	@Temporal(TemporalType.DATE)
	@Column(name="DISCHARGE_DATE")
	private Date dischargeDate;

	private String name;

	@Column(name="PERSON_ID")
	private BigDecimal personId;

	private String pesel;

	private String postale;

	private String street;

	@Column(name="STREET_ADDRESS")
	private BigDecimal streetAddress;

	private BigDecimal suite;

	private String surname;

	//bi-directional many-to-many association to Parent
	@ManyToMany(mappedBy="childs")
	private List<Parent> parents;

	//bi-directional many-to-one association to Class
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private Class clazz;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="child")
	private List<Payment> payments;

	public Child() {
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setChild(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setChild(null);

		return payment;
	}

}