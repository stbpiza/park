<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String regular_non = (String)request.getAttribute("regular_non");
String price = (String)request.getAttribute("price");
double diceDouble = Math.random();
int diceInt = (int)(diceDouble * 10);
String dice = Integer.toString(diceInt);
String recard = (String)request.getAttribute("recard");
if (recard == null){
  recard = "0";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카드결제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container3">
<div class="simpletext">
<%=price %>원 입니다.<br>
카드를 긁어주세요.
</div>
<% if (Integer.parseInt(recard) >= 1){ %>
<div class="simpletext red">
카드결제에 실패했습니다! 다시 긁어주세요.
</div>
<% }%>
<% if (Integer.parseInt(recard) >= 3){ %>
<div class="simpletext red">
건물 관리인을 호출했습니다. 기다려주세요.
</div>
<% }%>
	<form class="form3" action="/WebTest/paySerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="dice" value=<%=dice%>>
	<input type="hidden" name="recard" value=<%=recard%>>
		<input class="button" type="submit" value="카드긁기">
	</form>
	<br>
		<form class="form3" action="/WebTest/paySerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="dice" value="1">
	<input type="hidden" name="recard" value=<%=recard%>>
		<input class="button" type="submit" value="카드잘못긁기">
	</form>
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