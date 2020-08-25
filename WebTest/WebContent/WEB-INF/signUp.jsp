<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8");
		
	String car_num = (String)request.getAttribute("car_num");
	String gate_id = (String)request.getAttribute("gate_id");
 
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정기가입권유</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container3">
<div class="simpletext">
정기 회원이 아니시군요?<br>
가입 하시겠습니까?
</div>
	<form class="form3" action="/WebTest/handlerSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="payed" value="no">
		<input class="button" type="submit" name="reg" value="yes">
	</form><Br>
		<form class="form3" action="/WebTest/handlerSerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="reg" value="no">
	   <input class="button" type="submit" name="payed" value="no">
	</form>
<div class="simpletext">
<a href="/WebTest/gate.jsp">돌아가기</a>
</div>
</div>
</body>
</html>