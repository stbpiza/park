<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String idCheck = (String)request.getAttribute("idCheck");
if (idCheck==null){
	idCheck="first";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
주차장 시스템을 시작합니다.<br>
로그인이 필요합니다.<br>
(id:admin pw:0000)<br>
	<form action="/WebTest/sessionSerlvet" method="post">
		<p><input type="text" name="id" placeholder="id" required pattern="([a-zA-Z0-9]+)"></p>
		<p><input type="password" name="password" placeholder="password" required pattern="(\d+)"></p>
		<input type="hidden" name="errorCheck" value="check">
		<p><input type="submit" value="전송"></p>
	</form>
<%if (idCheck.contentEquals("no")){ %>
잘못 입력하셨습니다.
<%} %>
</body>
</html>