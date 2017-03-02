package com.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.ReplyDao;
import com.blog.entity.Reply;
import com.blog.util.SafeUtil;
import com.blog.util.UserUtil;
@Service
public class ReplyServiceImp implements ReplyService {
	@Autowired
	public ReplyDao dao;
	
	public List<Reply> findAll() {
		return dao.findAll();
	}

	public void addReply(String content, String postId, String parentId,String name, String uId) {
		if(content==null||content.trim().isEmpty())
		{
			throw new ReplyServiceException("内容不能为空");
		}
		
		if(postId==null||postId.trim().isEmpty())
		{
			throw new ReplyServiceException("帖子id不能为空");
		}
		
		if(uId==null||uId.trim().isEmpty())
		{
			throw new ReplyServiceException("用户id不能为空");
		}
		
		Reply reply = new Reply();
		reply.setContent(SafeUtil.strReplaceSensitive(content));
		reply.setPostId(SafeUtil.strReplaceSensitive(postId));
		reply.setUserId(SafeUtil.strReplaceSensitive(uId));
		
		reply.setUserName(SafeUtil.strReplaceSensitive(name));
		reply.setCreateTime(UserUtil.getStringTime());
		if(parentId!=null&&!parentId.trim().isEmpty())
		{
			reply.setParentId(parentId);
		}
		dao.addReplye(reply);
	}

	public void update(String id, String content, String postId, String parentId, String uId) {
		if(id==null||id.trim().isEmpty())
		{
			throw new ReplyServiceException("回复id不能为空");
		}
		if(content==null||content.trim().isEmpty())
		{
			throw new ReplyServiceException("内容不能为空");
		}
		if(postId==null||postId.trim().isEmpty())
		{
			throw new ReplyServiceException("帖子id不能为空");
		}
		
		if(uId==null||uId.trim().isEmpty())
		{
			throw new ReplyServiceException("请登陆后进行回复");
		}
		Reply reply = dao.findReplyById(id);
		if(reply==null)
		{
			throw new ReplyServiceException("回复不存在");
		}
		reply.setContent(SafeUtil.strReplaceSensitive(content));
		reply.setPostId(SafeUtil.strReplaceSensitive(postId));
		reply.setUserId(SafeUtil.strReplaceSensitive(uId));
		if(parentId!=null&&!parentId.trim().isEmpty())
		{
			reply.setParentId(SafeUtil.strReplaceSensitive(parentId));
		}
		dao.update(reply);
		
	}

	public List<Reply> findReplyPostId(String pId) {
		if(pId==null||pId.trim().isEmpty())
		{
			throw new ReplyServiceException("参数不合法");
		}
		
		
		return dao.findReplyByPostId(pId);
	}

	public List<Reply> findReplyUserId(String uId) {
		if(uId==null||uId.trim().isEmpty())
		{
			throw new ReplyServiceException("参数不合法");
		}
		return dao.findReplyByUserId(uId);
	}
	private List<Reply> list;
	public List<Reply> findAllParent(String id) {
		list=null;
		list= new ArrayList<Reply>();
		Reply reply = dao.findReplyById(id);
		list.add(reply);
		isParent(reply.getParentId());
		System.out.println(list);
		return list;
	}
	private synchronized String isParent(String parentId){
		if(parentId==null)
		{
			return null;
		}
		Reply reply = dao.findReplyById(parentId);
		System.out.println("进行递归");
		System.out.println(reply);
		list.add(reply);
		if(isParent(reply.getParentId())==null){
			return null;
		}
		return reply.getParentId();
	}
}
