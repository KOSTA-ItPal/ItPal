<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- icon-cdn-->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@24,400,0,0" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!--slick -->
<!-- Slick Slider CSS -->
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css" />
<link rel="stylesheet" type="text/css"
	href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick-theme.css" />

<!-- Slick Slider JS -->
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$('#cardNew').slick({
			slidesToShow : 4,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
			arrows : true,
			prevArrow : $('.slick-prev-new'),
			nextArrow : $('.slick-next-new'),
		});
		$('#cardHOT').slick({
			slidesToShow : 4,
			slidesToScroll : 1,
			autoplay : true,
			autoplaySpeed : 2000,
			arrows : true,
			prevArrow : $('.slick-prev-hot'),
			nextArrow : $('.slick-next-hot'),
		});
	});
</script>
<style>
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
	text-align: left;
	background-color: lightgray;
}

#cardCategoryContainer>div {
	width: 90%;
	height: 35%;
	
}

#cardNew, #cardHOT {
	background-color: white; /* 배경색 흰색 */
	padding: 10px;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1); /* 그림자 추가 */ img {
	object-fit : center;
	box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.2);
	width: 40px;
	height: 120px;
	margin: 20px 100px;
}


#cardHOT>button {
	display: none;
}

}
.slick-arrow {
	width: 50px; /* 버튼 크기 확대 */
	height: 30px; /* 버튼 크기 확대 */;
	display: flex;
	align-items: center;
	justify-content: center;
	color: #ccc;
}

.slick-prev-new {
	position: absolute;
	left: 21%;
	top: 34%; /* 위치 조정 */
}

.slick-next-new {
	position: absolute;
	right: 20.5%;
	top: 34%; /* 위치 조정 */
}

.slick-prev-hot {
	position: absolute;
	left: 21%;
	top: 62%; /* 위치 조정 */
}

.slick-next-hot {
	position: absolute;
	right: 20.5%;
	top: 62%; /* 위치 조정 */
}



}
#cardRecommand {
	width: 100%;
	height: 200px;
	background-color: blue;
	margin-top: 20px;
}

#cardSearchContainer {
	width: 100%;
	height: 500px;
	background-color: lightgray;
	margin-top: 200px;
}
</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid"></div>
	<div class="container">
		<!-- 승현이 작성 -->
		<div id="cardCategoryContainer">


			<h1 style="align-self: flex-start; margin-left:5%;">New</h1>

			<div id="cardNew">
				<img alt="" src="cat.jpg"> <img alt="" src="cat.jpg"> <img
					alt="" src="cat.jpg"> <img alt="" src="cat.jpg"> <img
					alt="" src="cat.jpg">
			</div>

			<img class="slick-arrow slick-prev-new" src="arrow-left.png"> <img
				class="slick-arrow slick-next-new" src="arrow-right.png">


			<h1 style="align-self: flex-start; margin-left:5%;">HOT</h1>

			<div id="cardHOT">
				<img alt="" src="cat.jpg"> <img alt="" src="cat.jpg"> <img
					alt="" src="cat.jpg"> <img alt="" src="cat.jpg"> <img
					alt="" src="cat.jpg">
			</div>
			<img class="slick-arrow slick-prev-hot" src="arrow-left.png"> <img
				class="slick-arrow slick-next-hot" src="arrow-right.png">
		</div>
		<div id="cardRecommand"></div>


		<!-- 도현 작성 -->
		<div id="cardSearchContainer"></div>

	</div>
</body>
</html>