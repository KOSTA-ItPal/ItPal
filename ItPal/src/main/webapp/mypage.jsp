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
<link rel="stylesheet" type="text/css" href="mypage.css"><!-- CSS 파일 링크 -->
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<title>잇플(ItPal):: 마이페이지</title>
</head>
<body>
	<jsp:include page="header.html" />	
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<div id="myInfo">
				<div id="myInfo-content">${user.userName}님 자산관리</div>
			</div>
			<div id="MGMT-13">
				<div id="MGMT-1">
					<!-- 예산 설정이 되어있지 않을 때 -->
					<c:if test="${budget==0}">
						<div id="beforeBudSetContainer">
							<div id="beforeBudSetComment">
								<b> 예산을 설정하시면 </b><br> <b> 당신의 소비 여정을 스마트하게 </b><br> <b>
									만들어 줄 것입니다. </b><br>
								<hr>
							</div>
							<div id="setBudContainer">
								<div id="setBudget">
									<form class="form-group" action="setBudget.do" method="post"
										id="setBudgetForm">
										<label for="setBudgetInput">월 예산 설정</label> <input
											name="budget" class="form-control form-control-sm"
											type="text" placeholder="한달 지출금액을 입력해주세요."
											id="setBudgetInput"
											onkeyup='formatNumber(["setBudgetInput"]);' /> <input
											class="btn btn-sm" type="submit" value="설정하기"
											id="setBudgetBtn">
									</form>
								</div>
							</div>
						</div>
					</c:if>

					<!-- 예산 설정이 되어있을 때 -->
					<c:if test="${budget>0}">
						<div id="afterBudSetContainer">
							<div id="afterBudSetHeader">
								<span class="left">권장 소비액 확인</span> <span class="right">
									<img id="editBtn" style="width: 15px; height: 15px;"
									src="https://www.svgrepo.com/show/42233/pencil-edit-button.svg"
									onclick="">
								</span>
							</div>
							<div id="afterBudSetList">
								<div class="left">월 예산:</div>
								<div class="right" id="budget">${budget}원</div>
								<br>
								<div class="left">오늘까지 권장소비액:</div>
								<div class="right" id="recommSpend">${recommSpend}원</div>
								<br>
								<div class="left">이번달 지출금액:</div>
								<div class="right" id="totalSpend">${totalSpend}원</div>
								<br>
								<div class="left">지출비율:</div>
								<div class="right">${spendRate}%</div>
								<br>
								<hr>
								<div id="budgetAlert">
									<form action="setBudget.do" method="post">
										<div id="afterSetBudget">
											<c:if test="${spendRate>=100}">
												<div id="comment">예산초과을 초과했어요!</div>
												<img src="img/characters/Danger_Alarm.png">
												<br>
											</c:if>
											<c:if test="${spendRate>=80 && spendRate<100}">
												<div id="comment">소비습관을 정비해보세요</div>
												<img src="img/characters/Online_Payment_Alert.png">
												<br>
											</c:if>
											<c:if test="${spendRate<80}">
												<div id="comment">지금처럼만 관리해주세요</div>
												<img src="img/characters/thumb_up.png">
												<br>
											</c:if>
										</div>
									</form>
								</div>
							</div>
						</div>
					</c:if>
				</div>
				<div id="MGMT-3">
					<!-- 저축 목표액 설정이 되어있지 않을 때 -->
					<c:if test="${saving==0}">
						<div id="beforeSaveSetContainer">
							<div id="beforeSaveSetComment">
								<b> 저축 목표액을 설정하시면 </b><br> <b> 당신의 목표에 한 걸음 더 </b><br>
								<b> 가까워집니다. </b><br>
								<hr>
							</div>
							<div id="setSaveContainer">
								<div id="setSave">
									<form class="form-group" action="setSave.do" method="post"
										id="setSaveForm">
										<label for="setSaveInput">저축 목표액 설정</label> <input
											name="saving" class="form-control form-control-sm"
											type="text" placeholder="저축 목표액을 입력해주세요." id="setSaveInput"
											onkeyup='formatNumber(["setSaveInput"]);' /> <input
											class="btn btn-sm" type="submit" value="설정하기" id="setSaveBtn">
									</form>
								</div>
							</div>
						</div>
					</c:if>
					<!-- 저축 목표액 설정이 되어있을 때 -->
					<c:if test="${saving>0}">
						<div id="afterSaveSetContainer">
							<div id="afterSaveSetHeader">
								<span class="left">저축현황 확인</span> <span class="right"> <img
									id="editBtn2" style="width: 15px; height: 15px;"
									src="https://www.svgrepo.com/show/42233/pencil-edit-button.svg"
									onclick="">
								</span>
							</div>
							<div id="afterSaveSetList">
								<div class="left">나의 순자산:</div>
								<div class="right" id="totalAsset">${totalAsset}원</div>
								<br>
								<div class="left">저축목표:</div>
								<div class="right" id="saving">${saving}원</div>
								<br>
								<div class="left">달성률:</div>
								<div class="right">${achiveRate}%</div>
								<br>
								<div class="progress">
									<div class="progress-bar" id="progressBar"
										style="width:${achiveRate}%"></div>
								</div>
								<hr>
								<div id="savingAlert">
									<form action="setSave.do" method="post">
										<div id="afterSetSave">
											<c:if test="${achiveRate == 100}">
												<div id="comment">저축목표 달성! 축하드립니다!</div>
												<img src="img/characters/4_rabbit.png">
												<img src="img/characters/Capital.png">
											</c:if>
											<c:if test="${achiveRate >= 80 && achiveRate < 100}">
												<div id="comment">부자가 되고 있어요! 조금만 더 모아봐요!</div>
												<img src="img/characters/4_rabbit.png">
												<img src="img/characters/Financial_Growth.png">
											</c:if>
											<c:if test="${achiveRate >= 60 && achiveRate < 80}">
												<div id="comment">토끼가 알에서 나왔네요 열심히 모아봐요!</div>
												<img src="img/characters/3_rabbit.png">
											</c:if>
											<c:if test="${achiveRate >= 40 && achiveRate < 60}">
												<div id="comment">꾸준히 돈을 모아 토끼가 태어났어요!</div>
												<img src="img/characters/2_rabbit.png">
											</c:if>
											<c:if test="${achiveRate >= 20 && achiveRate < 40}">
												<div id="comment">어떤 동물이 나올까요?</div>
												<img src="img/characters/1_egg_gift_(3).png">
											</c:if>
											<c:if test="${achiveRate >= 0 && achiveRate < 20}">
												<div id="comment">
													저축목표를 설정하셨군요?<br>선물로 알을 드릴게요
												</div>
												<img src="img/characters/1_egg_gift.png">
											</c:if>
										</div>
									</form>
								</div>
							</div>
						</div>
					</c:if>
				</div>
			</div>
			<div id="MGMT-4">
				<div id="MGMT-41">
					<form action="cardsearch.jsp">
						<input type="submit" value="card">
					</form>
				</div>
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
<script type="text/javascript">
		
	function formatNumber(inputs) {
	    inputs.forEach(id => {
	        const input = document.getElementById(id);
	        let value = input.value.replace(/,/g, '');
	        if (!isNaN(value) && value !== '') {
	            input.value = Number(value).toLocaleString();
	        } else {
	            input.value = '';
	        }
	    });
	}
	
	$(function(){
		//loading 숫자 천단위로 formating
	    $('#budget').text(Number(${budget}).toLocaleString() + '원');
	    $('#recommSpend').text(Number(${recommSpend}).toLocaleString() + '원');
	    $('#totalSpend').text(Number(${totalSpend}).toLocaleString() + '원');
	    $('#totalAsset').text(Number(${totalAsset}).toLocaleString() + '원');
	    $('#saving').text(Number(${saving}).toLocaleString() + '원');
	    
		//budget 수정 버튼
		$('#editBtn').on('click', function() {
			let inputField = `<input type='text' name="budget" id='newBudgetInput' onkeyup='formatNumber(["newBudgetInput"]);' class='form-control form-control-sm' placeholder="설정할 예산을 입력하세요." style="text-align:center"/>`;
			let newBudgetBtn = '<button id="saveBtn" class="btn btn-sm">저장하기</button>';

			$('#afterSetBudget').html(inputField);
			$('#afterSetBudget').append(newBudgetBtn);
			
			$('#afterSetBudget').on('click', '#saveBtn', function() {
				let newBudget = $('#newBudgetInput').val().replace(/,/g, ''); // 쉼표 제거
				//포맷팅
			    $('#budget').text(Number(${budget}).toLocaleString() + '원');
			    $('#recommSpend').text(Number(${recommSpend}).toLocaleString() + '원');
			    $('#totalSpend').text(Number(${totalSpend}).toLocaleString() + '원');

				if (isNaN(newBudget) || newBudget.trim() === '') {
					alert('반드시 숫자를 입력해주세요.');
					return false;
				}//if
			});//saveBtn(budget)
		});//editBtn(budget)

		//savings 수정 
		$('#editBtn2').on('click', function() {
			let inputField = `<input type='text' name="saving" id='newSavingInput' onkeyup='formatNumber(["newSavingInput"]);' class='form-control form-control-sm' placeholder="설정할 저축목표를 입력하세요." style="text-align:center"/>`;
			let newSavingBtn = '<button id="saveBtn2" class="btn btn-sm">저장하기</button>';

			$('#afterSetSave').html(inputField);
			$('#afterSetSave').append(newSavingBtn);

			$('#afterSetSave').on('click', '#saveBtn2', function() {
				let newSaving = $('#newSavingInput').val().replace(/,/g, ''); // Remove commas
				//포맷팅
			    $('#totalAsset').text(Number(${totalAsset}).toLocaleString() + '원');
			    $('#saving').text(Number(${saving}).toLocaleString() + '원');

				if (isNaN(newSaving) || newSaving.trim() === '') {
					alert('반드시 숫자를 입력해주세요.');
					return false;
				}//if
			});//saveBtn2(saving)
		});//editbtn2(saving)
	});//ready
</script>
</body>
</html>
