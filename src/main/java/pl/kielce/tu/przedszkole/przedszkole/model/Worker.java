package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the WORKER database table.
 * 
 */
@Data
@Entity
public class Worker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String active;

	@Temporal(TemporalType.DATE)
	private Date birthdate;

	private String city;

	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name="HIRE_DATE")
	private Date hireDate;

	@Temporal(TemporalType.DATE)
	@Column(name="LATOFF_DATE")
	private Date latoffDate;

	private String login;

	private String name;

	private String password;

	@Column(name="PERSON_ID")
	private BigDecimal personId;

	private String pesel;

	private String phonenumber;

	private String postale;

	private BigDecimal salary;

	private String street;

	@Column(name="STREET_ADDRESS")
	private BigDecimal streetAddress;

	private BigDecimal suite;

	private String surname;

	//bi-directional many-to-one association to Class
	@OneToMany(mappedBy="worker")
	private List<Class> clazzs;

	//bi-directional many-to-one association to New
	@OneToMany(mappedBy="worker")
	private List<New> news;

	//bi-directional many-to-one association to Role
	@ManyToOne
	private Role role;

	public Worker() {
	}



}