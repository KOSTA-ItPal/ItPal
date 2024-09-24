<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- JSTL 함수 라이브러리 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
	</div>
</div>
</body>
</html>









