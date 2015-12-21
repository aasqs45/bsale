package light.mvc.pageModel.auth;

public class Role implements java.io.Serializable {

	private static final long serialVersionUID = -6478933308731810025L;
	
	private Long id;
	private String name; // 角色名称
	private Integer seq; // 排序号
	private Integer isdefault; // 是否默认
	private String description; // 备注

	private String accessIds;
	private String accessNames;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getAccessIds() {
		return accessIds;
	}

	public void setAccessIds(String accessIds) {
		this.accessIds = accessIds;
	}

	public String getAccessNames() {
		return accessNames;
	}

	public void setAccessNames(String accessNames) {
		this.accessNames = accessNames;
	}

}
