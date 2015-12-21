package light.mvc.pageModel.basic;


public class Branchclass  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String name;
	private String code;
	private Integer isdefault;

	public Branchclass() {
		super();
	}
	

	public Branchclass(Long id, String name, String code, Integer isdefault) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.isdefault = isdefault;
	}


	public Integer getIsdefault() {
		return isdefault;
	}


	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}


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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
