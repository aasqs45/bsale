package light.mvc.model.basic;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_payment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tpayment  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private Integer paytype;  
	private Integer isvalid;
	private Integer isdefault;
	private Integer account;
	private String description;
	
	public Tpayment() {
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
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Integer getPaytype() {
		return paytype;
	}


	public void setPaytype(Integer paytype) {
		this.paytype = paytype;
	}


	public Integer getIsvalid() {
		return isvalid;
	}


	public void setIsvalid(Integer isvalid) {
		this.isvalid = isvalid;
	}


	public Integer getIsdefault() {
		return isdefault;
	}


	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}


	public Integer getAccount() {
		return account;
	}


	public void setAccount(Integer account) {
		this.account = account;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
}
