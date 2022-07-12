package com.lec.pet.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.dao.PMemberDao;
public class MidConfirmService implements Service {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String mId = request.getParameter("mId");
		PMemberDao mDao = PMemberDao.getInstance();
		// ID 중복체크
		int result = mDao.mIdConfirm(mId);
		if(result == PMemberDao.NONEXISTENT) {
			request.setAttribute("idConfirmResult", "사용 가능한 ID");
		}else {
			request.setAttribute("idConfirmResult", "<b>중복된 ID</b>");
		}
	}
	
}

