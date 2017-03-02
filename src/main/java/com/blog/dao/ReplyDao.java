package com.blog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.entity.Reply;
@Repository
public interface ReplyDao {
	public List<Reply> findAll();
	public void addReplye(Reply reply);
	public void update(Reply reply);
	public Reply findReplyById(String id);
	public List<Reply> findReplyByPostId(String pId);
	public List<Reply> findReplyByUserId(String uId);
}
