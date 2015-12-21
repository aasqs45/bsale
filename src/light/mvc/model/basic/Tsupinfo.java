package light.mvc.model.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_sup_info")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tsupinfo  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private Tsupclass supclass;
	private String linkman;
	private String tel;
	private String address;
	private String phone;
	private String bank;
	private String account;
	private String email;
	private Date createdatetime;
	private Integer state;
	
	public Tsupinfo(){
	}
	
	public Tsupinfo(Long id, String code, String name, Tsupclass supclass, String linkman, String tel, String address, String phone, String bank, String account, String email, Date createdatetime, Integer state) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.supclass = supclass;
		this.linkman = linkman;
		this.tel = tel;
		this.address = address;
		this.phone = phone;
		this.bank = bank;
		this.account = account;
		this.email = email;
		this.createdatetime = createdatetime;
		this.state = state;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "supclass_id")
	public Tsupclass getSupclass() {
		return supclass;
	}

	public void setSupclass(Tsupclass supclass) {
		this.supclass = supclass;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdatetime", length = 19)
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	
}
