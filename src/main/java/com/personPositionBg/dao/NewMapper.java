package com.personPositionBg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.personPositionBg.entity.*;

public interface NewMapper {

	int queryForInt(@Param("title") String title);

	List<New> queryList(@Param("title") String title, @Param("rowNum") int rowNum, @Param("rows") int rows, String sort, String order);

	int add(New n);

	New selectById(String id);

	int edit(New n);

}
