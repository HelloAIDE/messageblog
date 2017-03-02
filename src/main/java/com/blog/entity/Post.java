package com.blog.entity;

import java.io.Serializable;

public class Post implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id ;
	public String title;
	public String content;
	public String city;
	public String type;
	public String uId;
	public String other;
	public String createtime;
	public String lastmodifytime;
	public String top;
	public String hot;
	public String essence;//精华
	public String number;
	public String resources;
	public String state;

	public Post(String id, String title, String content, String city, String type, String uId, String other,
			String createtime, String lastmodifytime, String top, String hot, String essence, String number,
			String resources, String state) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.city = city;
		this.type = type;
		this.uId = uId;
		this.other = other;
		this.createtime = createtime;
		this.lastmodifytime = lastmodifytime;
		this.top = top;
		this.hot = hot;
		this.essence = essence;
		this.number = number;
		this.resources = resources;
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public Post() {
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", city=" + city + ", type=" + type
				+ ", uId=" + uId + ", other=" + other + ", createtime=" + createtime + ", lastmodifytime="
				+ lastmodifytime + ", top=" + top + ", hot=" + hot + ", essence=" + essence + ", number=" + number
				+ ", resources=" + resources + ", state=" + state + "]";
	}

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getLastmodifytime() {
		return lastmodifytime;
	}

	public void setLastmodifytime(String lastmodifytime) {
		this.lastmodifytime = lastmodifytime;
	}

	public String getTop() {
		return top;
	}

	public void setTop(String top) {
		this.top = top;
	}

	public String getHot() {
		return hot;
	}

	public void setHot(String hot) {
		this.hot = hot;
	}

	public String getEssence() {
		return essence;
	}

	public void setEssence(String essence) {
		this.essence = essence;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
