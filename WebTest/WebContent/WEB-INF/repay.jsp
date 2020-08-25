<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>다시결제</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container3">
<div class="simpletext">
계산을 안하셨군요?<br>
계산하러 갑시다.
</div>
	<form class="form3" action="/WebTest/regularSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
		<input class="button" type="submit" value="확인">
	</form>
</div>
</body>
</html>