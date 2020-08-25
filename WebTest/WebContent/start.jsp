<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
HttpSession ss = request.getSession();
String changeBox = (String)ss.getAttribute("changeBox");
String idCheck = (String)request.getAttribute("idCheck");
if (idCheck==null){
	idCheck="first";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부팅중</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container2">
<%if (changeBox ==null) {%>
<div class="simpletext red fontsizebig">
주차장 시스템을 시작합니다.<br>
로그인이 필요합니다.<br>
(id:admin pw:0000)<br>
</div>
	<form class="form2" action="/WebTest/sessionSerlvet" method="post">
		<input class="text" type="text" name="id" placeholder="id" required pattern="([a-zA-Z0-9]{1,10})">
		<input class="text" type="password" name="password" placeholder="password" required pattern="(\d{1,10})">
		<input type="hidden" name="errorCheck" value="check">
		<input class="button" type="submit" value="전송">
	</form>
<%if (idCheck.contentEquals("no")){ %>
<div class="simpletext red">잘못 입력하셨습니다.</div>
<%} %>
<%} %>
<%if (changeBox !=null) {%>
<div class="simpletext">
이미 로그인 하셨군요? 여기로 들어가세요
</div>
<div class="simpletext">
<a href="/WebTest/gate.jsp">들어가기</a></div>
</div>
<% }%>
</body>
</html>