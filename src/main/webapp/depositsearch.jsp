<<<<<<< HEAD:src/main/webapp/depositsearch.jsp
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
	
	#depositSearch {
		width: 100%;
		height: 300px;
		background-color: gray;
	}
</style>
</head>
<body>
<div class="jumbotron jumbotron-fluid"></div>
<div class="container">

	<!-- 지남님은 여기서부터 알아서 작성! -->
	<div id="depositSearch"></div>

</div>
</body>
</html>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
    />
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <title>Insert title here</title>

    <style>
      /* 웹폰트(세가지 굵기) */
      @font-face {
        font-family: "LINESeedKR-Th";
        src: url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Th.woff2");
        font-weight: 700;
        font-style: normal;
      }
      @font-face {
        font-family: "LINESeedKR-Rg";
        src: url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Rg.woff2");
        font-weight: 700;
        font-style: normal;
      }
      @font-face {
        font-family: "LINESeedKR-Bd";
        src: url("https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/LINESeedKR-Bd.woff2");
        font-weight: 700;
        font-style: normal;
      }

      /* 공통속성 */
      * {
        /* border: 1px solid gray; */
        box-sizing: border-box;
      }

      /* 텍스트 스타일 */
      /* 폰트 적용 */
      body {
        font-family: "LINESeedKR-Rg";
      }
      #option-box span,
      .container > p,
      table th,
      #calculator > p,
      .calculator-option > span,
      .calculator-option > label,
      .result-interestAmount span,
      .result-maturityAmount span {
        font-family: "LINESeedKR-Bd";
      }
      /* 글자 크기 설정 */
      .container > p {
        font-size: 35px;
      }
      #calculator > p {
        font-size: 22px;
      }
      #option-box span {
        font-size: 18px;
      }
      #calculator > span,
      #calculator > ul > li {
        font-size: small;
      }
      /* 글자 색 설정 */
      #calculator > span,
      .calculator-option > span > span,
      .calculator-option > label > span {
        color: tomato;
      }

      /* 마진 */
      /* 페이지 제목 마진 설정 */
      .container > p {
        margin: 40px 0 10px 0;
      }
      /* 예적금 계산기 마진 설정 */
      .calculator-option {
        margin-bottom: 12px;
      }
      #calculator > p {
        margin-bottom: 13px;
      }
      #calculator > span {
        margin-bottom: 15px;
      }
      #calculator > ul {
        display: flex;
        flex-direction: column;
        gap: 10px;
        padding-left: 18px;
        margin: 13px 0 0 0;
      }
      /* 예적금 계산기 결과 마진 설정 */
      #calculator-result {
        margin: 18px 0 7px 6px;
      }
      .result-interestAmount {
        margin-bottom: 10px;
      }

      /* 정렬 */
      /* 페이지 제목 정렬 */
      .container > p {
        width: 100%;
      }
      /* 예적금 계산기 radio 정렬 */
      .radio-box {
        display: flex;
        gap: 12px;
        margin: 0;
      }
      #header {
        width: 100%;
        height: 70px;
        margin-bottom: 20px;
        background: #ececec;
      }
      /* 예적금 계산기 결과 정렬 */
      .result-interestAmount,
      .result-maturityAmount {
        display: flex;
      }
      .result-interestAmount > div,
      .result-maturityAmount > div {
        width: 70%;
        text-align: right;
      }
      #calculator-result {
        display: flex;
        flex-direction: column;
        gap: 25px;
      }

      .container {
        /* border: 1px solid red; */
        margin-left: 13%;
        margin-right: 10px;
        display: flex;
        flex-direction: column;
        align-items: center;
        gap: 40px;
        min-width: 1140px;
      }

      #page {
        display: flex;
      }

      #option-box {
        width: 80%;
        /* background-color: gray; */
        /* 여기까지 도현님이 작성한 option의 내용 */
        display: flex;
        flex-direction: column;
        align-items: center;
        padding: 27px 40px 22px 40px;
        border: 2px solid rgb(199, 199, 199);
        border-radius: 20px;
        background: #fff;
      }
      #option-list {
        width: 100%;
      }
      #calculator {
        width: 400px;
        height: 100%;
        margin: 10px;
        padding: 25px 40px;
        display: flex;
        flex-direction: column;
        flex-shrink: 0;
        position: sticky;
        top: 10%;
        border: 2px solid rgb(199, 199, 199);
        border-radius: 20px;
        background: #fff;
      }

      /* 옵션 버튼 모양 및 버튼 글자 커스텀 */
      .option {
        display: flex;
        flex-direction: row;
        align-items: center;
        gap: 15px;
        margin-bottom: 13px;
      }
      .radio_area label {
        cursor: pointer;
        display: flex;
        align-items: center;
        /* height: 40px; */
        padding: 8px 23px;
        border-radius: 30px;
        color: #909090;
        background: #ededed;
        transition: all 0.2s;
      }
      .radio_area label:hover {
        background: #e1e1e1;
      }
      .radio_area input[type="radio"] {
        display: none;
      }
      .radio_area input[type="radio"]:checked + label {
        color: #fff;
        background: #111;
      }
      /*  */

      .option > span {
        width: 170px;
        padding-left: 10px;
      }

      /* 버튼 커스텀 */
      button {
        margin-top: 20px;
        padding: 0 27px;
        height: 45px;
        border-radius: 10px;
        background: #121212;
        color: #e1e1e1;
      }

      button:active {
        background: #e1e1e1;
        color: #121212;
      }

      /* 예적금 선택 */
      #depositType-box {
        display: flex;
      }

      table * {
        /* font-size: small; */
        text-align: center;
        border: 1px solid rgb(209, 209, 209);
      }
      table a {
        border: none;
      }

      th,
      td {
        padding: 5px;
      }

      th {
        background: #121212;
        color: rgb(242, 242, 242);
      }

      table .primeCond {
        text-align: left;
      }

      #bankTypeCol {
        width: 80px;
      }

      #bankNameCol {
        width: 110px;
      }

      #productNameCol {
        width: 200px;
      }

      #depositPeriodCol {
        width: 60px;
      }

      #calMethodCol {
        width: 80px;
      }

      #beforeTaxRateCol {
        width: 60px;
      }

      #afterTaxRateCol {
        width: 60px;
      }

      #primeCondCol {
        width: 290px;
      }

      /* #targetCol {
        width: 140px;
      } */

      #registerTypeCol {
        width: 120px;
      }

      tbody tr:hover {
        background: #ededed;
      }
      tbody tr:active {
        background: #e1e1e1;
      }

      #calculator p {
        text-align: center;
      }
      .calculator-option {
        display: flex;
        align-items: center;
        position: relative;
        left: 50%;
        transform: translateX(-50%);
      }
      label {
        margin: 0px;
      }

      #calculator-amount {
        width: 58%;
      }
      label[for="calculator-amount"] {
        width: 42%;
      }

      #calculator-depositPeriod {
        width: 58%;
      }
      label[for="calculator-depositPeriod"] {
        width: 42%;
      }

      #calculator-beforeTaxRate,
      #calculator-afterTaxRate,
      .calculator-option > .radio-box {
        width: 58%;
      }

      label[for="calculator-beforeTaxRate"],
      label[for="calculator-afterTaxRate"],
      .calculator-option > span {
        width: 42%;
      }

      #calculatorBtn {
        position: relative;
        left: 50%;
        transform: translateX(-50%);
      }

      .result {
        background: rgb(242, 242, 242);
      }

      .radio-box {
        display: flex;
        justify-content: space-evenly;
      }

      input {
        transition: background-color 1s ease; /* 배경색 전환 효과 */
        padding: 5px 16px;
        text-align: right; /* 텍스트를 오른쪽으로 정렬 */
        border: 2px solid rgb(199, 199, 199);
        border-radius: 6px;
      }

      /* input number화살표 제거 */
      /* Chrome, Safari, Edge, Opera */
      input::-webkit-outer-spin-button,
      input::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }
      /* Firefox  */
      input[type="number"] {
        -moz-appearance: textfield;
      }

      #header {
        background: #fafafc;
      }
    </style>

    <script>
      $(() => {
        $("#searchBtn").click(function () {
          // 선택된 옵션 값을 가져옴
          var bankSector = $("input[name='bankSector']:checked").attr("id");
          var calMethod = $("input[name='calMethod']:checked").attr("id");
          var registerPeriod = $("input[name='registerPeriod']:checked").attr(
            "id"
          );
          var depositType = $("input[name='depositType']:checked").attr("id");

          // AJAX 요청
          $.ajax({
            url: "depositSearch.do",
            type: "get",
            data: {
              bankSector: bankSector,
              calMethod: calMethod,
              registerPeriod: registerPeriod,
              depositType: depositType,
            }, //data
            dataType: "json",
            success: function (depositList) {
              alert("검색결과 수 : " + depositList.length);

              var html = "";

              $.each(depositList, function (index, deposit) {
                html += "<tr>";
                html +=
                  "<td class=bankTypeCol>" + deposit.bank.bankType + "</td>";
                html +=
                  "<td class=bankNameCol>" + deposit.bank.bankName + "</td>";
                html +=
                  "<td class=productNameCol><a href=" +
                  deposit.dUrl +
                  ">" +
                  deposit.productName +
                  "</a></td>";
                html +=
                  "<td class=beforeTaxRateCol>" +
                  deposit.beforeTaxRate +
                  "</td>";
                html +=
                  "<td class=afterTaxRateCol>" + deposit.afterTaxRate + "</td>";

                if (deposit.primeCond.length > 40) {
                  html +=
                    "<td class=primeCondCol>" +
                    deposit.primeCond.substring(0, 40) +
                    "<br/>...</td>";
                } else {
                  html +=
                    "<td class=primeCondCol>" + deposit.primeCond + "</td>";
                }
                html += "<td class=calMethodCol>" + deposit.calMethod + "</td>";

                html +=
                  "<td class=depositPeriodCol>" +
                  deposit.depositPeriod +
                  "</td>";

                html +=
                  "<td class=registerTypeCol>" + deposit.registerType + "</td>";
                html += "</tr>";
              }); //each

              $("tbody").html(html);

              // 동적으로 추가된 데이터에 대해 jQuery 기능 재적용
              applyJQueryFunctions();
            }, //success
            error: function (xhr, status, error) {
              console.error("에러 발생:", error);
              console.error("상태:", status);
              console.error("응답:", xhr.responseText);
            }, //error
          }); //ajax
        }); //click

        var inputs = document.querySelectorAll("input");

        // 각 input 필드에 대해 이벤트 리스너 추가
        inputs.forEach(function (input) {
          input.addEventListener("input", function () {
            // 배경색 변경
            input.style.backgroundColor = "lightgray"; // 예: 노란색 배경

            // 1초 후 배경색을 원래대로 복원
            setTimeout(function () {
              input.style.backgroundColor = "#fff"; // 원래 배경색 (기본값)
            }, 1000); // 1000ms = 1초
          });
        });

        // 동적으로 추가된 요소에 jQuery 기능을 적용하는 함수
        function applyJQueryFunctions() {
          //tr 클릭시 해당 tr의 정보가 계산기 폼에 주입되게 함
          $("tbody").on("click", "tr", function () {
            //예금/적금
            var productName = $(this).children(".productNameCol").text();
            var depositType = "";
            if (productName.includes("예금")) {
              depositType = "예금";
            }
            if (productName.includes("적금")) {
              depositType = "적금";
            }
            //예금/적금 기간
            var depositPeriod = $(this).children(".depositPeriodCol").text();
            //세전 이자율
            var beforeTaxRate = $(this).children(".beforeTaxRateCol").text();
            //세후 이자율
            var afterTaxRate = $(this).children(".afterTaxRateCol").text();
            //이자 계산 방법
            var calMethod = $(this).children(".calMethodCol").text();

            //폼 안에 값 주입
            if (depositType == "예금") {
              $("#calculator-savingsAccount").attr("checked", "true");
            }
            if (depositType == "적금") {
              $("#calculator-installmentSavingsAccount").attr(
                "checked",
                "true"
              );
            }
            $("#calculator-depositPeriod").val(depositPeriod);
            $("#calculator-beforeTaxRate").val(beforeTaxRate);
            $("#calculator-afterTaxRate").val(afterTaxRate);
            if (calMethod == "단리") {
              $("#calculator-simpleInterest").attr("checked", "true");
            }
            if (calMethod == "복리") {
              $("#calculator-compoundInterest").attr("checked", "true");
            }

            // 각 input에 대해 배경색 전환 효과 적용
            var inputsToHighlight = [
              "#calculator-depositPeriod",
              "#calculator-beforeTaxRate",
              "#calculator-afterTaxRate",
            ];

            inputsToHighlight.forEach(function (selector) {
              var input = $(selector);
              input.trigger("input"); // 값 설정 및 input 이벤트 트리거
              input.css("background-color", "lightgray"); // 배경색 변경
              setTimeout(function () {
                input.css("background-color", "#fff"); // 원래 배경색으로 복원
              }, 1000); // 1000ms = 1초
            });
          }); //click

          //계산기 버튼 클릭시 세전이자, 세후이자, 세전만기액, 세후만기액 계산
          $("#calculatorBtn").on("click", function () {
            //예금인가?
            var isSavingsAccount =
              $("#calculator-savingsAccount").prop("checked") == true;
            //적금인가?
            var isInstallmentSavingsAccount =
              $("#calculator-installmentSavingsAccount").prop("checked") ==
              true;
            //단리인가?
            var isSimpleInterest =
              $("#calculator-simpleInterest").prop("checked") == true;
            //복리인가?
            var isCompoundInterest =
              $("#calculator-compoundInterest").prop("checked") == true;

            // 입력값들을 숫자로 변환하고, 이자율을 퍼센트에서 소수로 변환
            var amount = parseFloat(
              $("#calculator-amount").val().replace(/,/g, "")
            );
            var depositPeriod = parseInt($("#calculator-depositPeriod").val());
            var beforeTaxRate =
              parseFloat($("#calculator-beforeTaxRate").val()) * 0.01;
            var afterTaxRate =
              parseFloat($("#calculator-afterTaxRate").val()) * 0.01;

            // 입력값이 null, undefined, NaN 또는 빈 문자열인 경우 체크
            if (isNaN(amount) || isNaN(depositPeriod) || isNaN(beforeTaxRate)) {
              alert("필수입력 항목을 모두 작성해주세요");
            } else {
              //이자액, 만기액 계산
              //세전이자액
              var interestAmountBeforeTax = 0;
              //세전만기액
              var maturityAmountBeforeTax = 0;
              //세후이자액
              var interestAmountAfterTax = 0;
              //세후만기액
              var maturityAmountAfterTax = 0;
              //일반과세 공제율
              var generalTaxRate = 0.154;

              // 1. 예금인 경우
              if (isSavingsAccount) {
                // 단리인 경우 - 원금 * 세전이자율 * (기간 / 12)
                if (isSimpleInterest) {
                  //세전
                  interestAmountBeforeTax =
                    amount * beforeTaxRate * (depositPeriod / 12);
                  maturityAmountBeforeTax = amount + interestAmountBeforeTax;

                  //세후
                  if (isNaN(afterTaxRate)) {
                    //세후이자율이 비어있다면 - 세후이자액 = 세전이자액(1-0.154)
                    interestAmountAfterTax =
                      interestAmountBeforeTax * (1 - generalTaxRate);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } else {
                    //세후이자율이 비어있지 않다면
                    interestAmountAfterTax =
                      amount * afterTaxRate * (depositPeriod / 12);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } //if-else
                } //if

                // 복리인 경우 - 원금 * (1 + 연이자율 / 12) ** 예치개월수 - 원금
                if (isCompoundInterest) {
                  //세전
                  interestAmountBeforeTax =
                    amount * Math.pow(1 + beforeTaxRate / 12, depositPeriod) -
                    amount;
                  maturityAmountBeforeTax = amount + interestAmountBeforeTax;

                  //세후
                  if (isNaN(afterTaxRate)) {
                    //세후이자율이 비어있다면 - 세후이자액 = 세전이자액(1-0.154)
                    interestAmountAfterTax =
                      interestAmountBeforeTax * (1 - generalTaxRate);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } else {
                    //세후이자율이 비어있지 않다면
                    interestAmountAfterTax =
                      amount * afterTaxRate * (depositPeriod / 12);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } //if-else
                }
              }

              // 2. 적금인 경우
              if (isInstallmentSavingsAccount) {
                // 단리인 경우 - 월불입액 * 이자율 * (기간(기간 + 1) / 2) * (1 / 12)
                if (isSimpleInterest) {
                  //세전
                  interestAmountBeforeTax =
                    (amount / depositPeriod) *
                    beforeTaxRate *
                    ((depositPeriod * (depositPeriod + 1)) / 2) *
                    (1 / 12);
                  maturityAmountBeforeTax = amount + interestAmountBeforeTax;

                  //세후
                  if (isNaN(afterTaxRate)) {
                    //세후이자율이 비어있다면 - 세후이자액 = 세전이자액(1-0.154)
                    interestAmountAfterTax =
                      interestAmountBeforeTax * (1 - generalTaxRate);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } else {
                    //세후이자율이 비어있지 않다면
                    interestAmountAfterTax =
                      amount * afterTaxRate * (depositPeriod / 12);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } //if-else
                }

                // 복리인 경우 - 월납입금 * (1+이자율/12) * ((1+이자율/12)^기간-1) / (이자율/12) - 원금
                if (isCompoundInterest) {
                  //세전
                  interestAmountBeforeTax =
                    ((amount / depositPeriod) *
                      (1 + beforeTaxRate / 12) *
                      (Math.pow(1 + beforeTaxRate / 12, depositPeriod) - 1)) /
                      (beforeTaxRate / 12) -
                    amount;
                  maturityAmountBeforeTax = amount + interestAmountBeforeTax;

                  //세후
                  if (isNaN(afterTaxRate)) {
                    //세후이자율이 비어있다면 - 세후이자액 = 세전이자액(1-0.154)
                    interestAmountAfterTax =
                      interestAmountBeforeTax * (1 - generalTaxRate);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } else {
                    //세후이자율이 비어있지 않다면
                    interestAmountAfterTax =
                      amount * afterTaxRate * (depositPeriod / 12);
                    maturityAmountAfterTax = amount + interestAmountAfterTax;
                  } //if-else
                }
              }

              //결과를 출력
              var html = "";

              // 세전 계산 결과
              html += "<div id=calculator-result>";
              html += "<div class=calculator-result>";
              html += "<div class=result-interestAmount>";
              html += "<span>세전 이자액</span>";
              html +=
                "<div id=result-interestAmount-beforeTax>" +
                parseFloat(interestAmountBeforeTax.toFixed()).toLocaleString() +
                "<span> 원</span></div>";
              html += "</div>";

              html += "<div class=result-maturityAmount>";
              html += "<span>세전 만기액</span>";
              html +=
                "<div id=result-maturityAmount-beforeTax>" +
                parseFloat(maturityAmountBeforeTax.toFixed()).toLocaleString() +
                "<span> 원</span></div>";
              html += "</div>";
              html += "</div>";

              //세후 계산 결과
              html += "<div class=calculator-result>";
              html += "<div class=result-interestAmount>";
              html += "<span>세후 이자액</span>";
              html +=
                "<div id=result-interestAmount-afterTax>" +
                parseFloat(interestAmountAfterTax.toFixed()).toLocaleString() +
                "<span> 원</span></div>";
              html += "</div>";

              html += "<div class=result-maturityAmount>";
              html += "<span>세후 만기액</span>";
              html +=
                "<div id=result-maturityAmount-afterTax>" +
                parseFloat(maturityAmountAfterTax.toFixed()).toLocaleString() +
                "<span> 원</span></div>";
              html += "</div>";
              html += "</div>";
              html += "</div>";

              $("#calculator-result").html(html);
            }
          }); //click

          function removeResult() {
            $("#calculator-result").html("");
          }

          // 모든 입력 필드와 체크박스에 input 이벤트 리스너 추가
          $(
            "#calculator-amount, #calculator-depositPeriod, #calculator-beforeTaxRate, #calculator-afterTaxRate"
          ).on("input", removeResult);

          // 라디오 버튼과 체크박스에 change 이벤트 리스너 추가
          $(
            "#calculator-savingsAccount, #calculator-installmentSavingsAccount, #calculator-simpleInterest, #calculator-compoundInterest"
          ).on("change", removeResult);

          //계산기 금액 입력란에 천단위로 콤마 표시
          const input = document.querySelector("#calculator-amount");

          // 숫자만 입력되도록 제한하고 콤마 추가
          input.addEventListener("input", function (e) {
            let value = e.target.value;

            // 숫자 이외의 문자 제거 (정규식을 사용하여 숫자만 남김)
            value = value.replace(/[^0-9]/g, "");

            // 천 단위로 콤마를 추가한 값을 생성
            const formattedValue = new Intl.NumberFormat("ko-KR").format(value);

            // 포맷된 값으로 input 값을 업데이트
            input.value = formattedValue;
          });

          // 커서 위치 보정
          input.addEventListener("keydown", function (e) {
            const key = e.key;

            // 입력 값이 백스페이스일 경우 콤마와 관련된 커서 위치 조정 필요
            if (key === "Backspace" || key === "Delete") {
              let value = e.target.value.replace(/,/g, "");
              input.value = value;
            }
          });

          // 모든 bankNameCol 중에서 ( 가 포함된 항목을 찾음
          $(".bankNameCol").each(function () {
            var content = $(this).html();
            // (가 포함된 경우 <br/>을 추가
            if (content.includes("(")) {
              var newContent = content.replace("(", "<br/>(");
              $(this).html(newContent); // 치환된 내용으로 업데이트
            } //if
          }); //each

          // 모든 productNameCol 중에서 ( 가 포함된 항목을 찾음
          $(".productNameCol").each(function () {
            var content = $(this).html();
            // (가 포함된 경우 <br/>을 추가
            if (content.includes("(")) {
              var newContent = content.replace("(", "<br/>(");
              $(this).html(newContent); // 치환된 내용으로 업데이트
            } //if
          }); //each

          // 모든 registerType 중에서 , 가 포함된 항목을 찾음
          $(".registerTypeCol").each(function () {
            var content = $(this).html();
            // (가 포함된 경우 <br/>을 추가
            if (content.includes(",")) {
              var newContent = content.replaceAll(",", ",<br/>");
              $(this).html(newContent); // 치환된 내용으로 업데이트
            } //if
          }); //each

          $(".registerTypeCol").each(function () {
            var content = $(this).html();
            var maxParts = 3; // 최대 허용된 콤마 구분 부분 개수

            // 콤마 기준으로 문자열을 분할
            var parts = content.split(",");

            if (parts.length > maxParts) {
              var truncatedContent =
                parts.slice(0, maxParts).join(",") + ", <br/>기타";
              $(this).html(truncatedContent); // 잘라낸 내용으로 업데이트
            }
          }); //each
        } //applyJQueryFunctions

        // 초기 페이지 로드 시 jQuery 기능 적용
        applyJQueryFunctions();
      }); //ready
    </script>
  </head>
  <body>
    <jsp:include page="header.html"></jsp:include>

    <div id="page">
      <div class="container">
        <!-- 지남님은 여기서부터 알아서 작성! -->

        <p>예적금 상품 검색</p>

        <!-- 옵션 -->
        <div id="option-box">
          <div id="option-list">
            <!-- 4. 예/적금 -->
            <div class="option" id="depositType">
              <span>예금 / 적금</span>

              <div class="radio_area">
                <input
                  type="radio"
                  name="depositType"
                  id="전체예적금"
                  checked
                />
                <label for="전체예적금">전체</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="depositType" id="예금" />
                <label for="예금">예금</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="depositType" id="적금" />
                <label for="적금">적금</label>
              </div>
            </div>

            <!-- 1. 금융권역 -->
            <div class="option" id="bankSector">
              <span>금융권역</span>

              <div class="radio_area">
                <input
                  type="radio"
                  name="bankSector"
                  id="전체권역"
                  checked
                  value="allBank"
                />
                <label for="전체권역">전체</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="bankSector" id="제1금융" />
                <label for="제1금융">1금융권</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="bankSector" id="제2금융" />
                <label for="제2금융">2금융권</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="bankSector" id="저축은행" />
                <label for="저축은행">저축은행</label>
              </div>
            </div>

            <!-- 2. 이자 계산 방식 -->
            <div class="option" id="calMethod">
              <span>이자 계산 방식</span>

              <div class="radio_area">
                <input type="radio" name="calMethod" id="전체방식" checked />
                <label for="전체방식">전체</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="calMethod" id="단리" />
                <label for="단리">단리</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="calMethod" id="복리" />
                <label for="복리">복리</label>
              </div>
            </div>

            <!-- 3. 가입 기간 -->
            <div class="option" id="registerPeriod">
              <span>가입 기간</span>

              <div class="radio_area">
                <input
                  type="radio"
                  name="registerPeriod"
                  id="전체기간"
                  checked
                />
                <label for="전체기간">전체</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="registerPeriod" id="6" />
                <label for="6">6개월</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="registerPeriod" id="12" />
                <label for="12">12개월</label>
              </div>

              <div class="radio_area">
                <input type="radio" name="registerPeriod" id="24" />
                <label for="24">24개월</label>
              </div>
            </div>
          </div>

          <button id="searchBtn">검색</button>
        </div>

        <table>
          <thead>
            <tr>
              <th id="bankTypeCol">금융권역</th>
              <th id="bankNameCol">금융사</th>
              <th id="productNameCol">상품명</th>
              <th id="beforeTaxRateCol">세전<br />이자율</th>
              <th id="afterTaxRateCol">세후<br />이자율</th>
              <th id="primeCondCol">최고 우대금리</th>
              <th id="calMethodCol">이자계산<br />방식</th>
              <th id="depositPeriodCol">가입<br />기간</th>
              <!-- <th id="targetCol">가입대상</th> -->
              <th id="registerTypeCol">가입방법</th>
            </tr>
          </thead>

          <tbody>
            <c:forEach items="${deposits}" var="deposit">
              <tr>
                <td class="bankTypeCol">${deposit.bank.bankType}</td>
                <td class="bankNameCol">${deposit.bank.bankName}</td>
                <td class="productNameCol">
                  <a href="${deposit.dUrl}">${deposit.productName}</a>
                </td>
                <td class="beforeTaxRateCol">${deposit.beforeTaxRate}</td>
                <td class="afterTaxRateCol">${deposit.afterTaxRate}</td>
                <td class="primeCondCol">
                  <c:set var="primeCond" value="${deposit.primeCond}" />
                  <c:choose>
                    <c:when test="${fn:length(primeCond)>40}">
                      ${fn:substring(primeCond, 0, 40)}<br />...
                    </c:when>
                    <c:otherwise> ${deposit.primeCond} </c:otherwise>
                  </c:choose>
                </td>
                <td class="calMethodCol">${deposit.calMethod}</td>
                <td class="depositPeriodCol">${deposit.depositPeriod}</td>
                <td class="registerTypeCol">${deposit.registerType}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <div class="number-button-wrapper"></div>
      </div>

      <div id="calculator">
        <p>예적금 계산기</p>
        <span>* 는 필수 입력</span>

        <div class="calculator-option wrap">
          <span>예금 / 적금<span> *</span></span>

          <div class="radio-box">
            <div class="radio_area">
              <input
                type="radio"
                name="calculator-depositType"
                id="calculator-savingsAccount"
                checked
              />
              <label for="calculator-savingsAccount">예금</label>
            </div>

            <div class="radio_area">
              <input
                type="radio"
                name="calculator-depositType"
                id="calculator-installmentSavingsAccount"
              />
              <label for="calculator-installmentSavingsAccount">적금</label>
            </div>
          </div>
        </div>

        <div class="calculator-option">
          <label for="calculator-amount">원금 (원)<span> *</span></label>
          <input type="text" id="calculator-amount" value="0" />
        </div>

        <div class="calculator-option">
          <label for="calculator-depositPeriod"
            >기간 (개월)<span> *</span></label
          >
          <input type="number" id="calculator-depositPeriod" value="0" />
        </div>

        <div class="calculator-option">
          <label for="calculator-beforeTaxRate"
            >세전 이자율 (%)<span> *</span></label
          >
          <input type="number" id="calculator-beforeTaxRate" value="0" />
        </div>

        <div class="calculator-option">
          <label for="calculator-afterTaxRate">세후 이자율 (%)</label>
          <input type="number" id="calculator-afterTaxRate" />
        </div>

        <div class="calculator-option wrap">
          <span>단리 / 복리<span> *</span></span>

          <div class="radio-box">
            <div class="radio_area">
              <input
                type="radio"
                name="calculator-interest"
                id="calculator-simpleInterest"
                checked
              />
              <label for="calculator-simpleInterest">단리</label>
            </div>

            <div class="radio_area">
              <input
                type="radio"
                name="calculator-interest"
                id="calculator-compoundInterest"
              />
              <label for="calculator-compoundInterest">복리</label>
            </div>
          </div>
        </div>
        <ul>
          <li>복리의 경우, 월복리로 계산됩니다.</li>
          <li>
            세후 이자율이 입력되지 않을 시, 세후 이자액은 일반과세(세전 이자액의
            15.4% 공제) 기준으로 계산됩니다.
          </li>
        </ul>
        <button id="calculatorBtn">계산</button>

        <div id="calculator-result"></div>
        <!--  -->
      </div>
    </div>
  </body>
</html>
>>>>>>> dev:ItPal/src/main/webapp/depositsearch.jsp
