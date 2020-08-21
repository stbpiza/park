<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String reg_price = (String)request.getAttribute("reg_price");
String time_price = (String)request.getAttribute("time_price");
String regular_non;
String price;
if (reg_price != null){
price = reg_price;
regular_non = "1";
}
else{
price = time_price;
regular_non = "0";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
결제합시다<br>
<div><%=price%>원 입니다. </div>
<% if (time_price != null) {%>
<a href="#">방문증 등록</a><Br>
<% }%>
	<form action="/WebTest/cardSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
		<p><input type="submit" value="card"></p>
	</form>
	<form action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
		<p><input type="submit" value="cash"></p>
	</form>
<br> <a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>