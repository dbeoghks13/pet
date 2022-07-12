package com.lec.pet.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.dao.PFileboardDao;
import com.lec.pet.dto.PFileboardDto;
import com.lec.pet.service.Service;

public class BoardReplyViewService implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		PFileboardDao boardDao = PFileboardDao.getInstance();
		PFileboardDto originBoard = boardDao.modifyView_replyView(fId);
		request.setAttribute("originBoard", originBoard);
	}

}