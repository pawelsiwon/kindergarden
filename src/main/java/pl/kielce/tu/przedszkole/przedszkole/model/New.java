package pl.kielce.tu.przedszkole.przedszkole.model;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the NEWS database table.
 * 
 */
@Data
@Entity
@Table(name="NEWS")
public class New implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String contetnt;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String title;

	//bi-directional many-to-one association to Worker
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private Worker worker;

	public New() {
	}
}