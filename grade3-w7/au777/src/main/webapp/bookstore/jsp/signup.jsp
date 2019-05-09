<%@ page import="java.util.ArrayList"%>
<%@ page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
	<title>BookStore</title>

	<%
		String path = request.getContextPath();
	%>
	<link href="<%=path%>/bookstore/css/bootstrap.min.css" rel="stylesheet">
	<link href="<%=path%>/bookstore/css/dataTables.bootstrap.css"
		  rel="stylesheet">
	<link href="<%=path%>/bookstore/css/dataTables.responsive.css"
		  rel="stylesheet">
	<link href="<%=path%>/bookstore/css/bookstore.css" rel="stylesheet">
	<link href="<%=path%>/bookstore/css/font-awesome.min.css"
		  rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=path%>/bookstore/css/signup.css">
</head>

<body>
<%
	ArrayList<User> userList = new ArrayList<User>();
	if (request.getAttribute("users") != null) {
		userList = (ArrayList<User>) request.getAttribute("users");
	}
%>

<div id="wrapper">
	<!-- Navigation -->
	<nav class="navbar navbar-default navbar-static-top" role="navigation"
		 style="margin-bottom: 0">

		<div class="navbar-header">
			<a class="navbar-brand" href="#">Welcome To 175 BookStore</a>
		</div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
                    <li><a href="LoginPro">Login</a></li>
					<li><a href="allUsersPro" class="active">Signup </a></li>
					<li><a href="LogoutPro"><i ></i>LogOut</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

	<div id="page-wrapper">
		<div class="modal-body">
			<table>
				<tr>
					<td align="right">Name:</td>
					<td><input type="text" name="username" id="username" size=20 /></td>
				</tr>
				<tr>
					<td align="right">Password:</td>
					<td><input type="password" name="password" id="password" size=20 /></td>
				</tr>
				<tr>
					<td align="right">Repeat Password:</td>
					<td><input type="password" name="password2" id="password2" size=20 /></td>
				</tr>
				<tr>
					<td align="right">Phone:</td>
					<td><input type="text" name="phone" id="phone" size=20 /></td>
				</tr>
				<tr>
					<td align="right">Email:</td>
					<td><input type="email" name="email" id="email"	size=20 /></td>
				</tr>
				<tr>
					<td><button type="button" class="btn btn-primary" id="save">Sign Up</button></td>
					<td></td>
				</tr>
			</table>
		</div>
		<!-- /.row -->
	</div>
	<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->



<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
<script src="<%=path%>/bookstore/js/bookstore.js"></script>
<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

<script src="<%=path%>/bookstore/js/signup.js"></script>

<script>
    $(document).ready(function() {
        $('#dataTables').DataTable({
            responsive : true
        });
    });
</script>

</body>

</html>

