package com.lec.pet.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.dao.PFileboardDao;
import com.lec.pet.dto.PFileboardDto;
public class BoardContentService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int fId = Integer.parseInt(request.getParameter("fId"));
		PFileboardDao boardDao = PFileboardDao.getInstance();
		PFileboardDto board = boardDao.contentView(fId);
		request.setAttribute("board", board);
	}

}
