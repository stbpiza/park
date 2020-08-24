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
<title>Insert title here</title>
</head>
<body>
<%if (!admin.contentEquals("yes")) {%>
암호를 입력해 주세요.(숫자만)<br>

	<form action="/WebTest/adminSerlvet" method="post">
	<p><input type="password" name="password" required pattern="(\d{1,10})"></p>
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
		<p><input type="submit" value="확인"></p>
	</form>
<%if (admin.contentEquals("no")) {%>
암호가 틀렸습니다!
<%} %>
	<form action="/WebTest/adminSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="back" value="back">
		<p><input type="submit" value="돌아가기"></p>
	</form>

<%} %>
<%if (admin.contentEquals("yes")) {%>
가격을 입력해주세요.<br>
	<form action="/WebTest/paySerlvet" method="post">
	<p><input type="text" name="price" required pattern="(\d{1,5})([0]{3})|([0])"></p>
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
		<p><input type="submit" value="결제"></p>
	</form>
<%} %>
</body>
</html>