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
<title>Insert title here</title>
</head>
<body>
암호를 입력해 주세요(숫자만)<br>
	<form action="/WebTest/adminSerlvet" method="post">
	<p><input type="password" name="password" required pattern="(\d{1,10})"></p>
		<input type="hidden" name="page" value="check">
		<p><input type="submit" value="확인"></p>
	</form>
<%if(error.contentEquals("1")){ %>
암호가 틀렸습니다!<br>
<%} %>
<a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>