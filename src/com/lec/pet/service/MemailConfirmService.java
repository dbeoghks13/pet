package com.lec.pet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.dao.PMemberDao;

public class MemailConfirmService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mEmail = request.getParameter("mEmail");
		PMemberDao mDao = PMemberDao.getInstance();
		// email 중복체크
		int result = mDao.emailConfirm(mEmail);
		if(result == PMemberDao.NONEXISTENT) {
			request.setAttribute("emailConfirmResult", "사용 가능한 메일입니다");
		}else {
			request.setAttribute("emailConfirmResult", "<b>중복된 이메일 입니다</b>");
		}
	}
}	
	


