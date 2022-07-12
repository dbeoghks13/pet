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
<link href="${conPath }/css/join.css" rel="stylesheet">
<style>
	#content_form {
			width: 800px; height:480px; 
			margin: 20px auto 0px;
	}
	#idConfirmResult, #pwChkResult, #emailConfirmResult{text-align: left; padding-left: 30px;}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function(){
		$('input[name="mId"]').keyup(function(){
			var mId = $('input[name="mId"]').val();
			$.ajax({
				url : '${conPath}/idConfirm.do',
				type : 'get',
				dataType : 'html',
				data : "mId="+mId,
				success : function(data){
					$('#idConfirmResult').html(data);
				}
			});//ajax
		});// mId keyup 이벤트
		$('input[name="mPw"], input[name="mPwChk"]').keyup(function(){
			var mPw = $('input[name="mPw"]').val();
			var mPwChk = $('input[name="mPwChk"]').val();
			if(mPw == mPwChk){
				$('#pwChkResult').text('비밀번호 일치');
			}else{
				$('#pwChkResult').html('<b>비밀번호 불일치</b>');
			}
		}); // pw check
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
			var idConfirmResult = $('#idConfirmResult').text().trim();
			var pwChkResult     = $('#pwChkResult').text().trim();
			var emailConfirmResult = $('#emailConfirmResult').text().trim();
			if(idConfirmResult!='사용 가능한 ID'){
				alert('사용가능한 ID로 가입하세요');
				$('input[name="mId"]').focus();
				return false;
			}else if(pwChkResult !='비밀번호 일치'){
				alert('비밀번호를 확인하세요');
				$('input[name="mPw"]').focus();
				return false;
			}else if(emailConfirmResult!='' && emailConfirmResult != '사용 가능한 메일입니다'){
				alert('이메일을 확인하세요');
				$('input[name="mEmail"]').focus();
				return false;
			}
		});
	});//mIdConfirm의 click이벤트
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
	<form action="${conPath }/join.do" method="post" enctype="multipart/form-data">
		<table>
			<tr><th>아이디</th>
					<td>
						<input type="text" name="mId" required="required">
						<div id="idConfirmResult"> &nbsp; </div>
					</td>
			</tr>
			<tr><th>비밀번호</th>
					<td><input type="password" name="mPw" required="required"></td>
			</tr>
			<tr><th>비밀번호확인</th>
					<td>
						<input type="password" name="mPwChk" required="required">
						<div id="pwChkResult"> &nbsp; </div>
					</td>
			</tr>
			<tr><th>이름</th>
					<td><input type="text" name="mName" required="required"></td>
			</tr>
			<tr><th>메일</th><td><input type="email" name="mEmail"><div id="emailConfirmResult"> &nbsp; </div></td></tr>
			<tr><th>사진</th><td><input type="file" name="mPhoto"></td></tr>
			<tr><th>생년월일</th><td><input type="text" name="mBirth" id="mBirth"></td></tr>
			<tr><th>주소</th><td><input type="text" name="mAddress"></td></tr>
			<tr><td colspan="2">
						
			<div class = "find">
						<input type="submit" value="회원가입" class="btn">
						<input type="button" value="로그인" class="btn"
								onclick="location.href='${conPath}/loginView.do'">
			</div>					
		</table>
	</form>
	</div>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>