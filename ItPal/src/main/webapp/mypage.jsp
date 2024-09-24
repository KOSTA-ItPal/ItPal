<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- chartjs -->
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function() {
		var pieCts = document.querySelector('#pieChart-sum');
		var pieCtc = document.querySelector('#pieChart-count');
		var barCt = document.querySelector('#barChart');

		const pieChart = new Chart(pieCts, {
			type : 'pie',
			data : {
				labels : [ '2020', '2021', '2022', '2023' ],
				datasets : [ {
					label : 'Dataset',
					data : [ 10, 20, 30, 40 ],
				} ]
			},
			options : {
				responsive : false, // 반응형을 끔
				maintainAspectRatio : false, // 너비와 높이 비율을 유지하지 않음
				plugins : {
					legend : {
						display : false
					// 범례를 제거
					},
				}
			}
		});
		const pieChart2 = new Chart(pieCtc, {
			type : 'pie',
			data : {
				labels : [ '2020', '2021', '2022', '2023' ],
				datasets : [ {
					label : 'Dataset',
					data : [ 10, 20, 30, 40 ],
				} ]
			},
			options : {
				responsive : false, // 반응형을 끔
				maintainAspectRatio : false, // 너비와 높이 비율을 유지하지 않음
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
				labels : [ '2020', '2021', '2022', '2023', '2024', '2025' ],
				datasets : [ {
					label : 'Dataset',
					data : [ 10, 20, 30, 40, 50, 60 ],
				} ]
			},
			options : {
				responsive : false, // 반응형을 끔
				maintainAspectRatio : false, // 너비와 높이 비율을 유지하지 않음
				plugins : { // plugins 하위에 legend 설정을 추가
					legend : {
						display : false
					// 범례를 제거
					}
				}
			}
		});
	}
</script>
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
	display: flex;
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
	<div class="jumbotron jumbotron-fluid"></div>
	<div class="container">
		<div id="myInfo">MyInfo</div>
		<div id="MGMT-13">
			<div id="MGMT-1"></div>
			<div id="MGMT-3"></div>
		</div>
		<div id="MGMT-4">
			<div id="MGMT-41">
				<canvas id="pieChart-sum" width="250" height="250"
					style="display: block; box-sizing: border-box; height: 250px; width: 250px;"></canvas>
				<canvas id="pieChart-count" width="250" height="250"
					style="display: block; box-sizing: border-box; height: 250px; width: 250px;"></canvas>
				<div id="pieChart-mess">
					<h2>소비패턴분석</h2>
					<hr>
					<div class="section">
						<p>
							<strong>소비액 순</strong>
						</p>
						<ul>
							<li>Top1. @@</li>
							<li>Top2. @@</li>
							<li>Top3. @@</li>
						</ul>
					</div>
					<div class="section">
						<p>
							<strong>소비 횟수 순</strong>
						</p>
						<ul>
							<li>Top1. @@</li>
							<li>Top2. @@</li>
							<li>Top3. @@</li>
						</ul>

					</div>
				</div>
			</div>
			<div id="MGMT-42">
				<canvas id="barChart" width="400" height="300"
					style="display: block; box-sizing: border-box; height: 300px; width: 400px;"></canvas>
				<div id="barChart-mess">
					<h2>전월 대비 지출액 증감</h2>
					<h4>
						@@ 님 전월보다 지출액 1,000,000원<br> 증가하셨습니다.
					</h4>
				</div>
			</div>
		</div>
		<div id="MGMT-2"></div>
	</div>
</body>
</html>