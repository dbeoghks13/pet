package com.lec.pet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lec.pet.dao.PMemberDao;
import com.lec.pet.dto.PMemberDto;

public class MLoginService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		PMemberDao mDao = PMemberDao.getInstance();
		int result = mDao.loginCheck(mId, mPw);
		if(result==PMemberDao.LOGIN_SUCCESS) { // 로그인 성공
			HttpSession session = request.getSession();
			PMemberDto member = mDao.getMember(mId);
			session.setAttribute("member", member);
		}else { // 로그인 실패
			request.setAttribute("loginErrorMsg", "아이디와 비번을 확인하세요");
		}
	}

}