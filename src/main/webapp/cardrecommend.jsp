<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<<<<<<< HEAD:src/main/webapp/cardrecommend.jsp
=======

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- JSTL 함수 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

>>>>>>> dev:ItPal/src/main/webapp/cardrecommend.jsp
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<<<<<<< HEAD:src/main/webapp/cardrecommend.jsp
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
<style>
	.jumbotron {
		border: 1px solid black;	
	}
	
	.container {
		padding: 0;
		border: 1px solid red;
	}
	
	#ahffkdy {
		width: 100%;
		height: 100px;
		border: 1px solid blue;
		margin-bottom: 100px;
	}
	
	#ahffkdy>p {
		margin: auto;
		align-item: center;
	}
	
	#card-21 {
		width: 100%;
		height: 650px;
		background-color: lightgray;
		margin-bottom: 250px;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	#card-21 div {
		width: 100%;
		height: 30%;
		border: 1px solid red;
	}
	
	#card-22 {
		width: 100%;
		height: 650px;
		background-color: lightgray;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
	}
	
	#card-22 div {
		width: 100%;
		height: 30%;
		border: 1px solid blue;
	}
	
	
</style>
</head>
<body>
<div class="jumbotron jumbotron-fluid"></div>
<div class="container">
	<div id="ahffkdy">OO님에게 추천해드리는 카드들입니다.</div>
	<div id="card-21">
		<div id="recom1"></div>
		<div id="recom2"></div>
		<div id="recom3"></div>
	</div>
	<div id="card-22">
		<div id="recom1"></div>
		<div id="recom2"></div>
		<div id="recom3"></div>
=======
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="cardRecommend.css">
</head>

<body>
<jsp:include page="header.html"></jsp:include>
<div class="jumbotron jumbotron-fluid">
	<div class="container">
		<div id="mainInfo">
		    <span style="color:#1C1678; font-size:50px;">${user.userName}</span> 님의 소비 내역을 분석해 추천드리는 카드입니다
		</div>
		
		<c:set var="totalCardsIdx" value="0"/>	
		<div class="recommend">
			<c:forEach var="cardList" items="${cardListByCategoryMap}" varStatus="catStatus">
				<div class = "cards">	
					<div class="categoryInfo">
						<div> <span style="color:#8576FF; font-size:35px;"> ${cardList.key}</span> 할인 BEST ${fn:length(cardList.value)}</div>
					</div>
		
					<c:forEach items="${cardList.value}" var="card" varStatus = "cardStatus">
		 				<div class ="card">
							<img class="rankImg" src=img/rank/${cardStatus.count}.png>
							
							<div class="card_img">
								<p class ="img">
									<img data-v src= "${card.imgUrl}" width="auto" height="auto"/>
								</p>
							</div>
							
							
							<div class="cardInfo">
								<div class="cardName">
									<p class="name">${card.cardName} </p>
									<p class="bankName">${card.companyName}</p>
									<a class ="cardLink" href =${card.cUrl}>카드사 바로가기</a>
								</div>
								
									
								<!-- 혜택 정보 출력 -->
								<c:forEach items="${cardDetails.get(totalCardsIdx)}" var="details" varStatus="detailStatus">
									<div class="cardBenefit">
										<div class="benefitDetail">
											${details}
										</div>
									</div>
								</c:forEach>
								<div class="cardAnnual">
									${card.annualFee}
								</div>							
							</div>
		 				</div>
		 				<c:set var="totalCardsIdx" value="${totalCardsIdx + 1}" />
					</c:forEach>
				</div>
			</c:forEach>
		</div>
>>>>>>> dev:ItPal/src/main/webapp/cardrecommend.jsp
	</div>
</div>
</body>
</html>