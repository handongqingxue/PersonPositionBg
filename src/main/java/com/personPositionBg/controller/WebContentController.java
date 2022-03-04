package com.personPositionBg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personPositionBg.entity.*;
import com.personPositionBg.service.*;

@Controller
@RequestMapping(WebContentController.MODULE_NAME)
public class WebContentController {

	@Autowired
	private NewService newService;
	public static final String MODULE_NAME="/background/webContent";
	
	@RequestMapping(value="/new/new")
	public String goNewNew(HttpServletRequest request) {
		
		return MODULE_NAME+"/new/new";
	}
	
	@RequestMapping(value="/new/edit")
	public String goNewEdit(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		New n = newService.selectById(id);
		request.setAttribute("n", n);
		
		return MODULE_NAME+"/new/edit";
	}
	
	@RequestMapping(value="/new/list")
	public String goNewList() {
		
		return MODULE_NAME+"/new/list";
	}
	
	@RequestMapping(value="/queryNewList")
	@ResponseBody
	public Map<String, Object> queryNewList(String title,int page,int rows,String sort,String order) {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		int count = newService.queryForInt(title);
		List<New> newList=newService.queryList(title, page, rows, sort, order);
		
		jsonMap.put("total", count);
		jsonMap.put("rows", newList);
		
		return jsonMap;
	}
	
	@RequestMapping(value="/newNew")
	public String newNew(New n) {
		
		newService.add(n);
		
		return MODULE_NAME+"/new/list";
	}
	
	@RequestMapping(value="/newEdit")
	public String newEdit(New n) {
		
		int i=newService.edit(n);
		
		return MODULE_NAME+"/new/list";
	}
}
