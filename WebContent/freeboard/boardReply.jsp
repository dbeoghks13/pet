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
  <link href="${conPath}/css/style.css" rel="stylesheet">
	<style>
		#content_form {
			width: 800px; height:400px;
			margin: 100px auto 0px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<form action="${conPath }/boardReply.do" method="post" enctype="multipart/form-data">
			<!-- reply.do시 필요한 원글 정보 : bGroup, bStep, bIndent
			                              지금 입력할 답변글 : bName, bTitle, bContent
			                       pageNum -->
			<input type="hidden" name="fGroup" value="${originBoard.fGroup }">
			<input type="hidden" name="fStep" value="${originBoard.fStep }">
			<input type="hidden" name="fIndent" value="${originBoard.fIndent }">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption>${originBoard.fId }번글의 답변쓰기 폼</caption>
				<tr><td>작성자</td><td>${member.mName }(${member.mId })</td></tr>
				<tr><td>제목</td><td><input type="text" name="fTitle"
								required="required" size="30"
								value="[답]${originBoard.fTitle }"></td></tr>
				<tr><td>본문</td><td><textarea name="fContent" rows="3" cols="3"></textarea></td></tr>
				<tr><td>첨부파일</td><td><input type="file" name="fFilName"></td></tr>
				<tr><td colspan="2">
							<input type="submit" value="답변쓰기" class="btn">
							<input type="reset" value="취소" class="btn">
							<input type="button" value="목록" class="btn"
								onclick="location.href='${conPath}/boardList.do'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>