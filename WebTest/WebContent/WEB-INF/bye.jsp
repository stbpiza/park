<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
request.setCharacterEncoding("utf-8");
String remainingDays = (String)request.getAttribute("remainingDays");
String text="";
if (remainingDays!=null){
  text="정기 기간이 " + remainingDays + "일 남았습니다.";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="3; url=/WebTest/gate.jsp" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<title>출차완료</title>
</head>
<body>
<div class="container3">
<div class="simpletext fontsizebig">
문이 열렸습니다.<br>
안녕히가세요.<br>
<%=text %>
</div>
<!-- <div class="simpletext">
<a href="/WebTest/gate.jsp">돌아가기</a>
</div> -->
</div>
</body>
</html>