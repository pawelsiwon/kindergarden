package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PARENT database table.
 * 
 */
@Data
@Entity
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "person_seq")
	private long id;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private String city;

	private String email;

	private String login;

	private String name;

	private String password;

	private String pesel;

	private String phonenumber;

	private String postale;

	private String street;

	@Column(name="STREET_ADDRESS")
	private BigDecimal streetAddress;

	private BigDecimal suite;

	private String surname;

	//bi-directional many-to-many association to Child
	@ManyToMany
	@JoinTable(
		name="ANCESTOR"
		, joinColumns={
			@JoinColumn(name="PARENT_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="CHILD_ID")
			}
		)
	private List<Child> childs;

	private String role;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="person")
	private List<Payment> payments;

	public Person() {
	}

	public Payment addPayment(Payment payment) {
		getPayments().add(payment);
		payment.setPerson(this);

		return payment;
	}

	public Payment removePayment(Payment payment) {
		getPayments().remove(payment);
		payment.setPerson(null);

		return payment;
	}

}