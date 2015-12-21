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
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_access")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Taccess  implements java.io.Serializable {

	private static final long serialVersionUID = -6077231144063931002L;
	
	protected Long id;
	private Date createdatetime; // 创建时间
	private String name; // 名称
	private String url; // 菜单路径
	private String description; // 描述
	private String icon; // 图标
	private Integer seq; // 排序号
	private Integer accesstype; // 资源类型, 0菜单 1功能
	private Taccess access; // 父级
	private Integer state; // 状态 0启用 1停用
	private Set<Trole> roles = new HashSet<Trole>(0);
	private Set<Taccess> accesss = new HashSet<Taccess>(0);

	public Taccess() {
	}

	public Taccess(Long id, Date createdatetime, String name, String url, String description, String icon,
			Integer seq, Integer accesstype, Taccess access, Integer state) {
		super();
		this.id = id;
		this.createdatetime = createdatetime;
		this.name = name;
		this.url = url;
		this.description = description;
		this.icon = icon;
		this.seq = seq;
		this.accesstype = accesstype;
		this.access = access;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDATETIME", length = 19)
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getAccesstype() {
		return accesstype;
	}

	public void setAccesstype(Integer accesstype) {
		this.accesstype = accesstype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public Taccess getAccess() {
		return access;
	}

	public void setAccess(Taccess access) {
		this.access = access;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_role_access", joinColumns = { @JoinColumn(name = "access_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) })
	@OrderBy("id ASC")
	public Set<Trole> getRoles() {
		return roles;
	}

	public void setRoles(Set<Trole> roles) {
		this.roles = roles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "access")
	public Set<Taccess> getAccesss() {
		return accesss;
	}

	public void setAccesss(Set<Taccess> accesss) {
		this.accesss = accesss;
	}

}