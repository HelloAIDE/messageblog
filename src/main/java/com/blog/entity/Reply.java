package com.blog.entity;

public class Reply {
	public String id;
	public String postId;
	public String parentId;
	public String userName;
	public String userId;
	public String content;
	public String createTime;
	public String state;
	public String getId() {
		return id;
	}
	public Reply(String id, String postId, String parentId, String userId, String userName, String content,
			String createTime, String state) {
		super();
		this.id = id;
		this.postId = postId;
		this.parentId = parentId;
		this.userId = userId;
		this.userName = userName;
		this.content = content;
		this.createTime = createTime;
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Reply() {
		// TODO Auto-generated constructor stub
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Reply [id=" + id + ", postId=" + postId + ", parentId=" + parentId + ", userId=" + userId
				+ ", userName=" + userName + ", content=" + content + ", createTime=" + createTime + ", state=" + state
				+ "]";
	}
	
	
}
