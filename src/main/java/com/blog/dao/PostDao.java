package com.blog.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.blog.entity.Post;

@Repository
public interface PostDao {
	/**
	 * 添加帖子
	 * @param post
	 */
	public void addPost(Post post);
	/**
	 * 添加一条阅读数量
	 * @param postid
	 */
	public void addPostNum(int postid);
	/**
	 * 查找所有帖子
	 * @return
	 */
	public List<Post> findPostAll();
	/**
	 * 通过id查找帖子
	 */
	public Post findPostById(String id);

	/**
	 * 通过type查找帖子
	 */
	public  List<Post> findPostByType(String type);

	/**
	 * 通过city查找帖子
	 */
	public  List<Post> findPostByCity(String city);
	

	/**
	 * 通过id+type查找帖子
	 */
	public  List<Post> findPostByIdAndType(String id,String type);
	
	/**
	 * 通过用户id查找帖子
	 */
	public  List<Post> findPostByUid(String uid);
	/**
	 * 通过状态查找帖子
	 */
	public List<Post> findPostByState(String state);
	
	public void updatePost(Post post);
}
