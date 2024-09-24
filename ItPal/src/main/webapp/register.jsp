<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="register.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<title>Insert title here</title>
<script>
$(() => {
	
	// 취소버튼 클릭 이벤트
	$("#cancel").on('click', function() { 
		window.location.href = "login.jsp";
	});
	
	$("#idCheckBtn").on('click', function() {
		var userId = $("#id").val(); //입력한 ID 값 가져오기
		
		if (userId === "") {
			alert("아이디를 입력하세요.");
			return;
		}
		
		//AJAX 요청을 통해 서버에 ID 중복 체크
		$.ajax({
			url: "idDuplicateCheck.do",
			type: "POST",
			data: {id: userId}, //서버로 보낼 데이터
			success: function(response) {
				if(response === "true") {
					//서버에서 ID가 존재한다고 응답한 경우
					$("#id-error-msg").show(); //에러 메시지 표시
					$("#id-error-msg").html("<p style='color: red;'>이미 존재하는 아이디입니다.</p>"); //에러 메시지 숨기기
				} else {
					//ID가 중복되지 않는경우
					$("#id-error-msg").show();
					$("#id-error-msg").html("<p style='color: blue;'>사용 가능한 아이디입니다!</p>"); //에러 메시지 숨기기
				}
			},
			error: function() {
				alert("오류가 발생했습니다. 다시 시도해주세요.");
			}
		}); // AJAX
	});
	
	//비밀번호 값 검증...
	$("#confirmPwd").on('input', function() {
        // 첫 번째와 두 번째 비밀번호 값 가져오기
        var password = $("#pwd").val();
        var confirmPassword = $("#confirmPwd").val();

        // 두 값이 일치하지 않으면 에러 메시지 표시
        if (password !== confirmPassword) {
            $("#pwd-error-msg").show();
            $("#pwd-error-msg").html("<p style='color: red;'>패스워드가 서로 일치하지 않습니다. <br>다시 한번 확인해주시기 바랍니다.</p>"); 
        } else {
        	$("#pwd-error-msg").show();
            $("#pwd-error-msg").html("<p style='color: blue;'>패스워드가 서로 일치합니다!</p>");  // 값이 일치하면 에러 메시지 숨기기
        }
    });
	
}); //ready();
</script>
</head>
<body>
<jsp:include page="header.html"></jsp:include>
<div id="one">더 좋은 서비스를 제공받기 위해 계정을 등록하세요.</div>

<div id="frmContainer">
    <h2 id="title">계정 등록</h2>

    <form action="register.do">
        <div class="form-group">
            <label for="id">ID</label>
            <div id="idCheck">
                <input type="text" name="id" id="id" placeholder="아이디를 입력하세요." required>
                <div id="idCheckBtn">중복확인</div>
            </div>
            <div class="error-message" id="id-error-msg"></div>
        </div>
    
        <div class="form-group">
            <label for="pwd">PASSWORD</label>
            <input type="password" name="pwd" id="pwd" placeholder="패스워드를 입력하세요" required>
            <input type="password" id="confirmPwd" placeholder="패스워드를 한 번 더 입력하세요" required>
            <div class="error-message" id="pwd-error-msg"></div>
        </div>
    
        <div class="form-group">
            <label for="username">USER NAME</label>
            <input type="text" name="username" id="username" placeholder="James" required>
        </div>
    
        <div class="form-group">
            <label for="phone">PHONE NUMBER</label>
            <input type="text" name="phone" id="phone" placeholder="핸드폰 번호를 입력하세요. ex)010-1234-5678" required>
        </div>
    
        <div class="form-group">
            <label for="email">EMAIL</label>
            <input type="text" name="email" id="email" placeholder="itpal@naver.com" required>
        </div>
    
        <div class="form-group">
            <label for="birthdate">BRITH DAY</label>
            <input type="date" name="birthdate" id="birthdate" required>
        </div>
    
        <div class="buttons">
            <div class="cancel" id="cancel">취소</div>
            <input type="submit" class="submit" id="registerBtn" value="회원가입"></input>
        </div> 
    </form>
</div>
</body>
</html>