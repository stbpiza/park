<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String regular_non = (String)request.getAttribute("regular_non");
String price = (String)request.getAttribute("price");
String cash = (String)request.getAttribute("cash");
if (cash == null){
	 cash = "0";
}
int cashInt = Integer.parseInt(cash);
int priceInt = Integer.parseInt(price);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=price %>원 입니다.<br>
현금을 넣어주세요.<br>
현재 투입된 금액 : <%=cash%><br>
<%if(priceInt > cashInt) {%>
	<form action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<p><input type="submit" name="addCash" value="50000"></p>
	</form>
		<form action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<p><input type="submit" name="addCash" value="10000"></p>
	</form>
		<form action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<p><input type="submit" name="addCash" value="5000"></p>
	</form>
		<form action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<p><input type="submit" name="addCash" value="1000"></p>
	</form>
	<%} %>
	<br>
	<form action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
	<input type="hidden" name="paid" value="yes">
		<p><input type="submit" value="결제"></p>
	</form>
</body>
</html>