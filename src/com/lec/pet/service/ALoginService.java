package com.lec.pet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.pet.dao.PAdminDao;
import com.lec.pet.dto.PAdminDto;

public class ALoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String aId = request.getParameter("aId");
		String aPw = request.getParameter("aPw");
		PAdminDao aDao = PAdminDao.getInstance();
		int result = aDao.loginCheck(aId, aPw);
		if(result == PAdminDao.LOGIN_SUCCESS) {
			HttpSession httpSession = request.getSession();
			httpSession.setAttribute("aId", aId);
			PAdminDto admin = aDao.getPAdmin(aId);
			httpSession.setAttribute("admin", admin);
			request.setAttribute("adminLoginResult", "관리자계정으로 들어오셨습니다");
		}else {
			request.setAttribute("adminLoginResult", "관리자계정 로그인이 실패되었습니다");
			request.setAttribute("adminLoginError", "error");
		}
	}

}
