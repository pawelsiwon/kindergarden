package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the "CLASS" database table.
 * 
 */
@Data
@Entity
@Table(name="CLASSROOM")
public class Class implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "classroom_seq")
	private long id;

	private String name;

	@Column(name="YEAR_END")
	private BigDecimal yearEnd;

	@Column(name="YEAR_START")
	private BigDecimal yearStart;

	//bi-directional many-to-one association to Child
	@OneToMany(mappedBy="clazz")
	private List<Child> childs;

	//bi-directional many-to-one association to Worker
	@ManyToOne
	@JoinColumn(name="TEACHER")
	private Person person;

	public Class() {
	}

	public Child addChild(Child child) {
		getChilds().add(child);
		child.setClazz(this);

		return child;
	}

	public Child removeChild(Child child) {
		getChilds().remove(child);
		child.setClazz(null);

		return child;
	}
}