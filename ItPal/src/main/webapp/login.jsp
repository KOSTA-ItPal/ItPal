<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="login.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.html"></jsp:include>
<div id="one">더 좋은 서비스를 제공받기 위해 로그인하세요.</div>
<div id="two">
	<div id="two-1">잇플에 로그인하세요</div>
	<div id="two-2">
	<form action="login.do" method="post" id="frm">
		<input type="text" name="id" id="id" placeholder="아이디를 입력하세요." required/>
		<input type="password" name="pwd" id="pwd" placeholder="비밀번호를 입력하세요." required/>
		
		<input type="submit" id="login" value="로그인"></input>
	</form>
	</div>
	<div id="three">
		<p id="three-2"><a href="#">암호를 잊어버리셨나요?</a></p>
		<p id="three-1">아직 잇플 회원이 아니신가요? <a href="register.jsp">회원가입</a></p>
	</div>
</div>
</body>
</html>