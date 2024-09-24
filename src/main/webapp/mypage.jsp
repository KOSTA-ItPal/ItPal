<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>

<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./mypage.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- chartjs -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<title>Insert title here</title>

<style type="text/css">
<.jumbotron {
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
	background-color: #F9FCFF;
	display: flex;
}

#pieChart {
	display: grid;
	grid-template-rows: 10px 1fr;
	grid-template-columns: 1fr 1fr;
	gap: 30px;
	margin-left: 50px;
	margin-top: 50px;
	text-align: center;
}

#pieChart-sum {
	margin-left: 8%;
	margin-top: 5%;
}

#pieChart-count {
	margin-left: 3%;
	margin-top: 5%;
}

#pieChart-mess {
	display: grid;
	grid-template-columns: 1.5fr 1fr; /* 두 개의 동일한 너비의 컬럼 */
	grid-template-rows: 1fr 1fr;
	gap: 10px; /* 컬럼 사이의 간격 */
	padding: 20px;
	width: 400px; /* 전체 컨테이너의 너비 설정 */
	margin: 75px auto;
	color: black;
}

.section {
	display: flex;
	flex-direction: column;
}

ul {
	padding: 0;
	list-style: none; /* li 앞의 점을 없앰 */
}

#MGMT-42 {
	width: 100%;
	height: 50%;
	background-color: lightgray;
	display: flex;
	flex-direction: row-reverse;
}

#barChart {
	margin-right: 10%;
	margin-top: 4%;
}

#barChart-mess {
	margin-top: 10%;
	margin-right: 12%;
	margin-left: 8%;
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
<script type="text/javascript">



	window.onload = function() {
		var pieCts = document.querySelector('#pieChart-sum');
		var pieCtc = document.querySelector('#pieChart-count');
		var barCt = document.querySelector('#barChart');
		   var Cntpattern = {
		            <c:forEach var="entry" items="${Cntpattern}" varStatus="status">
		                '${entry.key}': ${entry.value}<c:if test="${!status.last}">,</c:if>
		            </c:forEach>
		        };
		   var Sumpattern = {
		            <c:forEach var="entry" items="${Sumpattern}" varStatus="status">
		                '${entry.key}': ${entry.value}<c:if test="${!status.last}">,</c:if>
		            </c:forEach>
		        };
		   var status = {
		            <c:forEach var="entry" items="${status}" varStatus="status">
		                '${entry.key}': ${entry.value}<c:if test="${!status.last}">,</c:if>
		            </c:forEach>
		        };
		   
		   // 값(value) 기준으로 정렬하는 함수
		    function sortByValue(obj) {
		        return Object.entries(obj) // 객체를 배열로 변환 [key, value]
		            .sort((a, b) => b[1] - a[1]) // value 기준 오름차순 정렬
		            .reduce((acc, [key, value]) => {
		                acc[key] = value; // 다시 객체로 변환
		                return acc;
		            }, {});
		    } // 정렬된 객체
		    var sortedCntpattern = sortByValue(Cntpattern);
		    var sortedSumpattern = sortByValue(Sumpattern);
		    console.log("Sorted Cntpattern:", sortedCntpattern);
		    console.log("Sorted Sumpattern:", sortedSumpattern);
		    // HTML 요소에 목록 채우기
		    function fillList(containerId, sortedData) {
		        var list = document.getElementById(containerId);
		        var index = 1;
		        for (var key in sortedData) {
		            if (sortedData.hasOwnProperty(key)) {
		                var listItem = document.createElement('li');
		                listItem.textContent = 'Top' + index + '. ' + key;
		                list.appendChild(listItem);
		                index++;
		                if (index > 3) break; // Top 3까지만 표시
		            }
		        }
		    }

		    // Cntpattern과 Sumpattern 목록을 채움
		    fillList('cntpattern-list', sortedCntpattern);
		    fillList('sumpattern-list', sortedSumpattern);
		
		
		  
		const cntpieChart = new Chart(pieCtc, {
			type : 'pie',
			data : {
				labels : Object.keys(Cntpattern),
				datasets : [ {
					label : 'Count Payment Pattern',
					data : Object.values(Cntpattern),
				} ]
			},
			options : {
				responsive : false, // 반응형을 끔
				maintainAspectRatio : false, // 너비와 높이 비율을 유지하지 않음
				plugins : {
					legend : {
						display : false
					// 범례를 제거
					
					}
				}
			}
		});
		const sumpieChart = new Chart( pieCts, {
			type : 'pie',
			data : {
				labels : Object.keys(Sumpattern),
				datasets : [ {
					label : 'Sum Payment Pattern',
					data : Object.values(Sumpattern),
				} ]
			},
			options : {
				responsive : false, // 반응형을 끔
				maintainAspectRatio : false,// 너비와 높이 비율을 유지하지 않음
				plugins : {
					legend : {
						display : false
					// 범례를 제거
					},
				}
			}
		});
		const barChart = new Chart(barCt, {
			type : 'bar',
			data : {
				labels : Object.keys(status),
				datasets : [ {
					label : 'Dataset',
					data : Object.values(status),
				} ]
			},
			options : {
				responsive : false, // 반응형을 끔
				maintainAspectRatio : false, // 너비와 높이 비율을 유지하지 않음
				plugins : { // plugins 하위에 legend 설정을 추가
					legend : {
						display : false
					// 범례를 제거
					},
				}
			}
		});
		 // 현재 날짜로부터 이번 달을 구함
	    var currentDate = new Date();
	    var currentMonth = currentDate.getMonth() + 1; // JavaScript의 getMonth는 0부터 시작하므로 1을 더해줘야 함
	    var lastMonth = currentMonth === 1 ? 12 : currentMonth - 1; // 1월이면 12월로, 그 외에는 전달로 설정

	    // status 객체에서 이번 달과 전달의 지출액을 추출
	    var currentMonthSpending = status[currentMonth];
	    var lastMonthSpending = status[lastMonth];
	    console.log(currentMonthSpending);
	    console.log(lastMonthSpending);
	    // 지출액 차이를 계산
	    var spendingDifference = currentMonthSpending - lastMonthSpending;
	    var differenceText = spendingDifference > 0 ? '증가' : '감소';
	    var absoluteDifference = Math.abs(spendingDifference).toLocaleString(); // 차이를 천 단위로 포맷팅
	    console.log('지출 차이값 확인',absoluteDifference); // 지출 차이값 확인
	    console.log('증감',differenceText); // 증가 또는 감소 텍스트 확인
	    const message = '유저 님 전월보다 지출액 ' + absoluteDifference + '원 ' + differenceText + '하셨습니다.';
	    console.log('메시지', message);
	    document.getElementById("spending").innerHTML = message;
	    var achiveRate = ${achiveRate};
	}
	
