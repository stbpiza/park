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
<meta charset="UTF-8" http-equiv="refresh" content="5; url=/WebTest/gate.jsp" />
<title>Insert title here</title>
</head>
<body>
문이 열렸습니다.<br>
안녕히가세요.<br>
<%=text %><br>
<a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>