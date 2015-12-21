package light.mvc.pageModel.basic;

import java.util.Date;

public class Branch implements java.io.Serializable {

	private static final long serialVersionUID = 1849372600306647916L;
	
	private String iconCls;
	private Long pid;
	private String pname;
	
	private Long branchclassId;
	private String branchclassName;
	
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

	public Branch() {
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Long getBranchclassId() {
		return branchclassId;
	}

	public void setBranchclassId(Long branchclassId) {
		this.branchclassId = branchclassId;
	}

	public String getBranchclassName() {
		return branchclassName;
	}

	public void setBranchclassName(String branchclassName) {
		this.branchclassName = branchclassName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	
}
