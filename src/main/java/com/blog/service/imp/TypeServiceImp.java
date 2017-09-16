package com.blog.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dao.TypeDao;
import com.blog.entity.Type;
import com.blog.service.TypeService;
import com.blog.service.exception.TypeServiceException;
import com.blog.util.Config;
import com.blog.util.SafeUtil;

@Service
public class TypeServiceImp implements TypeService {
	@Autowired
	public TypeDao dao;

	public void addType(String name, String image) {
		Type type;
		if (name == null || name.trim().isEmpty()) {
			throw new TypeServiceException("请输入分类名称");
		}
		if (image == null || image.trim().isEmpty()) {
			image = Config.DEFAULT_TYPE_IMAGE;
		}
		type = dao.findTypeByName(name);
		if (type != null) {
			throw new TypeServiceException("分类名称已存在");
		}
		type = new Type();
		type.setName(SafeUtil.strReplaceSensitive(name));
		type.setImage(SafeUtil.strReplaceSensitive(image));
		dao.addType(type);
	}

	public void updateType(String id, String name, String image, String managerId) {
		if (id == null || id.trim().isEmpty()) {
			throw new TypeServiceException("要修改的分类不存在");
		}
		if (name == null || name.trim().isEmpty()) {
			throw new TypeServiceException("分类名称不能为空");
		}
		Type type = dao.findTypeById(id);
		if (type == null) {
			throw new TypeServiceException("要修改的分类不存在");
		}
		type.setName(name);
		if (image != null && !image.trim().isEmpty()) {
			type.setImage(SafeUtil.strReplaceSensitive(image));
		}
		if (managerId != null && !managerId.trim().isEmpty()) {
			type.setManagerId(SafeUtil.strReplaceSensitive(managerId));
		}
		dao.update(type);
	}
	public List<Type> findAllType() {
		List<Type> types = dao.findAll();
		if (types == null) {
			types=new ArrayList<Type>();
		}
		return types;
	}
	public Type findTypeById(String id) {
		if (id == null || id.trim().isEmpty()) {
			throw new TypeServiceException("参数不合法");
		}
		Type type = dao.findTypeById(id);
		if (type == null) {
			throw new TypeServiceException("分类不存在");
		}
		return type;
	}

	public Type findTypeByName(String name) {
		if (name == null || name.trim().isEmpty()) {
			throw new TypeServiceException("参数不合法");
		}
		Type type = dao.findTypeByName(name);
		if (type == null) {
			throw new TypeServiceException("分类不存在");
		}
		return type;
	}
}
