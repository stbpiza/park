<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주차장연습중</title>
</head>
<body>
hell world <br>
<%
	Statement stm = null;
	ResultSet rs = null;
	Class.forName("com.mysql.jdbc.Driver");
	String myUrl = "jdbc:mysql://localhost:3306/park_test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	Connection conn = DriverManager.getConnection(myUrl, "root", "");
	try {
        	stm = conn.createStatement();
        	if(stm.execute("select * from gate")) {
                	rs = stm.getResultSet();
        }
        while(rs.next()) {
                out.println(rs.getString("gate_id"));
                out.println(rs.getString("car_num"));
                out.println(rs.getString("time"));
                out.println(rs.getString("in_out"));
                out.write("<br>");
        }
        rs.close();
        stm.close();
}
catch(Exception e) {
        out.println("rs.next() ERROR");
}
conn.close();
%>
</body>
</html>