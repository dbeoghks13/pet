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
	<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300&display=swap" rel="stylesheet">
	<style>
		#content_form {
			width: 800px; height:470px;
			margin: 30px auto 0px;
		}
		#content_form table tr { height: 10px;}
		.paging{width: 800px; height:360px;
				text-align: center;
				}
	</style>
	<style></style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			
		});
		
	</script>
</head>
<body>
	<c:if test="${insertResult ==1 }">
		<script>alert('강아지 등록 성공');</script>
	</c:if>
	<c:if test="${insertResult ==1 }">
		<script>alert('강아지 등록  실패');</script>
	</c:if>
	<c:if test="${not empty boaredResult }">
		<script>alert('${boaredResult}');</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<table>
		<tr>
		<c:if test="${totCnt==0 }">
			<tr><td colspan="6">등록된 강아지가  없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:set var="i" value="1"/>
			<c:forEach items="${dogList }" var="dog">
				<td>
					<img src="${conPath }/dogImg/${dog.dPhoto }" width="100"><br>
					${dog.dName } (${dog.dSize } - ${dog.dCategory })<br>
					${dog.dContent}
				</td>
				<c:if test="${i%3==0}">
					</tr><tr>
				</c:if>
				<c:set var="i" value="${i+1 }"/>	
			</c:forEach>
		</c:if>
		</tr>
	</table>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/main.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/main.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/main.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>