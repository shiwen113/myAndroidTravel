package com.gem.scenery.entity;

public class Senery {
	private Integer id;
	private String name;
	private String urlImage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Senery [name=" + name + ", urlImage=" + urlImage + "]";
	}
	
}
