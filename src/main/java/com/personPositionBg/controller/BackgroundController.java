package com.personPositionBg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personPositionBg.entity.User;
import com.personPositionBg.service.*;
import com.personPositionBg.util.JsonUtil;
import com.personPositionBg.util.PlanResult;

@Controller
@RequestMapping(BackgroundController.MODULE_NAME)
public class BackgroundController {

	@Autowired
	private UtilService utilService;
	public static final String MODULE_NAME="/background";
	
	/**
	 * ��ת������ҳ��
	 * @return
	 */
	@RequestMapping(value="/test")
	public String test() {
		return MODULE_NAME+"/test";
	}
	
	/**
	 * ��ת����¼ҳ��
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login() {
		return MODULE_NAME+"/login";
	}

	/**
	 * Ϊ��¼ҳ���ȡ��֤��
	 * @param session
	 * @param identity
	 * @param response
	 */
	@RequestMapping(value="/login/captcha")
	public void getKaptchaImageByMerchant(HttpSession session, String identity, HttpServletResponse response) {
		utilService.getKaptchaImageByUser(session, identity, response);
	}

	/**
	 * ��̨�û���¼
	 * @param userName
	 * @param password
	 * @param rememberMe
	 * @param loginVCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST,produces="plain/text; charset=UTF-8")
	@ResponseBody
	public String login(String userName,String password,
			String rememberMe,String loginVCode,HttpServletRequest request) {
		System.out.println("===��¼�ӿ�===");
		//����ֵ��json
		PlanResult plan=new PlanResult();
		HttpSession session=request.getSession();
		String verifyCode = (String) session.getAttribute("verifyCode");
		System.out.println("verifyCode==="+verifyCode);
		System.out.println("loginVCode==="+loginVCode);
		if(verifyCode.equals(loginVCode)) {
			//TODO���⸽����ӵ�¼������Ϣ���裬���û����˺��Լ����봢�浽shiro��ܵĹ������õ��з��������ѯ
			try {
				System.out.println("��֤��һ��");
				UsernamePasswordToken token = new UsernamePasswordToken(userName,password);  
				Subject currentUser = SecurityUtils.getSubject();  
				if (!currentUser.isAuthenticated()){
					//ʹ��shiro����֤  
					token.setRememberMe(true);  
					currentUser.login(token);//��֤��ɫ��Ȩ��  
				}
			}catch (Exception e) {
				e.printStackTrace();
				plan.setStatus(1);
				plan.setMsg("��½ʧ��");
				return JsonUtil.getJsonFromObject(plan);
			}
			User user=(User)SecurityUtils.getSubject().getPrincipal();
			session.setAttribute("user", user);
			
			plan.setStatus(0);
			plan.setMsg("��֤ͨ��");
			plan.setUrl("/background/webContent/new/list");
			return JsonUtil.getJsonFromObject(plan);
		}
		plan.setStatus(1);
		plan.setMsg("��֤�����");
		return JsonUtil.getJsonFromObject(plan);
	}

	@RequestMapping(value="/exit")
	public String exit(HttpSession session) {
		System.out.println("�˳��ӿ�");
		 Subject currentUser = SecurityUtils.getSubject();
	       currentUser.logout();    
		return "/background/login";
	}
}
