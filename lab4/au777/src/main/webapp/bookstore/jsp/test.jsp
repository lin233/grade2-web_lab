<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<sql:query var="rs" dataSource="jdbc/sample">
  select id, name, password, phone, email from users1
</sql:query>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>DB Test</title>
</head>
<body>
	<h2>Results WEB</h2>
	<c:forEach var="row" items="${rs.rows}"> ID ${row.id}<br />
                      Name ${row.name}   <br />
                      Password${row.password}<br />
                      phone ${row.phone}<br />
                       email ${row.email}<br />
                        <br />
	</c:forEach>
</body>
</html>
