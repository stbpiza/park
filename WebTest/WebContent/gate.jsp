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
	<form action="/WebTest/handlerSerlvet" method="get">
		<p><input type="text" name="car_num" placeholder="차번호" required pattern="(\d{2})([^A-Za-z0-9])(\d{4})"></p>
		<p><input type="submit" name="car" value="incar"></p>
	</form>
	<form action="/WebTest/handlerSerlvet" method="get">
		<p><input type="text" name="car_num" placeholder="차번호" required pattern="(\d{2})([^A-Za-z0-9])(\d{4})"></p>
		<p><input type="submit" name="car" value="outcar"></p>
	</form>
</section>
	<a href="/WebTest/pay.jsp">결제창</a>
<br> <a href="/WebTest/admin.jsp">관리자</a>
</body>
</html>