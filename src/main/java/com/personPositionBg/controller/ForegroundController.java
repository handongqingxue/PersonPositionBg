package com.personPositionBg.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personPositionBg.entity.*;
import com.personPositionBg.service.*;
import com.personPositionBg.util.StringUtils;

@Controller
@RequestMapping(ForegroundController.MODULE_NAME)
public class ForegroundController {

	@Autowired
	private NewService newService;
	public static final String MODULE_NAME="/foreground";

	@RequestMapping(value="/getNewList")
	@ResponseBody
	public void getNewList(int page,int rows,HttpServletResponse response) {
		try {
			List<New> newList=newService.queryList(null, page, rows, null, null);
			String jsonpCallback=null;
			if(newList.size()==0) {
				jsonpCallback="jsonpCallback(\"{\\\"status\\\":\\\"no\\\"}\")";
			}
			else {
				StringBuilder newListSB=new StringBuilder();
				newListSB.append("[");
				int newListSize = newList.size();
				for (int i = 0; i <newListSize ; i++) {
					New n = newList.get(i);
					newListSB.append("{\\\"id\\\":\\\"");
					newListSB.append(n.getId());
					newListSB.append("\\\",\\\"title\\\":\\\"");
					newListSB.append(n.getTitle());
					newListSB.append("\\\",\\\"createTimeYmd\\\":\\\"");
					newListSB.append(n.getCreateTimeYmd());
					if(i<newListSize-1)
						newListSB.append("\\\"},");
					else
						newListSB.append("\\\"}");
				}
				newListSB.append("]");
				jsonpCallback="jsonpCallback(\"{\\\"status\\\":\\\"ok\\\",\\\"list\\\":"+newListSB.toString()+"}\")";
			}
			response.getWriter().print(jsonpCallback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value="/getNewById")
	@ResponseBody
	public void getNewById(String id, HttpServletResponse response) {
		//https://blog.csdn.net/east123321/article/details/80402295
		try {
			System.out.println("id==="+id);
			New n = newService.selectById(id);
			String jsonpCallback=null;
			if(n==null) {
				jsonpCallback="jsonpCallback(\"{\\\"status\\\":\\\"no\\\"}\")";
			}
			else {
				jsonpCallback="jsonpCallback(\"{\\\"status\\\":\\\"ok\\\",\\\"entity\\\":{\\\"title\\\":\\\""+n.getTitle()+"\\\",\\\"content\\\":\\\""+htmlspecialchars(n.getContent())+"\\\"}}\")";
			}
			response.getWriter().print(jsonpCallback);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String htmlspecialchars(String str) {
		//System.out.println(str);
		if(!StringUtils.isEmpty(str)){
			str = str.replaceAll("\r\n", "");
			str = str.replaceAll("\\\"", "\\\\\\\'");
		}
		return str;
	}
	
	public static void main(String[] args) {
		String s="化工生产安全保障难已是行业共识越来越多的企业开始部署精准的<a href=\"http://www.hualingdw.com\">化工厂人员定位系统</a>来提高工厂安全管理水平从而达到工厂安全生产的目的\r\n";
		System.out.println(s.replaceAll("\\\"", "\\\\\\\'"));
	}
}
