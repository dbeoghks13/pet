package com.lec.pet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lec.pet.service.ALoginService;
import com.lec.pet.service.BoardContentService;
import com.lec.pet.service.BoardDeleteService;
import com.lec.pet.service.BoardListService;
import com.lec.pet.service.BoardModifyService;
import com.lec.pet.service.BoardModifyViewService;
import com.lec.pet.service.BoardReplyService;
import com.lec.pet.service.BoardReplyViewService;
import com.lec.pet.service.BoardWriteService;
import com.lec.pet.service.DogInsertService;
import com.lec.pet.service.DogListService;
import com.lec.pet.service.DogSizeListService;
import com.lec.pet.service.MAllViewService;
import com.lec.pet.service.MJoinService;
import com.lec.pet.service.MLoginService;
import com.lec.pet.service.MLogoutService;
import com.lec.pet.service.MModifyService;
import com.lec.pet.service.MWithdrawalService;
import com.lec.pet.service.MemailConfirmService;
import com.lec.pet.service.MidConfirmService;
import com.lec.pet.service.Service;



@WebServlet("*.do")
public class PController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String com 	   = uri.substring(conPath.length());
		String viewPage = null;
		Service service = null;
		if(com.equals("/main.do")) {
			service = new DogListService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
			
		}else if(com.equals("/joinView.do")) {
			viewPage = "member/join.jsp";
		}else if(com.equals("/idConfirm.do")) {
			service = new MidConfirmService();
			service.execute(request, response);
			viewPage = "member/idConfirm.jsp";
		}else if(com.equals("/emailConfirm.do")) {
			service = new MemailConfirmService();
			service.execute(request, response);
			viewPage = "member/emailConfirm.jsp";
		}else if(com.equals("/join.do")) { // 회원가입 DB 처리
			service = new MJoinService(); // execute메소드 : mId중복체크 후 회원가입
			service.execute(request, response);
			viewPage = "loginView.do";
		}else if(com.equals("/loginView.do")) { // 로그인 화면
			viewPage = "member/login.jsp";
		}else if(com.equals("/login.do")) { // 로그인 DB 및 세션 처리
			service = new MLoginService(); 
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/logout.do")) {//로그아웃 - 세션 날리기
			service = new MLogoutService();
			service.execute(request, response);
			viewPage = "main.do";
		}else if(com.equals("/modifyView.do")) { // 정보 수정 화면
			viewPage = "member/modify.jsp";
		}else if(com.equals("/modify.do")) { // DB에 정보 수정
			service = new MModifyService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		}else if(com.equals("/allView.do")) { // 회원목록가져오기
			service = new MAllViewService();
			service.execute(request, response);
			viewPage = "member/mAllView.jsp";
		}else if(com.equals("/withdrawal.do")) { // 회원탈퇴
			service = new MWithdrawalService();
			service.execute(request, response);
			viewPage = "main/main.jsp";
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * Admin 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		}else if(com.equals("/adminLoginView.do")) {
			viewPage = "admin/AdminLogin.jsp";
		}else if(com.equals("/adminLogin.do")) {
			service = new ALoginService();
			service.execute(request, response);
			viewPage = "allView.do";
		/* * * * * * * * * * *  * * * * * * * * * * * *
		 * * * * * * * * * fileBoard 관련 요청  * * * * * * *
		* * * * * * * * * * *  * * * * * * * * * * * * */
		}else if(com.equals("/boardList.do")) {
			service = new BoardListService();
			service.execute(request, response);
			viewPage = "freeboard/boardList.jsp";
		}else if(com.equals("/boardWriteView.do")) {
			viewPage = "freeboard/boardWrite.jsp";
		}else if(com.equals("/boardWrite.do")) {
			service = new BoardWriteService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(com.equals("/boardContent.do")) {
			service = new BoardContentService();
			service.execute(request, response);
			viewPage = "freeboard/boardContent.jsp";
		}else if(com.equals("/boardModifyView.do")) {
			service = new BoardModifyViewService();
			service.execute(request, response);
			viewPage = "freeboard/boardModify.jsp";
		}else if(com.equals("/boradModify.do")) {
			service = new BoardModifyService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(com.equals("/boardDelete.do")) {
			service = new BoardDeleteService();
			service.execute(request, response);
			viewPage = "boardList.do";
		}else if(com.equals("/boardReplyView.do")) {
			service = new BoardReplyViewService();
			service.execute(request, response);
			viewPage = "freeboard/boardReply.jsp";
		}else if(com.equals("/boardReply.do")) { 
			service = new BoardReplyService();
			service.execute(request, response); 
			viewPage = "boardList.do"; 
			
			
			// Dog 메뉴
			
			
		}else if(com.equals("/DogInsertView.do")) {
			viewPage = "dog/DogInsertView.jsp";
		}else if(com.equals("/DogInsert.do")) {
			service = new DogInsertService();
			service.execute(request, response);
			viewPage = "/dogList.do";
		}else if(com.equals("/dogList.do")) {
			service = new DogSizeListService();
			service.execute(request, response);
			viewPage = "dog/doglist.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}
}
       
    

	
