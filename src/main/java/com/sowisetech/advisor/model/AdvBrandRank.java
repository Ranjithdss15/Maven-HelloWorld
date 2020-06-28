package com.sowisetech.advisor.model;

public class AdvBrandRank {
	private long advBrandRankId;
	private String advId;
	private long prodId;
	private long brandId;
	private long ranking;
	private String brand;
	private String delete_flag;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDelete_flag() {
		return delete_flag;
	}

	public void setDelete_flag(String delete_flag) {
		this.delete_flag = delete_flag;
	}

	public long getAdvBrandRankId() {
		return advBrandRankId;
	}

	public void setAdvBrandRankId(long advBrandRankId) {
		this.advBrandRankId = advBrandRankId;
	}

	public String getAdvId() {
		return advId;
	}

	public void setAdvId(String advId) {
		this.advId = advId;
	}

	public long getProdId() {
		return prodId;
	}

	public void setProdId(long prodId) {
		this.prodId = prodId;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public long getRanking() {
		return ranking;
	}

	public void setRanking(long ranking) {
		this.ranking = ranking;
	}

}
