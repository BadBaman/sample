package com.fabric.dao;

import org.apache.ibatis.annotations.Param;

import com.fabric.pojo.Organization;


public interface OrgDao {


	public Organization getOrg(@Param("orgname")String orgname);
	
}
