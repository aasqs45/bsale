package light.mvc.model.basic;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_item_class")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Titemclass  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private Titemclass itemclass;

	public Titemclass() {
		super();
	}
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Titemclass getItemclass() {
		return itemclass;
	}


	public void setItemclass(Titemclass itemclass) {
		this.itemclass = itemclass;
	}

	
}
