package light.mvc.model.basic;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "t_branch")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Tbranch  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private String icon;
	private Integer seq;
	private String address;
	private String manager;
	private String tel;
	private Integer branchtype;  //0为机构  1为仓库  2为部门
	private Date createdatetime;
	private Tbranch branch;
	private Tbranchclass branchclass;
	private Set<Tbranch> branchs = new HashSet<Tbranch>(0);

	public Tbranch() {
		super();
	}

	
	public Tbranch(Long id, String code, String name, String icon, Integer seq, String address, String manager, String tel, Integer branchtype, Date createdatetime, Tbranch branch, Tbranchclass branchclass, Set<Tbranch> branchs) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.icon = icon;
		this.seq = seq;
		this.address = address;
		this.manager = manager;
		this.tel = tel;
		this.branchtype = branchtype;
		this.createdatetime = createdatetime;
		this.branch = branch;
		this.branchclass = branchclass;
		this.branchs = branchs;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
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

	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotBlank
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	
	public String getManager() {
		return manager;
	}


	public void setManager(String manager) {
		this.manager = manager;
	}


	public String getTel() {
		return tel;
	}


	public void setTel(String tel) {
		this.tel = tel;
	}


	public Integer getBranchtype() {
		return branchtype;
	}


	public void setBranchtype(Integer branchtype) {
		this.branchtype = branchtype;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "branchclass_id")
	public Tbranchclass getBranchclass() {
		return branchclass;
	}

	public void setBranchclass(Tbranchclass branchclass) {
		this.branchclass = branchclass;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public Set<Tbranch> getBranchs() {
		return branchs;
	}

	public void setBranchs(Set<Tbranch> branchs) {
		this.branchs = branchs;
	}

}
