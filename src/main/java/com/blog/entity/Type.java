package com.blog.entity;

public class Type {
	public String id;
	public String name;
	public String image;
	public String managerId;
	public Type(String id, String name, String image, String managerId) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.managerId = managerId;
	}
	public Type() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Type [id=" + id + ", name=" + name + ", image=" + image + ", managerId=" + managerId + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
}
