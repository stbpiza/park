<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String set = (String)request.getAttribute("set");
HttpSession ss = request.getSession();
String setPricePerHour = (String)ss.getAttribute("setPricePerHour");
String setReg_price = (String)ss.getAttribute("setReg_price");
%>
<title>Insert title here</title>
</head>
<body>
관리자 페이지 입니다<br>
시간당 금액 : <%=setPricePerHour %><br>
정기 금액 : <%=setReg_price %><br>
시간당 금액 수정하기(1000단위로)<br>
	<form action="/WebTest/sessionSerlvet" method="post">
		<input type="hidden" name="errorCheck" value="check">
		<input type="hidden" name="type" value="admin">
		<p><input type="text" name="setPricePerHour" placeholder="1000원이상" required pattern="(\d{1,5})([0]{3})"></p>
		<p><input type="submit" value="수정"></p>
	</form><br>
정기 금액 수정하기(10000단위로)<br>
		<form action="/WebTest/sessionSerlvet" method="post">
		<input type="hidden" name="errorCheck" value="check">
		<input type="hidden" name="type" value="admin">
		<p><input type="text" name="setReg_price" placeholder="50000원이상" required pattern="([5-9])([0]{4})|(\d{1,4})([0]{5})"></p>
		<p><input type="submit" value="수정"></p>
	</form>
<%if (set != null){ %>
설정완료!<Br>
<%} %>
<a href="/WebTest/gate.jsp">돌아가기</a>
</body>
</html>