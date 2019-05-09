<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>
<%@ page import="model.Cart" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="net.sf.json.JSONArray" %>
<%@ page import="model.User" %>
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
<link href="<%=path%>/bookstore/css/dataTables.bootstrap.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/dataTables.responsive.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/bookstore.css" rel="stylesheet">
<link href="<%=path%>/bookstore/css/font-awesome.min.css" rel="stylesheet" type="text/css">
</head>

<body>
	<%
		ArrayList<Cart> bookList = new ArrayList<Cart>();
			if (session.getAttribute("carts") != null) {
				bookList = (ArrayList<Cart>) session.getAttribute("carts");
			}
		JSONObject wh = new JSONObject();
		if (session.getAttribute("Warehouse") != null) {
			wh = (JSONObject) session.getAttribute("Warehouse");
		}
	%>
	<%
		User user = new User();
		String name="";
		int userid = 1;
		if (session.getAttribute("user") != null) {
			user = (User)session.getAttribute("user");
			userid = user.getId();
		}
	%>
	<%
		String ok = "1";
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
					<li><a href="allUsersPro">
							Login</a></li>
					<li><a href="allBooksPro">Books</a></li>

					<li><a href="allCartsPro" class="active">ShopCart</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div>
					<h1 class="page-header" align="center">Shop cart</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<!-- /.panel-heading -->
						<div class="panel-body">
							<div class="dataTable_wrapper">
								<table class="table table-striped table-bordered table-hover"
									id="dataTables">
									<thead>
										<tr>
										    <th>ID</th>
											<th>Title</th>
											<th>Author</th>
											<th>Price</th>
											<th>Published</th>
											<th>Language</th>
											<th>Amount</th>
											<th>Total Price</th>
											<th><%=ok%></th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < bookList.size(); i++) {
																				Cart book = bookList.get(i);
										%>
										<tr>
										    <td><%=book.getId()%></td>
											<td><%=book.getTitle()%></td>
											<td><%=book.getAuthor()%></td>
											<td><%=book.getPrice()%></td>
											<td><%=book.getPublisher()%></td>
											<td><%=book.getLanguage()%></td>
											<td><%=book.getAmount()%></td>
											<td><%=book.getAmount()*book.getPrice()%></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<%=book.getId()%>">
													remove
												</button>
												<button class="btn btn-default edit" type="button"
													data-id="<%=book.getId()%>"
													data-title="<%=book.getTitle()%>"
													data-author="<%=book.getAuthor()%>"
													data-price="<%=book.getPrice()%>"
													data-publisher="<%=book.getPublisher()%>"
													data-language="<%=book.getLanguage()%>"
													data-amount="<%=book.getAmount()%>">
													change amount
												</button>
												<button class="btn btn-default addwh" type="button" name="addwh"
														data-id="<%=book.getId()%>"
														data-title="<%=book.getTitle()%>"
														data-author="<%=book.getAuthor()%>"
														data-price="<%=book.getPrice()%>"
														data-publisher="<%=book.getPublisher()%>"
														data-language="<%=book.getLanguage()%>"
														data-amount="<%=book.getAmount()%>">
														>
													buy
												</button>
												<button class="btn btn-default getwh" type="button" name="getwh"
														data-id="<%=book.getId()%>"
														data-title="<%=book.getTitle()%>"
														data-price="<%=book.getPrice()%>"
												>
													get
												</button>
											</td>
										</tr>
										<%
											}
										%>
									</tbody>
								</table>
							</div>
						</div>
						<!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<!-- /.panel-heading -->
						<table class="table table-striped table-bordered table-hover"
							   id="datas2">
							<thead>Orders
							<button class="btn btn-default" type="button" id="getorder"
									data-userid="<%=1%>">
								Get My order
							</button>
							<tr>
								<th>Bookid</th>
								<th>Title</th>
								<th>Author</th>
								<th>Price</th>
								<th>Amount</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td> <input disabled name="Bookid1"/></td>
								<td> <input disabled name="Title1"/></td>
								<td> <input disabled name="Author1"/></td>
								<td> <input disabled name="Price1"/></td>
								<td> <input disabled name="Amount1"/></td>
								<td> <button class="btn btn-default" type="button" id="delete1"
											 data-userid="<%=userid %>"
								>
									delete
								</button></td>
							</tr>
							<tr>
								<td> <input disabled name="Bookid2"/></td>
								<td> <input disabled name="Title2"/></td>
								<td> <input disabled name="Author2"/></td>
								<td> <input disabled name="Price2"/></td>
								<td> <input disabled name="Amount2"/></td>
								<td> <button class="btn btn-default" type="button" id="delete2"
											 data-userid="<%=userid %>"
								>
									delete
								</button></td>
							</tr>
							</tbody>
						</table>
						<!-- /.panel-body -->
						<input disabled name="text"/>
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>

			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div class="panel-heading">
						</div>
						<!-- /.panel-heading -->
						<table class="table table-striped table-bordered table-hover"
							   id="datas">
							<thead>
							<tr>
								<th>Description</th>
								<th>Price</th>
								<th>Location</th>
								<th></th>
							</tr>
							</thead>
							<tbody>
								<tr>
									<%
										if(session.getAttribute("Warehouse") != null){
									%>
									<td><%=wh.get("Description")%></td>
									<td><%=wh.get("Price")%></td>
									<td><%=wh.get("Location")%></td>
									<%
										}
									%>
								</tr>
							</tbody>
						</table>
						<!-- /.panel-body -->
						<input name="text"/>
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>

		</div>
		<!-- /#page-wrapper -->
	</div>
	<!-- /#wrapper -->


	<div class="modal fade" id="modal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="modalTitle"></h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Amount</label> <input class="form-control" name="amount">
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" id="save">save</button>
				</div>
			</div>
		</div>
	</div>

	<%--<script src="<%=path%>/bookstore/js/jquery.min.js"></script>--%>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>

	<script src="<%=path%>/bookstore/js/cart.js"></script>

	<script>
        $(document).ready(function() {
            $('#dataTables').DataTable({
                responsive : true
            });
        });
	</script>

</body>

</html>

