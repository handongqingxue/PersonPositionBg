package com.personPositionBg.service.serviceImpl;

import java.util.Arrays;
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

	@Override
	public New selectById(String id) {
		// TODO Auto-generated method stub
		return newDao.selectById(id);
	}

	@Override
	public int edit(New n) {
		// TODO Auto-generated method stub
		return newDao.edit(n);
	}

	@Override
	public int deleteByIds(String ids) {
		// TODO Auto-generated method stub
		int count=0;
		List<String> idList = Arrays.asList(ids.split(","));
		count=newDao.deleteByIds(idList);
		return count;
	}

}
