package light.mvc.pageModel.basic;

import java.util.Date;


public class Supinfo  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private String linkman;
	private String tel;
	private String address;
	private String phone;
	private String bank;
	private String account;
	private String email;
	private Date createdatetime;
	private Integer state;
	private Long supclassId;
	private String supclassName;
	
	public Supinfo(){
	}
	
	public Supinfo(Long id, String code, String name, String linkman, String tel, String address, String phone, String bank, String account, String email, Date createdatetime, Integer state, Long supclassId, String supclassName) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.linkman = linkman;
		this.tel = tel;
		this.address = address;
		this.phone = phone;
		this.bank = bank;
		this.account = account;
		this.email = email;
		this.createdatetime = createdatetime;
		this.state = state;
		this.supclassId = supclassId;
		this.supclassName = supclassName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Long getSupclassId() {
		return supclassId;
	}

	public void setSupclassId(Long supclassId) {
		this.supclassId = supclassId;
	}

	public String getSupclassName() {
		return supclassName;
	}

	public void setSupclassName(String supclassName) {
		this.supclassName = supclassName;
	}

	
}
