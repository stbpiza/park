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
<title>Insert title here</title>
</head>
<body>
정기 회원이 아니시군요?<br>
가입 하시겠습니까?<br>
	<form action="/WebTest/paySerlvet" method="get">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
		<p><input type="submit" name="reg" value="yes"></p>
	</form>
		<form action="/WebTest/handlerSerlvet" method="get">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	   <p><input type="submit" name="payed" value="no"></p>
	</form>

<a href="/WebTest/gate.jsp">돌아가기</a>

</body>
</html>