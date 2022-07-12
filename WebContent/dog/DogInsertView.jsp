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
	<c:if test="${empty member and empty admin}">
		<script>
			alert('로그인 후 애견을 등록하세요');
			location.href = "${conPath}/main.do";
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
  	  <form action="${conPath }/DogInsert.do" method="post" enctype="multipart/form-data">
  	    <table>
  		  <caption>강아지 등록</caption>
  		    <tr>
  		    	<th>강아지이름</th>
  		    	<td><input type="text" name="dName"></td>
  		    </tr>
  		    <tr>
  		    	<th>강아지크기</th>
  		    	<td><input type="text" name="dSize" value=${param.size }></td>
  		    </tr>
  		    <tr>
  		    	<th>견종</th>
  		    	<td><input type="text" name="dCategory"></td>
  		    </tr>
  		    <tr>
  		    	<th>강아지설명</th>
  		    	<td><textarea rows="5" cols="20" name="dContent"></textarea></td>
  		    </tr>
  		    <tr>
  		    	<th>강아지사진</th>
  		    	<td><input type="file" name="dPhoto"></td>
  		    </tr>
  		    <tr>
  		    	<td colspan="2"><input type="submit" value="${param.size }등록"></td>
  		    </tr>
		</table>
  		</form>
  	</div>
  	<jsp:include page="../main/footer.jsp"/>
</body>
</html>
  			


