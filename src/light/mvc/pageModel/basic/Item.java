package light.mvc.pageModel.basic;

import java.util.Date;


public class Item  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String itemno;
	private String barcode;
	private String name;
	private Long itemclassId;
	private String itemclassName;
	private String unit;
	private Long itembrandId;
	private String itembrandName;
	private Long itempropertyId;
	private String itempropertyName;
	private Double purchaseprice;
	private Double retailprice;
	private Double wholesaleprice;
	private Integer isbranch;  
	private Integer state; 
	private Date createdatetime;

	public Item() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getItemno() {
		return itemno;
	}


	public void setItemno(String itemno) {
		this.itemno = itemno;
	}


	public String getBarcode() {
		return barcode;
	}


	public void setBarcode(String barcode) {
		this.barcode = barcode;
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


	public String getItemclassName() {
		return itemclassName;
	}


	public void setItemclassName(String itemclassName) {
		this.itemclassName = itemclassName;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public Long getItembrandId() {
		return itembrandId;
	}


	public void setItembrandId(Long itembrandId) {
		this.itembrandId = itembrandId;
	}


	public String getItembrandName() {
		return itembrandName;
	}


	public void setItembrandName(String itembrandName) {
		this.itembrandName = itembrandName;
	}


	public Long getItempropertyId() {
		return itempropertyId;
	}


	public void setItempropertyId(Long itempropertyId) {
		this.itempropertyId = itempropertyId;
	}


	public String getItempropertyName() {
		return itempropertyName;
	}


	public void setItempropertyName(String itempropertyName) {
		this.itempropertyName = itempropertyName;
	}


	public Double getPurchaseprice() {
		return purchaseprice;
	}


	public void setPurchaseprice(Double purchaseprice) {
		this.purchaseprice = purchaseprice;
	}


	public Double getRetailprice() {
		return retailprice;
	}


	public void setRetailprice(Double retailprice) {
		this.retailprice = retailprice;
	}


	public Double getWholesaleprice() {
		return wholesaleprice;
	}


	public void setWholesaleprice(Double wholesaleprice) {
		this.wholesaleprice = wholesaleprice;
	}


	public Integer getIsbranch() {
		return isbranch;
	}


	public void setIsbranch(Integer isbranch) {
		this.isbranch = isbranch;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public Date getCreatedatetime() {
		return createdatetime;
	}


	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	
}
