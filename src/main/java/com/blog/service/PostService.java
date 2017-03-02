package com.blog.service;

import java.util.List;

import com.blog.entity.Post;

public interface PostService {
	/**
	 * 添加帖子
	 * @param title
	 * @param content
	 * @param city
	 * @param type
	 * @param uid
	 * @param resources
	 */
	public void addPost(String title,String content,String city,String type,String uid,String resources);
	/**
	 * 获取所有帖子
	 * @return
	 */
	public  List<Post> getAllPost();
	
	public Post findPostById(String id);
	
	public void deletePostById(String id);
	
}
