package com.personPositionBg.service;

import javax.servlet.http.*;

public interface UtilService {
	
	public void getKaptchaImageByUser(HttpSession session, String identity, HttpServletResponse response);
	
}
