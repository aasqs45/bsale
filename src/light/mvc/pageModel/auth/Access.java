package light.mvc.pageModel.auth;

import java.util.Date;

public class Access implements java.io.Serializable {

	private static final long serialVersionUID = 3897938125416848315L;
	
	private Long pid;
	private String pname;

	private Long id;
	private Date createdatetime; // 创建时间
	private String name; // 名称
	private String url; // 菜单路径
	private String description; // 描述
	private String iconCls; // 图标
	private Integer seq; // 排序号
	private Integer accesstype; // 资源类型, 0菜单 1功能
	private Integer state; // 状态 0启用 1停用
	private String icon;

	public Access() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
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

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

}