<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
101 <br>
shashi <br>
99 <br>

<br>
<% 

 

String url="jdbc:postgresql://localhost:5432/javapoint";
String username="postgres";
String password="postgres";
String sql="Select * from sss where sss_id=8";
Class.forName("org.postgresql.Driver");
Connection con=DriverManager.getConnection(url,username,password);
Statement st=con.createStatement();

ResultSet rs=st.executeQuery(sql);
rs.next();

%>
sss_id : <%= rs.getString(1) %> <br>
sss_name : <%= rs.getString(2) %> <br>
 
</body>
</html>