<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>
<%@ page import="model.Local" %>
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
		ArrayList<Book> bookList = new ArrayList<Book>();
			if (request.getAttribute("books") != null) {
				bookList = (ArrayList<Book>) request.getAttribute("books");
			}
	%>
	<%
		User user = new User();
		String name="";
		if (request.getAttribute("user") != null) {
			 user = (User)request.getAttribute("user");
			name = user.getUsername();
		}
	%>
	<%
		String ix = "data:image/jpg;base64,"+
				"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABsSFBcUERsXFhceHBsgKEIrKCUlKFE6PTBCYFVlZF9VXVtqeJmBanGQc1tdhbWGkJ6jq62rZ4C8ybqmx5moq6T/2wBDARweHigjKE4rK06kbl1upKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKSkpKT/wAARCAAyADQDAREAAhEBAxEB/8QAGgABAAIDAQAAAAAAAAAAAAAABAEDAAIFBv/EAC0QAAIBAwMCBAUFAQAAAAAAAAECAwAEEQUSISIxE0FRcRQygZGhJDRCsfBy/8QAFAEBAAAAAAAAAAAAAAAAAAAAAP/EABQRAQAAAAAAAAAAAAAAAAAAAAD/2gAMAwEAAhEDEQA/APSCgpmmS3jMkjhEHck8Cg5xvr64kYWdouzjDzHAI9cUEm41iLqe2tpVHlGxB/NAi01GO4fwyjxygdSOMEUDQq+goJ2j0FBIoOQ6i91RkkUNDaAHB/k59fpQPM0CShWdRIeOTQRc3HgYAXccE/agE8Uep2QubcbJvmRs8g+lAvT7g3Nursu2QdMg9GHegZQQKDlaT+61AEdQm5z37cUEXGmySyRHxAFjk3hvM5oL7s2pZUmmRZPLDANQX2ttHbRCOJQqDsKAunn9dqIz0iRfb5Rmg6dBqvn70HLut9hefFhS0Mg2zBRnbjs1A+KeKcZjcMDyCKCPhoDJ4phQydtxXmgNe6isJ8CAeNcNwsa8/f0oLNMtmtoMSHMrnfI3qxoG0FDTJHIsbHqcEj6d6Ad/cRGy3GZkSdekquTg/wC/NBVY3VlHbSGIMqxAbgV5J9h35oFR6jbSyeGkmSexxweM4zQEElompJMZDBK6EMrADP8A1zwaBL6lbpK0XWzq20qq/wC9qBcEqzwpKhO1hkZoDNFJLehyu1I0IU55Ytj+sfmgDNbXc9hDAbcAxEAdY8lx/ZP2oNLPT7mK3eN4gGYxnPiY+UjPI5FBfDYPCYF27guWk6uknDY785570GhtbtZ4XEbYiBGI5FVeccAY7UG1zp07yTSIWd2cMpdhgAAcYx7igfaqsVukbqE2jAXOcDy5oEigmgygygygygyg/9k=";
	%>
	<%
		ArrayList<String> photoList = new ArrayList<String>();
		if (request.getAttribute("headphoto") != null) {
			photoList = (ArrayList<String>) request.getAttribute("headphoto");
		}
	%>

	<div id="wrapper">
		<!-- Navigation -->
		<nav class="navbar navbar-default navbar-static-top" role="navigation"
			style="margin-bottom: 0">

            <div class="navbar-header">
                <a class="navbar-brand" href="#">Welcome To 175 BookStore</a><img src=<%=ix%>>
				<%
					for (int j = 0; j < photoList.size(); j++) {
						String head = "data:image/jpg;base64,"+ photoList.get(j);
				%>
				<img src=<%=head%>>
				<%
					}
				%>

            </div>

		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">
				<ul class="nav" id="side-menu">
					<li><a href="allUsersPro"><i ></i>
							Login</a></li>
					<li><a href="allBooksPro" class="active"><i
							></i> Books</a></li>
					<li><a href="allCartsPro" ><i
							></i> ShopCart</a></li>
				</ul>
			</div>
			<!-- /.sidebar-collapse -->
		</div>
		<!-- /.navbar-static-side --> </nav>

		<div id="page-wrapper">
			<div class="row">
				<div>
					<h1 class="page-header" align="center">Book Shop</h1>
				</div>
			</div>
			<!-- /.row -->
			<div class="row">
				<div class="col-lg-12">
					<div class="panel panel-default">
						<div>
							<table class="table table-striped table-bordered table-hover">
								<thead>
								<tr>
									<td>name</td>
									<td>Email</td>
									<td>Phone</td>
								</tr>
								</thead>
								<tbody>
								<tr>
									<td><%=name%></td>
									<td><%=user.getEmail()%></td>
									<td><%=user.getPhone()%></td>
									<td><button class="btn btn-default edit2" id ="edit2" type="button"
												data-id="<%=user.getId()%>"
												data-username="<%=user.getUsername()%>"
												data-password="<%=user.getPassword()%>"
												data-phone="<%=user.getPhone()%>"
												data-email="<%=user.getEmail()%>"
												data-role="<%=user.getRole()%>">
										<i>edit</i>
									</button></td>
								</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-heading">
							<button class="btn btn-default" type="button" id="add">
								add book
							</button>
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
											<th></th>
										</tr>
									</thead>
									<tbody>
										<%
											for (int i = 0; i < bookList.size(); i++) {
																				Book book = bookList.get(i);
										%>

										<tr>
										    <td><%=book.getId()%></td>
											<td><%=book.getTitle()%></td>
											<td><%=book.getAuthor()%></td>
											<td><%=book.getPrice()%></td>
											<td><%=book.getPublisher()%></td>
											<td><%=book.getLanguage()%></td>
											<td>
												<button class="btn btn-default delete" type="button"
													data-id="<%=book.getId()%>">
													delete
												</button>

												<button class="btn btn-default edit" type="button"
													data-id="<%=book.getId()%>"
													data-title="<%=book.getTitle()%>"
													data-author="<%=book.getAuthor()%>"
													data-price="<%=book.getPrice()%>"
													data-publisher="<%=book.getPublisher()%>"
													data-language="<%=book.getLanguage()%>">
													change
												</button>
												<button class="btn btn-default buy" type="button" name="buy"
														data-id="<%=book.getId()%>"
														data-title="<%=book.getTitle()%>"
														data-author="<%=book.getAuthor()%>"
														data-price="<%=book.getPrice()%>"
														data-publisher="<%=book.getPublisher()%>"
														data-language="<%=book.getLanguage()%>">
													buy
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
			<!-- /.row -->
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
									<label>Title</label> <input class="form-control" name="title">
								</div>
								<div class="form-group">
									<label>Author</label> <input class="form-control" name="author">
								</div>
								<div class="form-group">
									<label>Price</label> <input class="form-control" type="number"
										step="0.01" name="price">
								</div>
								<div class="form-group">
									<label>Published</label> <input class="form-control" name="publisher">
								</div>
								<div class="form-group">
									<label>Language</label> <input class="form-control" name="language">
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


	<div class="modal fade" id="mmodal" tabindex="-1" role="dialog"
		 aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="mmodal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="col-lg-12">
							<form role="form">
								<div class="form-group">
									<label>Username</label> <label class="form-control" name="username"><%=user.getUsername()%></label>
								</div>
								<div class="form-group">
									<label>Password</label> <input class="form-control" name="password">
								</div>
								<div class="form-group">
									<label>Email</label> <input class="form-control" name="email">
								</div>
								<div class="form-group">
									<label>Phone</label> <input class="form-control"
																   name="phone">
								</div>


							</form>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" id="save2">Save</button>
				</div>
			</div>
		</div>
	</div>


	<script src="<%=path%>/bookstore/js/jquery.min.js"></script>
	<script src="<%=path%>/bookstore/js/bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/jquery.dataTables.min.js"></script>
	<script src="<%=path%>/bookstore/js/dataTables.bootstrap.min.js"></script>
	<script src="<%=path%>/bookstore/js/bookstore.js"></script>
	<script src="<%=path%>/bookstore/js/bootbox.min.js"></script>


	<script src="<%=path%>/bookstore/js/book.js"></script>

	<script>
        $(document).ready(function() {
            $('#dataTables').DataTable({
                responsive : true
            });
        });
	</script>

</body>

</html>

