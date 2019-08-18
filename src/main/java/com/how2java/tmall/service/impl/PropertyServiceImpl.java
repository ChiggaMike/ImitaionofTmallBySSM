package com.how2java.tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.how2java.tmall.mapper.PropertyMapper;
import com.how2java.tmall.pojo.Property;
import com.how2java.tmall.pojo.PropertyExample;
import com.how2java.tmall.service.PropertyService;
@Service
public class PropertyServiceImpl implements PropertyService{
	
	@Autowired
	PropertyMapper propertyMapper;
	
	public void add(Property p){
		propertyMapper.insert(p);
	}
	
	public void delete(int id){
		propertyMapper.deleteByPrimaryKey(id);
	}
	
	public void update(Property p){
		propertyMapper.updateByPrimaryKeySelective(p);
	}
	
	public Property get(int id){
		return propertyMapper.selectByPrimaryKey(id);
	}
	
	public List list(int cid){
		PropertyExample example = new PropertyExample();
		example.createCriteria().andCidEqualTo(cid);
		example.setOrderByClause("id desc");
		return propertyMapper.selectByExample(example);
	}

}
