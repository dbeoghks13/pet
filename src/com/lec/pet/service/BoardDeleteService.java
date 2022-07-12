package com.lec.pet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.dao.PFileboardDao;
import com.lec.pet.service.Service;

public class BoardDeleteService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		PFileboardDao boardDao = PFileboardDao.getInstance();
		int result = boardDao.deleteBoard(fId);
		if(result == PFileboardDao.SUCCESS) {
			request.setAttribute("boaredResult", "글삭제 성공");
		}else {
			request.setAttribute("boaredResult", "글삭제 실패");
		}
	}
}
