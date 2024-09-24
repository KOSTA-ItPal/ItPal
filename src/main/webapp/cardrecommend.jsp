<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
	</div>
</div>
</body>
</html>