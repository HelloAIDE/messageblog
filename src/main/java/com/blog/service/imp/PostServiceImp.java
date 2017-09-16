package com.blog.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blog.dao.PostDao;
import com.blog.dao.TypeDao;
import com.blog.entity.Post;
import com.blog.entity.Type;
import com.blog.service.PostService;
import com.blog.service.exception.PostServiceException;
import com.blog.util.SafeUtil;
import com.blog.util.UserUtil;

@Transactional
@Service
public class PostServiceImp implements PostService {
	@Autowired
	public PostDao dao;

	@Autowired
	public TypeDao typedao;

	public Post addPost(String title, String content, String city, String type, String uid, String resources) {
		Post post = new Post();
		if (title == null || title.trim().isEmpty()) {
			throw new PostServiceException("标题不能为空");
		}

		if (content == null || content.trim().isEmpty()) {
			throw new PostServiceException("内容不能为空");
		}

		if (city == null || city.trim().isEmpty()) {
			throw new PostServiceException("请选择城市");
		}

		if (type == null || type.trim().isEmpty()) {
			throw new PostServiceException("请选择类型");
		}

		if (uid == null || uid.trim().isEmpty()) {
			throw new PostServiceException("请登陆后操作");
		}

		if (resources != null && !resources.trim().isEmpty()) {
			post.setResources(resources);
		}

		post.setTitle(title);
		post.setContent(SafeUtil.strReplaceSensitive(content));
		post.setCity(city);
		post.setType(type);
		post.setuId(uid);
		post.setCreatetime(UserUtil.getStringTime());
		dao.addPost(post);
		return post;
	}

	public List<Post> getAllPost() {
		List<Post> list = dao.findPostAll();
		
		return replaceListType(list);
	}

	public Post findPostById(String id) {

		if (id == null) {
			throw new PostServiceException("您访问的数据不存在");
		}
		Post post = dao.findPostById(id);
		String ty = post.getType();
		Type typ = typedao.findTypeById(ty);
		String type = typ.getName();
		post.setType(type);
		return post;
	}
	public void deletePostById(String id) {
		Post post = dao.findPostById(id);
		if (post == null) {
			throw new PostServiceException("您访问的数据不存在");
		}
		System.out.println(post);
		post.setState("1");
		Type type = typedao.findTypeByName(post.getType());
		post.setType(type.getId());
		dao.updatePost(post);
	}

	private List<Post> replaceListType(List<Post> list) {
		List<Post> list1 = new ArrayList<Post>();
		for (int i = 0; i < list.size(); i++) {
			Post post = (Post) list.get(i);
			String ty = post.getType();
			Type typ = typedao.findTypeById(ty);
			String type = typ.getName();
			post.setType(type);
			list1.add(post);
		}
		return list1;
	}
}
