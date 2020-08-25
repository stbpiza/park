<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String regular_non = (String)request.getAttribute("regular_non");
String price = (String)request.getAttribute("price");
String admin = (String)request.getAttribute("admin");
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자결제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container3">

<%if (!admin.contentEquals("yes")) {%>
<div class="simpletext">
암호를 입력해 주세요.(숫자만)
</div>
	<form class="form1" action="/WebTest/adminSerlvet" method="post">
	<input class="text" type="password" name="password" required pattern="(\d{1,10})">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="page" value="force">
		<input class="button" type="submit" value="확인">
	</form><br>
<%if (admin.contentEquals("no")) {%>
<div class="simpletext red">
암호가 틀렸습니다!
</div>
<%} %>
	<form class="form3" action="/WebTest/adminSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="page" value="force">
	<input type="hidden" name="back" value="back">
		<input class="button" type="submit" value="돌아가기">
	</form>
<%} %>
<%if (admin.contentEquals("yes")) {%>
<div class="simpletext">
가격을 입력해주세요.
</div>
	<form class="form1" action="/WebTest/paySerlvet" method="post">
	<input class="text" type="text" name="price" required pattern="(\d{1,5})([0]{3})|([0])">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
		<input class="button" type="submit" value="결제">
	</form>
<%} %>
</div>
</body>
</html>