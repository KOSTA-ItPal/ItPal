<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 숫자 표시  -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>


<!-- myPage.css -->
<link rel="stylesheet" type="text/css" href="myPage.css">

<style>

	@font-face {
    font-family: 'LINESeedKR-Bd';
    src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2') format('woff2');
    font-weight: 700;
    font-style: normal;
	}

	*{
		font-family: 'LINESeedKR-Bd';
	}

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
	
	#MGMT-2 {
		width: 100%;
		margin-top: 50px;
		border: 5px solid green;
		background-color: #F9FCFF;	
	}

	/* 수정 */
    #asset{
        width: 100%;
        height : 200px;
        background-color: #1C1678;
        color: white;
        font-size: 20;
		margin-bottom: 30px;
	}

    #showAmount{
        margin-top: 30px;
		padding: 0 80px;
        display: flex;
        justify-content: space-between;
        align-items: center;
		font-size: 20;
        
    }
    #totalAmount{
        display: inline-block;
        font-size: 30;
        text-align: right;
    }
	
	div[id$="Account"]{
		margin : 0 80px;
		border: 3px solid gray;
		border-radius: 10px;
		background-color:  #f5eeee;
		margin-bottom: 30px;
		padding : 30px;

	}
	.accountInfo{
		font-size: 20;
		border-bottom: 3px solid gray;
	}
	.account{
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin : 30px;
		font-size: 20px;
	}
	.logoAndInfo{
		display: flex;
		align-items: center;
	}
	.bankLogo{
		width : 60px;
		height : 60px;
		border-radius: 50%;
		margin-right: 10px;
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
	<div id="MGMT-2">
        <div id ="asset">
            <div style="text-align: center; font-size: 40pt;">총자산 현황</div>
            <div id = "showAmount">
                <div>총 자산 금액 </div>
                <div id = "totalAmount"> 
                	<fmt:formatNumber value="${totalAsset}" pattern="#,###"/>원
                </div>
            </div>
        </div>
		<div id ="demandAccount"> 
			<div class = "accountInfo">입출금</div>
			<c:choose>
				<c:when test="${fn:length(demandAccounts)== 0}">
					<div style="padding: 30px;">가지고 있는 입출금 통장이 없습니다.</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="demandAccounts" items="${demandAccounts}">
						<div class ="account">
							<div class = "logoAndInfo">
								<img class = "bankLogo" src="img/bank_logo/${demandAccounts.bankName}.png" > 
								<div>
									<div>${demandAccounts.bankName}</div>
									<div class ="accountNumber">${demandAccounts.accountNum}</div>
								</div>
							</div>
							<div class ="amount">
								<fmt:formatNumber value="${demandAccounts.balance}" pattern="#,###"/>원
							</div>
						</div>	
					</c:forEach>				
				</c:otherwise>
			</c:choose>	
		</div>
		
		<div id ="fixedAccount">
			<div class = "accountInfo">예금</div>
			<c:choose>
				<c:when test="${fn:length(fixedAccounts)== 0}">
					<div style="padding: 30px;">가지고 있는 예금 통장이 없습니다.</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="fixedAccounts" items="${fixedAccounts}">
						<div class ="account">
							<div class = "logoAndInfo">
								<img class = "bankLogo" src="img/bank_logo/${fixedAccounts.bankName}.png" > 
								<div>
									<div>${fixedAccounts.bankName}</div>
									<div class ="accountNumber">${fixedAccounts.accountNum}</div>
								</div>
							</div>
							<div class ="amount">
								<fmt:formatNumber value="${fixedAccounts.balance}" pattern="#,###"/>원
							</div>
						</div>	
					</c:forEach>				
				</c:otherwise>
			</c:choose>		

		</div>
		
		<div id ="installmentAccount">
			<div class = "accountInfo">적금</div> 
			<c:choose>
				<c:when test="${fn:length(installmentAccounts)== 0}">
					<div style="padding: 30px;">가지고 있는 적금 통장이 없습니다.</div>
				</c:when>
				<c:otherwise>
					<c:forEach var="installmentAccounts" items="${installmentAccounts}">						
						<div class ="account">
							<div class = "logoAndInfo">
								<img class = "bankLogo" src="img/bank_logo/${installmentAccounts.bankName}.png" > 
								<div>
									<div>${installmentAccounts.bankName}</div>
									<div class ="accountNumber">${installmentAccounts.accountNum}</div>
								</div>
							</div>
							<div class ="amount">
								<fmt:formatNumber value="${installmentAccounts.balance}" pattern="#,###"/>원
							</div>
						</div>	
					</c:forEach>				
				</c:otherwise>
			</c:choose>	
		</div>
    </div>
</div>

</body>
</html> 