package light.mvc.model.basic;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "t_item")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Titem  implements java.io.Serializable {
	
	private static final long serialVersionUID = -2428693430866228791L;
	
	private Long id;
	private String itemno;
	private String barcode;
	private String name;
	private Titemclass itemclass;
	private String unit;
	private Titembrand itembrand;
	private Titemproperty itemproperty;
	private Double purchaseprice;
	private Double retailprice;
	private Double wholesaleprice;
	private Integer isbranch;  
	private Integer state; 
	private Date createdatetime;

	public Titem() {
		super();
	}

	public Titem(Long id, String itemno, String barcode, String name, Titemclass itemclass, String unit, Titembrand itembrand, Titemproperty itemproperty, Double purchaseprice, Double retailprice, Double wholesaleprice, Integer isbranch, Integer state, Date createdatetime) {
		super();
		this.id = id;
		this.itemno = itemno;
		this.barcode = barcode;
		this.name = name;
		this.itemclass = itemclass;
		this.unit = unit;
		this.itembrand = itembrand;
		this.itemproperty = itemproperty;
		this.purchaseprice = purchaseprice;
		this.retailprice = retailprice;
		this.wholesaleprice = wholesaleprice;
		this.isbranch = isbranch;
		this.state = state;
		this.createdatetime = createdatetime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemclass_id")
	public Titemclass getItemclass() {
		return itemclass;
	}

	public void setItemclass(Titemclass itemclass) {
		this.itemclass = itemclass;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id")
	public Titembrand getItembrand() {
		return itembrand;
	}

	public void setItembrand(Titembrand itembrand) {
		this.itembrand = itembrand;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "property_id")
	public Titemproperty getItemproperty() {
		return itemproperty;
	}

	public void setItemproperty(Titemproperty itemproperty) {
		this.itemproperty = itemproperty;
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

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdatetime", length = 19)
	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	
}
