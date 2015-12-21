package light.mvc.pageModel.basic;

import java.util.Date;

public class Employee  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private String tel;
	private Date hiredate;
	private Integer sex;  
	private Integer age;  
	private String cardid;
	private Integer basesalary;
	private String email;
	private String duty;
	private Integer rate;
	private String race;
	private Long branchId;
	private String branchName;


	public Employee(Long id, String code, String name, String tel, Date hiredate, Integer sex, Integer age, String cardid, Integer basesalary, String email, String duty, Integer rate, String race, Long branchId, String branchName) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.tel = tel;
		this.hiredate = hiredate;
		this.sex = sex;
		this.age = age;
		this.cardid = cardid;
		this.basesalary = basesalary;
		this.email = email;
		this.duty = duty;
		this.rate = rate;
		this.race = race;
		this.branchId = branchId;
		this.branchName = branchName;
	}

	public Employee() {
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public Integer getBasesalary() {
		return basesalary;
	}

	public void setBasesalary(Integer basesalary) {
		this.basesalary = basesalary;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
