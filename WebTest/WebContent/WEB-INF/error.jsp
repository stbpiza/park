<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");
String in_out = (String)request.getAttribute("in_out");
String text="";
if (in_out.contentEquals("1")){
  text="이미 입차된 차량입니다!";
}
else if (in_out.contentEquals("0")){
	text="입차되지 않은 차량입니다!";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" http-equiv="refresh" content="5; url=/WebTest/gate.jsp" />
<title>Insert title here</title>
</head>
<body>
<%=text%><br>
<a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>