<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				margin: 50px auto 50px;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('input[name="mEmail"]').keyup(function(){
				var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
				var mEmail = $('input[name="mEmail"]').val();
				if(patternMail.test(mEmail)){
					$.ajax({
						url : '${conPath}/emailConfirm.do',
						type : 'get',
						dataType : 'html',
						data : "mEmail="+mEmail,
						success : function(data){
							$('#emailConfirmResult').html(data);
						}
					});//ajax
				}else if(!mEmail){
					$('#emailConfirmResult').html(' &nbsp; ');
				}else{
					$('#emailConfirmResult').html('메일 형식을 지켜주세요');
				}//if
			});// mEmail keyup 이벤트
			$('form').submit(function(){
				var emailConfirmResult = $('#emailConfirmResult').text().trim();
				if(emailConfirmResult!='' && emailConfirmResult != '사용 가능한 메일입니다'){
					alert('중복된 이메일은 수정불가합니다');
					$('input[name="mEmail"]').focus();
					return false;
				}
			});
		});
	</script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#mBirth" ).datepicker({
    	dateFormat : 'yy-mm-dd',
    	changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
    	monthNamesShort : ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
    	showMonthAfterYear : true,
    	yearSuffix : '년', // "2020년 3월"
    	showOtherMonths : true,
    	dayNamesMin:['일','월','화','수','목','금','토'],
			changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
			minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
			maxDate : 'y', // 현재 날짜이전까지만 표시
			yearRange : 'c-100:c+100', // 년도 선택 셀렉트 
    });
  } );
  </script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
	<form action="${conPath }/modify.do" method="post"
			enctype="multipart/form-data">
		<input type="hidden" name="dbmPhoto" value="${member.mPhoto }">
		<table>
			<tr><th>아이디</th>
					<td><input type="text" name="mId" value="${member.mId }"
									readonly="readonly">
					</td>
					<td rowspan="4">
						<img src="${conPath }/memberPhotoUp/${member.mPhoto}" width="150">
					</td>
			</tr>
			<tr><th>비밀번호</th>
					<td><input type="password" name="mPw" value="${member.mPw }"
									required="required">
					</td>
			</tr>
			<tr><th>이름</th>
					<td><input type="text" name="mName" value="${member.mName }"
									required="required">
					</td>
			</tr>
			<tr><th>메일</th><td><input type="email" name="mEmail" value="${member.mEmail }"><div id="emailConfirmResult"> &nbsp; </div></td></tr>
			<tr><th>사진</th>
					<td colspan="2"><input type="file" name="mPhoto"></td>
			</tr>
			<tr><th>생년월일</th>
					<td colspan="2">
						<input type="text" name="mBirth" id="mBirth" value="${member.mBirth }" id="birth">
					</td>
			</tr>
			<tr><th>주소</th>
					<td colspan="2">
						<input type="text" name="mAddress" value="${member.mAddress }">
					</td>
			</tr>
			<tr><td colspan="3">
						<input type="submit" value="정보수정" class="btn">
						<input type="reset" value="초기화" class="btn">
						<input type="button" value="이전" onclick="history.go(-1)" class="btn">
						<input type="button" value="회원탈퇴" 
								onclick="location.href='${conPath}/withdrawal.do'" class="btn">	
					</td>
			</tr>
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>
</head>
<body>
  
</body>
</html>