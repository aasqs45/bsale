package light.mvc.model.auth;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_role")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Trole  implements java.io.Serializable {
	
	private static final long serialVersionUID = -7509069780835660418L;
	
	protected Long id;
	private String name; // 角色名称
	private Integer seq; // 排序号
	private Integer isdefault; // 是否默认
	private String description; // 备注
	private Set<Taccess> accesss = new HashSet<Taccess>(0);
	private Set<Tuser> users = new HashSet<Tuser>(0);

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

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_role_access", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "access_id", nullable = false, updatable = false) })
	@OrderBy("id ASC")
	public Set<Taccess> getAccesss() {
		return accesss;
	}

	public void setAccesss(Set<Taccess> accesss) {
		this.accesss = accesss;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_user_role", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "user_id", nullable = false, updatable = false) })
	@OrderBy("id ASC")
	public Set<Tuser> getUsers() {
		return users;
	}

	public void setUsers(Set<Tuser> users) {
		this.users = users;
	}

}
