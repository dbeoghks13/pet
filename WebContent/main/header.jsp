<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${conPath }/css/header.css" rel="stylesheet">
</head>
<body>
<header>
	<c:if test="${empty member and empty admin}"> <%-- 로그인 전 화면 --%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/joinView.do">회원가입</a></li>
				<li><a href="${conPath }/loginView.do">로그인</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			ℙ𝕖𝕥 𝔽𝕣𝕚𝕖𝕟𝕕
			<ul>
				<li><a href="${conPath }/main.do"></a></li>
			</ul>
		</div>
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/dogList.do?dsize=소형견">소형견 리스트</a></li>
				<li><a href="${conPath }/dogList.do?dsize=중형견">중형견 리스트</a></li>
				<li><a href="${conPath }/dogList.do?dsize=대형견">대형견 리스트</a></li>
				<li><a href="${conPath}/boardList.do">입양후기</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${not empty member and empty admin}"> <%-- 사용자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/logout.do">로그아웃</a></li>
				<li><a href="${conPath }/modifyView.do">정보수정</a></li>
				<li><a>${member.mName }님 &nbsp; ▶</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			ℙ𝕖𝕥 𝔽𝕣𝕚𝕖𝕟𝕕
		</div>
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/dogList.do?dsize=소형견">소형견 리스트</a></li>
				<li><a href="${conPath }/dogList.do?dsize=중형견">중형견 리스트</a></li>
				<li><a href="${conPath }/dogList.do?dsize=대형견">대형견 리스트</a></li>
				<li><a href="${conPath}/boardList.do">입양후기</a></li>
			</ul>
		</div>
	</c:if>
	<c:if test="${empty member and not empty admin}"> <%-- 관리자 모드 로그인 화면--%>
		<div class="gnb">
			<ul>
				<li><a href="${conPath }/dogList.do">애견목록</a></li>
				<li><a href="${conPath }/logout.do">관리자모드나가기</a></li>
				<li><a>${admin.aName }님 &nbsp; ▶</a></li>
			</ul>
		</div>
		<div class="logo" onclick="location.href='${conPath}/main.do'">
			ℙ𝕖𝕥 𝔽𝕣𝕚𝕖𝕟𝕕
		</div>
		<div class="lnb">
			<ul>
				<li><a href="${conPath }/DogInsertView.do?size=소형견">소형견 등록</a></li>
				<li><a href="${conPath }/DogInsertView.do?size=중형견">중형견 등록</a></li>
				<li><a href="${conPath }/DogInsertView.do?size=대형견">대형견 등록</a></li>
				<li><a href="${conPath}/boardList.do">입양후기게시판</a></li>
				<li><a href="${conPath}/allView.do">회원목록</a></li>
			</ul>
		</div>
	</c:if>
</header>
</body>
</html>
				
				