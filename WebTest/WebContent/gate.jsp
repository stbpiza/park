<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% HttpSession ss = request.getSession();
String pricePerHour = (String)ss.getAttribute("setPricePerHour");
String reg_price = (String)ss.getAttribute("setReg_price");
String changeBox = (String)ss.getAttribute("changeBox");
if (pricePerHour == null){
	pricePerHour = "1000";
}
if (reg_price == null){
	reg_price = "50000";
}
if (changeBox == null){
	changeBox = "100000";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>출입구</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header><div>어서오세요 사무실 주차장 입니다</div></header>
<div class="container">
<nav>
<h3>
주차장 이용안내</h3><br>
<div class="navtext">
시간당 요금<br>
<%=pricePerHour%>원<br>
정기 요금(30일)<br>
<%=reg_price %>원<br>
남은 잔돈 : <%=changeBox %><br><br>
1시간 미만도 1시간 요금 책정<br>
정기 가입은 출차시 가능
</div>
</nav>
<section>
   <div class="top"></div>
	<form class="form1" action="/WebTest/handlerSerlvet" method="post">
		<input class="text" type="text" name="car_num" placeholder="차번호" required pattern="(\d?)(\d{2})([가-힣])(\d{4})">
		<input class="button" type="submit" name="car" value="incar">
	</form>
	<Br>
	<form class="form1" action="/WebTest/handlerSerlvet" method="post">
		<input class="text" type="text" name="car_num" placeholder="차번호" required pattern="(\d?)(\d{2})([가-힣])(\d{4})">
		<input class="button" type="submit" name="car" value="outcar">
	</form>
<br><div class="top"></div>
	<form class="form1" action="/WebTest/adminSerlvet" method="post">
	<span></span>
	<input type="hidden" name="page" value="admin">
	<input type="hidden" name="back" value="first">
	<input class="button" type="submit" value="관리자모드">
	<span></span>
	</form>
</section>

</div>
<footer>
	<span class="footer1">
		<span class="footer2">
			<span>회사소개</span>
			<span class="footer3">인사채용</span>
			<span class="footer3">제휴제안</span>
			<span>이용약관</span>
			<span class="footer3">개인정보처리방침</span>
			<span>자동차보호정책</span>
			<span>고객센터</span>
			<span>PARKING Corp.</span>
		</span>
	</span>
</footer>
</body>
</html>