package com.shf.web.bean;

/*
 * 商品的信息
 */
public class ShopInfo {

	private int id;
	private String name;
	private String imagePath;
	private double price;

	public ShopInfo() {
	}

	public ShopInfo(int id, String name, String imagePath, double price) {
		this.id = id;
		this.name = name;
		this.imagePath = imagePath;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ShopInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", imagePath='" + imagePath + '\'' +
				", price=" + price +
				'}';
	}
}
