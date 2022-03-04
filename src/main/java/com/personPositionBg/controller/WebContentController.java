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
import com.personPositionBg.util.*;

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
	
	@RequestMapping(value="/new/detail")
	public String goNewDetail(HttpServletRequest request) {
		
		String id = request.getParameter("id");
		New n = newService.selectById(id);
		request.setAttribute("n", n);
		
		return MODULE_NAME+"/new/detail";
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

	@RequestMapping(value="/deleteNew",produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String deleteNew(String ids) {
		//TODO 针对分类的动态进行实时调整更新
		int count=newService.deleteByIds(ids);
		PlanResult plan=new PlanResult();
		String json;
		if(count==0) {
			plan.setStatus(0);
			plan.setMsg("删除新闻失败");
			json=JsonUtil.getJsonFromObject(plan);
		}
		else {
			plan.setStatus(1);
			plan.setMsg("删除新闻成功");
			json=JsonUtil.getJsonFromObject(plan);
		}
		return json;
	}
}
