<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="cardsearch.css"><!-- CSS 파일 링크 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<title>잇플(ItPal):: 카드페이지</title>
<style>
@font-face {
	font-family: "LINESeedKR-Th";
	src:
		url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Th.woff2");
	font-weight: 700;
	font-style: normal;
}

@font-face {
	font-family: "LINESeedKR-Rg";
	src:
		url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Rg.woff2");
	font-weight: 700;
	font-style: normal;
}

@font-face {
	font-family: "LINESeedKR-Bd";
	src:
		url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2");
	font-weight: 700;
	font-style: normal;
}

* {
	font-family: "LINESeedKR-Bd";
}

.jumbotron {
	border: 1px solid black;
}

.container {
	padding: 0;
	border: 1px solid red;
}

#cardCategoryContainer {
	width: 100%;
	height: 500px;
	border: 1px solid black;
	display: flex;
	flex-direction: column;
	justify-content: space-evenly;
	align-items: center;
	background-color: lightgray;
}

#cardCategoryContainer>div {
	width: 90%;
	height: 35%;
	border: 1px solid black;
	background-color: white;
}

#cardRecommand {
	width: 100%;
	height: 200px;
	margin-top: 20px;
	background-color: rgb(163, 255, 214);
	border-radius: 10px;
	box-shadow: 0px 2px 60px 10px lightgray;
}

#cardSearchContainer {
	width: 100%;
	height: 500px;
	background-color: lightgray;
	margin-top: 200px;
}

#beforeLogin-BG {
	width: 100%;
	height: 100%;
	display: flex;
	flex-direction: row;
	justify-content: space-evenly;
	position: relative; /* 부모 요소의 위치 설정 */
	z-index: 1;
	box-sizing: border-box;
	border-radius: 10px;
	align-items: center; /* 수직 가운데 정렬 */
}

#beforeLogin-BG-cardImg {
	width: 110px;
    height: 110px;
    position: relative;
    
	display: flex;
	justify-content: center;
	align-items: center;
	
	background-color: #E9ECEF;
	border-radius: 100%;
}

#beforeLogin-BG-cardImg img {
    max-width: 100%;
    max-height: 100%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1;
}

#comment-beforeLogin, #comment-afterLogin {
	text-align: center;
	padding-top: 30px;
	padding-bottom: 30px;
	box-sizing: border-box;
	margin: 10px 0px;
}

#comment-beforeLogin #comment-lg, #comment-afterLogin #comment-lg {
	font-size: 30px;
}

#comment-beforeLogin #comment-md, #comment-afterLogin #comment-md {
	font-family: "LINESeedKR-Rg";
	font-size: 20px;
	color: gray;
}

#fakeBtn button, #recommentBtn button, #beforeLogin-FN-LoginBtn button {
	border: 1px solid lightgray;
	border-radius: 10px;
	background-color: #FCEAAB;
	padding: 10px 20px;
}

#beforeLogin-FN {
	display: flex;
	flex-direction: row;
	align-items: center;
	background-color: rgba(43, 43, 43, 0.726);
	position: absolute; /* 절대 위치 설정 */
	z-index: 2;
	top: 0; /* BG 요소의 가장 위에 위치하도록 설정 */
	left: 0; /* BG 요소의 왼쪽에 맞춤 */
	width: 100%; /* BG 요소의 너비에 맞춤 */
	height: 100%;
	box-sizing: border-box;
	justify-content: space-evenly;
	border-radius: 10px;
	box-shadow: 0px 2px 60px 10px lightgray;
}

#comment-beforeLogin2 {
	color: aliceblue;
	text-align: center;
}

#beforeLogin-FN-Icon img {
	width: 100px;
	height: 100px;
}

#afterLogin {
	width: 100%;
	height: 100%;
	box-sizing: border-box;
	background-color: rgba(163, 255, 214, 0.2);
	display: flex;
	flex-direction: row;
	border-radius: 10px;
	box-shadow: 0px 2px 60px 10px lightgray;
/* 	padding: 10px 150px; */
	justify-content: space-evenly;
	align-items: center;
}

#afterLogin-cardImg {
	width: 110px;
    height: 110px;
    position: relative;
	display: flex;
	justify-content: center;
	align-items: center;
	background-color: #E9ECEF;
	border-radius: 100%;
}


#afterLogin-cardImg img {
    max-width: 100%;
    max-height: 100%;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    z-index: 1;
}
</style>
</head>
<body>
	<jsp:include page="header.html" />
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<!-- 승현이 작성 -->
			<div id="cardCategoryContainer">
				<div id="cardNew"></div>
				<div id="cardHOT"></div>
			</div>
			<div id="cardRecommand">
				<!-- id가 null일 때 -->
				<c:if test="${user==null}">
					<div id="beforeLogin-BG">
						<div id="beforeLogin-BG-cardImg">
							<img
								src="https://d1c5n4ri2guedi.cloudfront.net/card/2422/card_img/27141/2422card.png">
						</div>
						<div id="comment-beforeLogin">
							<div id="comment-lg">나에게 찰떡궁합 카드는?</div>
							<div id="comment-md">나의 소비패턴에 가장 적합한 카드를 추천해드려요</div>
							<div id="fakeBtn">
								<button>나에게 딱맞는 카드 보러가기</button>
							</div>
						</div>
						<div id="beforeLogin-FN">
							<div id="beforeLogin-FN-Icon">
								<img src="img/characters/Security.png">
							</div>
							<div id="comment-beforeLogin2">
								<h3>로그인 후 이용 가능합니다</h3>
								<div id="beforeLogin-FN-LoginBtn">
									<button id="cardLoginBtn" onclick="location.href='login.jsp'">로그인 하러가기</button>
								</div>
							</div>
						</div>
					</div>
				</c:if>
				<!-- id가 null일 때 -->
				<c:if test="${user!=null}">
					<div id="afterLogin">
						<div id="afterLogin-cardImg">
							<img
								src="https://d1c5n4ri2guedi.cloudfront.net/card/2422/card_img/27141/2422card.png">
						</div>
						<div id="comment-afterLogin">
							<!-- @@에는 vo.name 을 넣어야 함 -->
							<div id="comment-lg">${user.userName}님에게 찰떡궁합인 카드는?</div>
							<div id="comment-md">소비패턴을 분석해 가장 적합한 카드를 추천해드려요</div>
							<div id="recommentBtn">
								<form action="cardRecommend.do">
									<button id="cardRecommendBtn"
										onclick="location.href='cardrecommend.jsp'">나에게 딱맞는 카드 보러가기</button>
								</form>
							</div>
						</div>
					</div>
				</c:if>
			</div>

			<!-- 도현 작성 -->
			<div id="cardSearchContainer"></div>

		</div>
	</div>
</body>
</html>