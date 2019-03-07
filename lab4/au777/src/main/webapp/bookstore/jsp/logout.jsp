<%@ page import="java.util.ArrayList"%>
<%@ page import="model.User"%>
<%@ page import="model.Local" %>
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
	ArrayList<Local> bookList = new ArrayList<Local>();
	if (request.getAttribute("localuser") != null) {
		bookList = (ArrayList<Local>) request.getAttribute("localuser");
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
					<li><a href="allUsersPro" ><i></i>Sign Up </a></li>
					<li><a href="LoginPro">Login</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>
		<div id="page-wrapper">
			<div class="modal-body">

				<label>Logout Successful, Now You Can Log In Again.</label>
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
	<script src="<%=path%>/bookstore/js/login.js"></script>

	<script>
		$(document).ready(function() {
			$('#dataTables').DataTable({
				responsive : true
			});
		});
	</script>

</body>

</html>

