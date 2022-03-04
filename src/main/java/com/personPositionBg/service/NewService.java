package com.personPositionBg.service;

import java.util.List;

import com.personPositionBg.entity.New;

public interface NewService {

	int queryForInt(String title);

	List<New> queryList(String title, int page, int rows, String sort, String order);

	int add(New n);

	New selectById(String id);

	int edit(New n);

}