</script>

</head>

<body>
	<div class="jumbotron jumbotron-fluid"></div>
	<div class="container">
		<div id="myInfo">MyInfo</div>
		<div id="MGMT-13">
			<div id="MGMT-1"></div>
			<div id="MGMT-3">
				<div class="progress" style="height: 20px">
					<div class="progress-bar"
						style="width: ${achiveRate}% ; height: 20px">
					</div>
				</div>
			</div>
		</div>
		
		<a href="cardList.do">테스트용 엥커</a>
		<div id="MGMT-4">
			<div id="MGMT-41">
				<div id="pieChart">
					<p>
						<strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;소비액 순</strong>
					</p>
					<p>
						<strong>소비 횟수 순</strong>
					</p>
					<canvas id="pieChart-sum" width="250" height="250"
						style="display: block; box-sizing: border-box; height: 250px; width: 250px;"></canvas>

					<canvas id="pieChart-count" width="250" height="250"
						style="display: block; box-sizing: border-box; height: 250px; width: 250px;"></canvas>
				</div>
				<div id="pieChart-mess">
					<h2>소비패턴분석</h2>
					<hr>
					<div class="section">
						<p>
							<strong>소비액 순</strong>
						</p>
						<ul id="sumpattern-list">
							<c:forEach var="entry" items="${sortedCntpattern}" begin="0"
								end="2" varStatus="status">
								<li>Top${status.index + 1}. ${entry.key}</li>
							</c:forEach>
						</ul>
					</div>
					<div class="section">
						<p>
							<strong>소비 횟수 순</strong>
						</p>
						<ul id="cntpattern-list">
							<c:forEach var="entry" items="${sortedCntpattern}" begin="0"
								end="2" varStatus="status">
								<li>Top${status.index + 1}. ${entry.key}</li>
							</c:forEach>
						</ul>

					</div>
				</div>
			</div>
			<div id="MGMT-42">
				<canvas id="barChart" width="400" height="300"
					style="display: block; box-sizing: border-box; height: 300px; width: 400px;"></canvas>
				<div id="barChart-mess">
					<h2>전월 대비 지출액 증감</h2>
					<h4 id="spending">데이터</h4>
				</div>
			</div>
		</div>
		<div id="MGMT-2"></div>
	</div>
</body>
</html>