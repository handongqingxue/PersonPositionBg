package com.personPositionBg.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.personPositionBg.service.*;

@Controller
@RequestMapping(BackgroundController.MODULE_NAME)
public class BackgroundController {

	@Autowired
	private UtilService utilService;
	public static final String MODULE_NAME="/background";
	
	/**
	 * 跳转至登录页面
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return MODULE_NAME+"/login";
	}

	/**
	 * 为登录页面获取验证码
	 * @param session
	 * @param identity
	 * @param response
	 */
	@RequestMapping(value="/login/captcha")
	public void getKaptchaImageByMerchant(HttpSession session, String identity, HttpServletResponse response) {
		utilService.getKaptchaImageByUser(session, identity, response);
	}
}
