<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
request.setCharacterEncoding("utf-8");
String set = (String)request.getAttribute("set");
HttpSession ss = request.getSession();
String setPricePerHour = (String)ss.getAttribute("setPricePerHour");
String setReg_price = (String)ss.getAttribute("setReg_price");
String changeBox = (String)ss.getAttribute("changeBox");
%>
<title>관리자 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<header>
<div class="simpletext center">
관리자 페이지 입니다<br>
시간당 금액 : <%=setPricePerHour %><br>
정기 금액 : <%=setReg_price %>
남은 잔돈 : <%=changeBox %>
</div>
</header>
<div class="container3">

<div class="simpletext">시간당 금액 수정하기(1000단위로)</div>
	<form class="form1" action="/WebTest/sessionSerlvet" method="post">
		<input type="hidden" name="errorCheck" value="check">
		<input type="hidden" name="type" value="admin">
		<input class="text" type="text" name="setPricePerHour" placeholder="1000원이상" required pattern="([1-9])(\d{0,2})([0]{3})">
		<input class="button" type="submit" value="수정">
	</form><br>
<div class="simpletext">정기 금액 수정하기(10000단위로)</div>
		<form class="form1" action="/WebTest/sessionSerlvet" method="post">
		<input type="hidden" name="errorCheck" value="check">
		<input type="hidden" name="type" value="admin">
		<input class="text" type="text" name="setReg_price" placeholder="10000원이상" required pattern="([1-9])(\d{0,2})([0]{4})">
		<input class="button" type="submit" value="수정">
	</form><br>
<div class="simpletext">잔돈 채우기(최대 30만원)</div>
		<form class="form3" action="/WebTest/sessionSerlvet" method="post">
		<input type="hidden" name="errorCheck" value="check">
		<input type="hidden" name="type" value="change">
		<input class="button" type="submit" value="10만원 충전">
	</form>
<%if (set != null){ %>
<div class="simpletext red">설정완료!</div>
<%} %>
<div class="simpletext">
<a href="/WebTest/gate.jsp">돌아가기</a></div>
</div>
</body>
</html>