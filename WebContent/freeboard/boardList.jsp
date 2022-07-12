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
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var fId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				//alert(fId);
				if(!isNaN(fId)){
					location.href = '${conPath}/boardContent.do?fId='+fId+'&pageNum=${pageNum}';
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${not empty boaredResult }">
		<script>alert('${boaredResult}');</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<table>
		<tr><td>
					<c:if test="${not empty member }"><a href="${conPath }/boardWriteView.do">글쓰기</a></c:if>
					<c:if test="${empty member }"><a href="${conPath }/loginView.do">글쓰기는 사용자 로그인 이후에만 가능합니다</a></c:if>
		</td></tr>
	</table>
	<br>
	<table>
		<tr><th>글번호</th><th>작성자</th><th>글제목</th><th>조회수</th>
				<th>날짜</th><th>ip</th></tr>
		<c:if test="${totCnt==0 }">
			<tr><td colspan="6">등록된 글이 없습니다</td></tr>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<c:forEach items="${boardList }" var="board">
				<tr><td>${board.fId }</td>
						<td>${board.mName }</td>
						<td class="left">
							<c:forEach var="i" begin="1" end="${board.fIndent }">
								<c:if test="${i==board.fIndent }">└─</c:if>
								<c:if test="${i!=board.fIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach>
							${board.fTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
							<c:if test="${not empty board.fFileName }">
								<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
							</c:if>
						</td>
						<td>${board.fHit }</td>
						<td><fmt:formatDate value="${board.fRdate }" type="date" dateStyle="short"/></td>
						<td>${board.fIp }</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/boardList.do?pageNum=${startPage-1}"> 이전 </a> ]
		</c:if>
		<c:forEach var="i" begin="${startPage }" end="${endPage }">
			<c:if test="${i == pageNum }">
				<b> [ ${i } ] </b>
			</c:if>
			<c:if test="${i != pageNum }">
				[ <a href="${conPath }/boardList.do?pageNum=${i}"> ${i } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/boardList.do?pageNum=${endPage+1}">다음 </a> ]
		</c:if>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>