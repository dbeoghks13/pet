package com.lec.pet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.dao.PFileboardDao;
import com.lec.pet.dto.PFileboardDto;

public class BoardModifyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		PFileboardDao dao = PFileboardDao.getInstance();
		PFileboardDto board = dao.modifyView_replyView(fId);
		request.setAttribute("board", board);
	}

}