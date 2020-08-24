<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header>어서오세요 여기는 주차장 입니다</header>
<section>
	<form action="/WebTest/handlerSerlvet" method="post">
		<p><input type="text" name="car_num" placeholder="차번호" required pattern="(\d?)(\d{2})([가-힣])(\d{4})"></p>
		<p><input type="submit" name="car" value="incar"></p>
	</form>
	<form action="/WebTest/handlerSerlvet" method="post">
		<p><input type="text" name="car_num" placeholder="차번호" required pattern="(\d?)(\d{2})([가-힣])(\d{4})"></p>
		<p><input type="submit" name="car" value="outcar"></p>
	</form>
</section>

<br>
	<form action="/WebTest/adminSerlvet" method="post">
	<input type="hidden" name="page" value="admin">
	<input type="hidden" name="back" value="first">
		<p><input type="submit" value="관리자모드"></p>
	</form>
</body>
</html>