package light.mvc.model.auth;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import light.mvc.model.basic.Tbranch;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_user")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tuser  implements java.io.Serializable {
	
	private static final long serialVersionUID = 6238285790991537093L;
	
	protected Long id;
	private String loginname; // 登录名
	private String password; // 密码
	private String name; // 姓名
	private Integer sex; // 性别
	private Integer age; // 年龄
	private Date createdatetime; // 创建时间
	private Integer usertype; // 用户类型
	private Integer isdefault; // 是否默认
	private Integer state; // 状态
	private Tbranch branch;
	private Set<Trole> roles = new HashSet<Trole>(0);

	public Tuser() {
		super();
	}

	public Tuser(String loginname, String password, String name, Integer sex, Integer age, Date createdatetime,
			Integer usertype, Integer isdefault, Integer state) {
		super();
		this.loginname = loginname;
		this.password = password;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.createdatetime = createdatetime;
		this.usertype = usertype;
		this.isdefault = isdefault;
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
	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdatetime", length = 19)
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	public Set<Trole> getRoles() {
		return roles;
	}

	public void setRoles(Set<Trole> roles) {
		this.roles = roles;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branch_id")
	public Tbranch getBranch() {
		return branch;
	}

	public void setBranch(Tbranch branch) {
		this.branch = branch;
	}

}