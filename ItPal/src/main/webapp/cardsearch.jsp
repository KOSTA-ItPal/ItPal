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

<script>
$(() => {
    // 페이지 로드 시 기본적으로 신용카드 탭을 보여줍니다
    showCardList("신용");

    // 신용카드 탭 클릭 시
    $("#sinCard").click(function() {
    	alert("신용");
    	$(".categoryBox").css("background-color", "white");
    	showCardList("신용");
    });

    // 체크카드 탭 클릭 시
    $("#checkCard").click(function() {
    	alert("체크");
    	$(".categoryBox").css("background-color", "white");
    	showCardList("체크");
    });
    
    
 	// AJAX로 카드 데이터를 가져와서 카드 리스트를 표시하는 함수
    function showCardList(cardType) {
        $.ajax({
            url: "cardSearch.do",  // 서버측 서블릿 URL
            type: "GET",
            data: {cardType: cardType},  // 카드 타입 전송 (신용카드: sin, 체크카드: check)
            dataType: "json",  // 서버에서 JSON 형식으로 데이터를 받음
            
            success: function(data) {
               let cardHtml = '';
                            
               let cards = data.cards;
               let details = data.splitDetails;
               
               console.log(data);
               
                // 카드 리스트 HTML 생성
                cards.forEach(card => {
                	
					var cardDetails = '';
                	var imgUrlHtml = '';
					
                	cardNo = card.cardNo;
                	console.log(card.imgUrl);
                	
                	//카드 디테일 반복...
                	details[cardNo].forEach(detail => {
                		cardDetails += `<div>` + detail + `</div>`;
                	});
                	//카드 디테일 반복 끝...
                    
                	cardHtml += `
               		<div class = "cards">
	                	<div class ="card">						
		                	<div class="card_img">
								<p class="img">
									<img src=`+ card.imgUrl +` width="auto" height="auto"/>
								</p>	
							</div>	
								
							<div class="cardInfo">
								<div class="cardName">
									<p class="name">`+ card.cardName + `</p>
									<p class="bankName">` + card.companyName +`</p>
									<div class="cardLink" data-url=` + card.cUrl + `>카드사 바로가기</div>
								</div>
								
								<!-- 혜택 정보 출력 -->
								<div class="cardBenefit">
									<div class="benefitDetail">`+
										cardDetails
									+`</div>
								</div>
								<div class="cardAnnual">
									<p>`+ card.annualFee +`</p>
								</div>							
							</div>
		 				</div>
	 				</div>
	 				`;
         
                });

                // 카드 리스트 HTML을 DOM에 삽입
                $("#cardList").html(cardHtml);

                // 탭 스타일 변경 (선택된 탭 강조)
                if (cardType === "신용") {
                    $("#sinCard").css("background-color", "#d8ffed");
                    $("#checkCard").css("background-color", "white");
                } else {
                    $("#checkCard").css("background-color", "#d8ffed");
                    $("#sinCard").css("background-color", "white");
                }
            },
            error: function(err) {
                console.log("Error fetching cards:", err);
            }
        });
    }
    
    // 동적으로 추가된 .cardLink에 클릭 이벤트를 적용하기 위해 이벤트 위임 사용
    $("#cardList").on("click", ".cardLink", function() {
    	var url = $(this).data('url');
    	window.location.href = url;
    });
    
 	// 카테고리 박스 클릭 이벤트 처리
    $(".categoryBox").click(function() {
    	// 모든 카테고리 박스의 배경색을 초기화 (원래 색상으로 되돌림)
        $(".categoryBox").css("background-color", "white");

        // 클릭된 카테고리 박스의 배경색 변경
        $(this).css("background-color", "#d8ffed");
    	
    	// 현재 선택된 탭 확인
        let cardType = $("#sinCard").css("background-color") === "rgb(216, 255, 237)" ? "신용" : "체크"; // lightblue일 경우
        
        var categoryName = $(this).find(".categoryInfo").text(); // 클릭한 카테고리의 이름을 가져옴
        alert("선택한 카테고리: " + categoryName); // 선택한 카테고리 이름 확인
        // AJAX 요청으로 카테고리 데이터 가져오기
        $.ajax({
            url: "cardSearch2.do",  // 서버측 서블릿 URL (카테고리 데이터 요청)
            type: "GET",
            data: {
            	cardType: cardType,
            	category: categoryName
           	},  // 카테고리 이름 전송
            dataType: "json",
            
            success: function(data) {
                let cardHtml = '';
                
                let cards = data.cards;
                let details = data.splitDetails;
                /* console.log(cards); */
                
                cards.forEach(card => {
                	var cardDetails = '';
                	
                	cardNo = card.cardNo;
                	
                	//카드 디테일 반복...
                	details[cardNo].forEach(detail => {
                		cardDetails += `<div>` + detail + `</div>`;
                	});
                	//카드 디테일 반복 끝...
                	
                	console.log(cardDetails);
                	
                	cardHtml += `
                		<div class = "cards">
	                	<div class ="card">						
							<div class="card_img">
								<p class="img">
									<img src=`+ card.imgUrl +` width="auto" height="auto"/>
								</p>	
							</div>
							
							<div class="cardInfo">
								<div class="cardName">
									<p class="name">`+ card.cardName + `</p>
									<p class="bankName">` + card.companyName +`</p>
									<div class="cardLink" data-url=` + card.cUrl + `>카드사 바로가기</div>
								</div>
								
								<!-- 혜택 정보 출력 -->
								<div class="cardBenefit">
									<div class="benefitDetail">`+
										cardDetails
									+`</div>
								</div>
								<div class="cardAnnual">
									<p>`+ card.annualFee +`</p>
								</div>							
							</div>
		 				</div>
	 				</div>
                    `;
                });
                $("#cardList").html(cardHtml);
            },
            error: function(err) {
                console.log("Error fetching category cards:", err);
            }
        }); //ajax 끝
    }); // 카테고리 박스 클릭 이벤트 처리 끝
 
});
</script>
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

			<<!-- 도현님 작성 -->
			<div id="cardSearchContainer">
				<div id="cardTypeChoice">
					<div class="cardTypeCheck" id="sinCard">신용카드</div>
					<div class="cardTypeCheck" id="checkCard">체크카드</div>
				</div>
				<div id="cardListContainer">
					<div id="categoryContainer">
						<div class="categoryBox">
							<img src="image/category/cafe.png" class="categoryIcon"/>
							<p class="categoryInfo">카페</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/mobile.png" class="categoryIcon"/>
							<p class="categoryInfo">모바일</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/transport.svg" class="categoryIcon"/>
							<p class="categoryInfo">교통</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/culture.png" class="categoryIcon"/>
							<p class="categoryInfo">문화</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/shopping.png" class="categoryIcon"/>
							<p class="categoryInfo">쇼핑</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/travel.svg" class="categoryIcon"/>
							<p class="categoryInfo">여행</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/digital-content.png" class="categoryIcon"/>
							<p class="categoryInfo">OTT</p>
						</div>
						<div class="categoryBox">
							<img src="image/category/cvs.png" class="categoryIcon"/>
							<p class="categoryInfo">편의점</p>
						</div>
					</div>
					<div id="cardList">
						
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>