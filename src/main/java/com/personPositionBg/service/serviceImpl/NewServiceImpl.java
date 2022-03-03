package com.personPositionBg.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personPositionBg.dao.*;
import com.personPositionBg.entity.*;
import com.personPositionBg.service.*;

@Service
public class NewServiceImpl implements NewService {

	@Autowired
	private NewMapper newDao;
	
	@Override
	public int queryForInt(String title) {
		// TODO Auto-generated method stub
		return newDao.queryForInt(title);
	}

	@Override
	public List<New> queryList(String title, int page, int rows, String sort, String order) {
		// TODO Auto-generated method stub
		return newDao.queryList(title, (page-1)*rows, rows, sort, order);
	}

	@Override
	public int add(New n) {
		// TODO Auto-generated method stub
		return newDao.add(n);
	}

}
