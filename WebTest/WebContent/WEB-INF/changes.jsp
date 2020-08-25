<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
HttpSession ss = request.getSession();
String changeBox = (String)ss.getAttribute("changeBox");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String regular_non = (String)request.getAttribute("regular_non");
String price = (String)request.getAttribute("price");
String changes = (String)request.getAttribute("changes");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>잔돈받기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container3">
<div class="simpletext">
<%=changes%>원 반환되었습니다.
</div>
<div class="simpletext">
남은 잔돈 : <%=changeBox%>
</div>
	<form class="form3" action="/WebTest/paySerlvet" method="post">
	<input type="hidden" name="car_num" value=<%=car_num%>>
	<input type="hidden" name="gate_id" value=<%=gate_id%>>
	<input type="hidden" name="regular_non" value=<%=regular_non%>>
	<input type="hidden" name="price" value=<%=price%>>
	<input type="hidden" name="kind" value="">
		<input class="button" type="submit" value="확인">
	</form>

</div>
</body>
</html>