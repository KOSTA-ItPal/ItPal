<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form action="login.do">
		<button id="loginButton" type="button">마이페이지로 가기</button>
	</form>

	<script type="text/javascript">
	
	function onLoginSuccess() {
	    const imageUrls = [
	    	'img/bank_logo/IBK.png',
	        'img/bank_logo/iM.png',
	        'img/bank_logo/KB.png',
	        'img/bank_logo/KBank.png',
	        'img/bank_logo/NH농협.png'];
	    let loadedImages = 0;

	    imageUrls.forEach((url, index) => {
	        fetch(url)
	            .then(response => {
	                if (!response.ok) throw new Error('Network response was not ok');
	                return response.blob();
	            })
	            .then(blob => {
	                const reader = new FileReader();
	                reader.onloadend = function() {
	                    const base64String = reader.result;
	                    localStorage.setItem(`savedImage${index + 1}`, base64String);
	                    loadedImages++;

	                    // 모든 이미지가 로드되면 login.do로 리디렉션
	                    if (loadedImages === imageUrls.length) {
	                        window.location.href = 'login.do';
	                    }
	                };
	                reader.readAsDataURL(blob);
	            })
	            .catch(error => console.error('이미지 가져오기 실패:', error));
	    });
	}

	// 로그인 버튼 클릭 이벤트
	document.getElementById('loginButton').addEventListener('click', function() {
	    onLoginSuccess();
	});
	</script>
</body>
</html>