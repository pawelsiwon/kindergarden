package pl.kielce.tu.przedszkole.przedszkole.model;

import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the "ROLE" database table.
 * 
 */
@Data
@Entity
@Table(name="\"ROLE\"")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String name;

	//bi-directional many-to-one association to Parent
	@OneToMany(mappedBy="role")
	private List<Parent> parents;

	//bi-directional many-to-many association to Action
	@ManyToMany
	@JoinTable(
		name="PRIVILEGE"
		, joinColumns={
			@JoinColumn(name="ROLE_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="ACTION_ID")
			}
		)
	private List<Action> actions;

	//bi-directional many-to-one association to Worker
	@OneToMany(mappedBy="role")
	private List<Worker> workers;

	public Role() {
	}

	public Parent addParent(Parent parent) {
		getParents().add(parent);
		parent.setRole(this);

		return parent;
	}

	public Parent removeParent(Parent parent) {
		getParents().remove(parent);
		parent.setRole(null);

		return parent;
	}

	public Worker addWorker(Worker worker) {
		getWorkers().add(worker);
		worker.setRole(this);

		return worker;
	}

	public Worker removeWorker(Worker worker) {
		getWorkers().remove(worker);
		worker.setRole(null);

		return worker;
	}

}