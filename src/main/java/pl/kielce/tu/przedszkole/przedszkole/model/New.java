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
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "news_seq")
	private long id;

	private String contetnt;

	@Temporal(TemporalType.DATE)
	@Column(name="CREATED_DATE")
	private Date createdDate;

	private String title;

	//bi-directional many-to-one association to Worker
	@ManyToOne
	@JoinColumn(name="CREATED_BY")
	private Person person;

	public New() {
	}
}