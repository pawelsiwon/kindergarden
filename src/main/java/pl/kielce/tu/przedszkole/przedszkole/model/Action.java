package pl.kielce.tu.przedszkole.przedszkole.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * The persistent class for the "ACTION" database table.
 * 
 */
@Data
@Entity
@Table(name="\"ACTION\"")
public class Action implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String classname;

	private String name;

	//bi-directional many-to-many association to Role
	@ManyToMany(mappedBy="actions")
	private List<Role> roles;

	public Action() {
	}
}