<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/style.css" rel="stylesheet">
	<style></style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<style>
		#content_form {
			width: 800px; height:400px;
			margin: 100px auto 0px;
		}
		#content_form {
			border:1px solid black;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<table>
				 <caption>${board.fId }글 상세보기</caption>
				 <tr><td>작성자</td>
				 		 <td>${board.mName}(${board.mId})님</td>
				 </tr>
				 <tr><td>제목</td>
				 		 <td>${board.fTitle }</td>
				 </tr>
				 <tr><td>본문</td>
				 		 <td><pre>${board.fContent}</pre></td>
				 </tr>
				 <tr><th>첨부파일</th>
						 <td>
						 	<c:if test="${not empty board.fFileName }">
						 		<a href="${conPath }/fileboardUp/${board.fFileName}" target="_blank">${board.fFileName}</a>
						 	</c:if>
						 	<c:if test="${empty board.fFileName }">
						 		첨부파일없음
						 	</c:if>
						</td>
				 </tr>
				 <tr><td colspan="2">
				 			<c:if test="${member.mId eq board.mId }">
				 				<button onclick="location='${conPath}/boardModifyView.do?fId=${board.fId }&pageNum=${param.pageNum }'">수정</button>
				 			</c:if>
				 			<c:if test="${member.mId eq board.mId or not empty admin}">
				 				<button onclick="location='${conPath}/boardDelete.do?fId=${board.fId }&pageNum=${param.pageNum }'">삭제</button>
				 			</c:if>
				 			<c:if test="${not empty member }">
				 				<button onclick="location='${conPath}/boardReplyView.do?fId=${board.fId }&pageNum=${param.pageNum }'">답변</button>
				 			</c:if>
				 			<input type="button" value="목록" class="btn"
				 	onclick="location='${conPath}/boardList.do?pageNum=${param.pageNum }'">			 
		</table>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>