<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String error = (String)request.getAttribute("error");
if (error == null){
	error = "first";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호입력</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body>
<div class="container3">
<div class="simpletext">
암호를 입력해 주세요(숫자만)</div>
	<form class="form1" action="/WebTest/adminSerlvet" method="post">
		<input class="text" type="password" name="password" required pattern="(\d{1,10})">
		<input type="hidden" name="page" value="check">
		<input class="button" type="submit" value="확인">
	</form>
<%if(error.contentEquals("1")){ %>
<div class="simpletext red">암호가 틀렸습니다!</div>>
<%} %>
<div class="simpletext">
<a href="/WebTest/gate.jsp">돌아가기</a>
</div>
</div>
</body>
</html>