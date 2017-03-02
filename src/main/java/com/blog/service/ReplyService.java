package com.blog.service;

import java.util.List;

import com.blog.entity.Reply;

public interface ReplyService {
	public List<Reply> findAll();
	public void addReply(String content,String postId,String parentId,String name,String uId);
	public void update(String id,String content,String postId,String parentId,String uId);
	public List<Reply> findReplyPostId(String pId);
	public List<Reply> findReplyUserId(String uId);
	public List<Reply> findAllParent(String id);
}
