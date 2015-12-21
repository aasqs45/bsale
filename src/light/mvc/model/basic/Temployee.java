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
@Table(name = "t_employee")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Temployee  implements java.io.Serializable {
	
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
	private Tbranch branch;
	private Date createdatetime;
	
	
	public Temployee() {
	}
	
	public Temployee(Long id, String code, String name, String tel, Date hiredate, Integer sex, Integer age, String cardid, Integer basesalary, String email, String duty, Integer rate, String race, Tbranch branch, Date createdatetime) {
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
		this.branch = branch;
		this.createdatetime = createdatetime;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "hiredate", length = 19)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	public Tbranch getBranch() {
		return branch;
	}

	public void setBranch(Tbranch branch) {
		this.branch = branch;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdatetime", length = 19)
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	
}
