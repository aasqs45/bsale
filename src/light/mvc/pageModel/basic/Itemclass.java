package light.mvc.pageModel.basic;


public class Itemclass  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String code;
	private String name;
	private Long itemclassId;
	private Long itemclassName;

	public Itemclass() {
		super();
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

	public Long getItemclassId() {
		return itemclassId;
	}

	public void setItemclassId(Long itemclassId) {
		this.itemclassId = itemclassId;
	}

	public Long getItemclassName() {
		return itemclassName;
	}

	public void setItemclassName(Long itemclassName) {
		this.itemclassName = itemclassName;
	}
	

}
