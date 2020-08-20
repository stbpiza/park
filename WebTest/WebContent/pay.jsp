<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String car_num = (String)request.getAttribute("car_num");
String gate_id = (String)request.getAttribute("gate_id");
String reg_price = (String)request.getAttribute("reg_price");
String time_price = (String)request.getAttribute("reg_price");
String price = "0";
if (reg_price != null){
price = reg_price;
}
else{
price = time_price;
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
결제합시다<br>
<div><%=price%>원 입니다. </div>
<% if (time_price == null) {%>
<a href="#">방문증 등록</a><Br>
<% }%>
<a href="#">카드</a><Br>
<a href="#">현금</a><br>
<br> <a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>