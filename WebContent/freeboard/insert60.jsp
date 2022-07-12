<%@page import="com.lec.pet.dao.PFileboardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
<%PFileboardDao dao = PFileboardDao.getInstance();
	for(int i=0 ; i<60 ; i++){
		String mId;
		String fFilename = "noImg.png";
		if(i%2==0){
			mId = "aaa";
			
		}else{
			mId="bbb";
		}
		String fTitle = "글제목 " +i;
	 	String fContent = "글본문";
	 	String fip = "129.16.126."+i;
	 	dao.writeBoard(mId, fTitle, fContent, fFilename, fip);
	}
	response.sendRedirect("../boardList.do");
%>
</body>
</html>