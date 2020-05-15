package com.fabric.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabric.dao.OrgDao;
import com.fabric.pojo.Organization;
import com.fabric.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService{

	@Autowired
	private OrgDao orgDao;
	
	@Override
	public Organization getOrg(String name) {
		// TODO Auto-generated method stub
		return orgDao.getOrg(name);
	}

}
