<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String reg_price = (String)request.getAttribute("reg_price");
String time_price = (String)request.getAttribute("time_price");
String regular_non = (String)request.getAttribute("regular_non");
String price = (String)request.getAttribute("price");
String error = (String)request.getAttribute("error");
if (error == null){
	error = "0";
}
if (reg_price != null){
price = reg_price;
regular_non = "1";
}
else if(time_price != null){
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
<div><%=price%>원 입니다. </div><br>
<%if (!price.contentEquals("0")) {%>
<% if (reg_price == null) {%>
방문증을 인식해 주세요.<br>
<% if (!error.contentEquals("0")) {%>
인식에 실패하였습니다. 다시 시도해 주세요.<br>
<% }%>
	<form action="/WebTest/paySerlvet" method="post">
		<input type="hidden" name="car_num" value=<%=car_num%>>
		<input type="hidden" name="gate_id" value=<%=gate_id%>>
		<input type="hidden" name="regular_non" value=<%=regular_non%>>
		<input type="hidden" name="price" value=<%=price%>>
		<input type="hidden" name="kind" value="coupon">
		<input type="hidden" name="error" value=<%=error%>>
		<p><input type="text" name="coupon" placeholder="방문증(숫자6자리)" required pattern="(\d{6})"></p>
		<p><input type="submit" value="인식"></p>
	</form>
<Br>
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
	</form><br>
	<form action="/WebTest/adminSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
		<p><input type="submit" value="관리자결제"></p>
	</form>
	
<%} %>
<%if (price.contentEquals("0")){ %>
	<form action="/WebTest/paySerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
		<p><input type="submit" value="출차하기"></p>
	</form>
<%} %>
<br> <a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>