<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
HttpSession ss = request.getSession();
String changeBox = (String)ss.getAttribute("changeBox");
if (changeBox == null){
	changeBox = "100000";
}
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
int changeBoxInt = Integer.parseInt(changeBox);
int nextChangeBox = 0;
if (priceInt < cashInt){
	nextChangeBox = changeBoxInt - (cashInt - priceInt);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>현금결제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container3">
<div class="simpletext">
<%=price %>원 입니다.<br>
현금을 넣어주세요.<br>
현재 투입된 금액 : <span class="yellow"><%=cash%></span><br>
남은 잔돈 : <span class="yellow"><%=changeBox%></span><br>
</div>
<%if(priceInt > cashInt) {%>
	<form class="form3" action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<input class="button" type="submit" name="addCash" value="50000">
	</form><br>
		<form class="form3" action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<input class="button" type="submit" name="addCash" value="10000">
	</form><Br>
		<form class="form3" action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<input class="button" type="submit" name="addCash" value="5000">
	</form><br>
		<form class="form3" action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
		<input class="button" type="submit" name="addCash" value="1000">
	</form>
	<%} %>
	<%if(nextChangeBox>=0) {%>
	<br>
	<form class="form3" action="/WebTest/cashSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="cash" value=<%=cash%>>
	<input type="hidden" name="paid" value="yes">
		<input class="button" type="submit" value="결제">
	</form>
	<%} %>
	<%if(nextChangeBox<0) { %>
	<div class="simpletext red">
	잔돈이 부족합니다!<br>
	건물 관리인을 호출하였으니,<br>
	기다리시거나 카드결제 해주세요
	</div>
	<%} %>
		<br>
		<form class="form3" action="/WebTest/paySerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="back" value="back">
		<input class="button" type="submit" value="돌아가기">
	</form>
</div>
</body>
</html>