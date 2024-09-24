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
	
	#myInfo {
		width: 100%;
		height: 50px;
		background-color: blue;
		margin-bottom: 20px;
	}
	
	#MGMT-13 {
		width: 100%;
		height: 300px;
		display: flex;
		justify-content: space-between;
	}
	
	#MGMT-1 {
		width: 48%;
		height: 100%;
		border: 1px solid red;
		background-color: gray;
	}
	
	#MGMT-3 {
		width: 48%;
		height: 100%;
		border: 1px solid red;
		background-color: gray;	
	}
	
	#MGMT-4 {
		width: 100%;
		height: 800px;
		display: flex;
		flex-direction: column;
		justify-content: space-between;
		align-content: space-between;
		margin-top: 50px;
		border: 1px solid blue;
	}
	
	#MGMT-41 {
		width: 100%;
		height: 50%;
		background-color: gray;	
	}
	
	#MGMT-42 {
		width: 100%;
		height: 50%;
		background-color: lightgray;
	}
	
	/* MGMT-2의 height 는 height 설정 X -> 동적으로 진행하는게 맞을듯?? */
	#MGMT-2 {
		width: 100%;	
		height: 2000px;
		margin-top: 50px;
		border: 1px solid green;
		background-color: gray;	 
	}

</style>
</head>
<body>
<div class="jumbotron jumbotron-fluid">

</div>
<div class="container">
	<div id="myInfo">MyInfo</div>
	<div id="MGMT-13">
		<div id="MGMT-1"></div>
		<div id="MGMT-3"></div>
	</div>
	<div id="MGMT-4">
		<div id="MGMT-41"></div>
		<div id="MGMT-42"></div>
	</div>
	<div id="MGMT-2"></div>
</div>
</body>
</html>