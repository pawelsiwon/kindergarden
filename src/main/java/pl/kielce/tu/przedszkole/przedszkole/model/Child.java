package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the CHILD database table.
 * 
 */
@Data
@Entity
public class Child implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "child_seq")
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

	//bi-directional many-to-many association to Person
	@ManyToMany(mappedBy="childs")
	private List<Person> people;

	//bi-directional many-to-one association to Classroom
	@ManyToOne
	@JoinColumn(name="GROUP_ID")
	private Classroom clazz;

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