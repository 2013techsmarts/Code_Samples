package org.smarttechie.restdemo.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Product {

	@Id
	@GeneratedValue 
	private String id;
	private String title;
	private String displayName;
	@Column(columnDefinition = "VARCHAR(5000)")
	private String shortDesc;
	@Column(columnDefinition = "VARCHAR(5000)")
	private String longDesc;
	private Double listPrice;
	private Double salePrice;
	private String upc;
	private String mpn;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Set<Review> reviews;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Set<TechSpecs> techSpecs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public String getLongDesc() {
		return longDesc;
	}
	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}
	public Double getListPrice() {
		return listPrice;
	}
	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public String getMpn() {
		return mpn;
	}
	public void setMpn(String mpn) {
		this.mpn = mpn;
	}
	
	public Set<Review> getReviews() {
		return reviews;
	}
	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
	public Set<TechSpecs> getTechSpecs() {
		return techSpecs;
	}
	public void setTechSpecs(Set<TechSpecs> techSpecs) {
		this.techSpecs = techSpecs;
	}
}
