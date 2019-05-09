$(function() {

	$ ("#save").click(function(e) {
		var amount = $("input[name='amount']").val();
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		jQuery.ajax({
			url : 'updateCartPro',
			processData : true,
			dataType : "text",
			data : {
				id:id ,
				amount : amount
			},
			success : function(data) {
				bootbox.alert({
					message : 'Add Successfully! ',
					callback : function() {
						location.reload();
					}
				});
			},

		})

		$('#modal').modal('hide');
	});

	$("#getorder").click(function(e) {
		var dataset = e.currentTarget.dataset;
		var userid = 1;
		$.ajax({
			type:"GET",
			url : 'http://localhost:8090/order',
			processData : true,
			dataType : "jsonp",
			jsonp: "jsonpCallback",
			jsonpCallback: 'handleResponse', //设置回调函数名
			data : {
				userid:userid
			},
			success : function(data) {
				var result = JSON.stringify(data); //json对象转成字符串
				alert(data);
				console.log(data);
				$("input[name='Bookid1']").val(data[0].id);
				$("input[name='Author1']").val(data[0].author);
				$("input[name='Title1']").val(data[0].title);
				$("input[name='Price1']").val(data[0].price);
				$("input[name='Amount1']").val(data[0].amount);
				if(data.length==2){
					$("input[name='Bookid2']").val(data[1].id);
					$("input[name='Author2']").val(data[1].author);
					$("input[name='Title2']").val(data[1].title);
					$("input[name='Price2']").val(data[1].price);
					$("input[name='Amount2']").val(data[1].amount);
				}
				else{
					$("input[name='Bookid2']").val("");
					$("input[name='Author2']").val("");
					$("input[name='Title2']").val("");
					$("input[name='Price2']").val("");
					$("input[name='Amount2']").val("");
				}
			},
		})


	});

	$("#delete1").click(function(e) {
		var dataset = e.currentTarget.dataset;
		var userid = 1;
		var bookid=6;
		$.ajax({
			url : 'http://localhost:8090/order',
			type:"DELETE",
			processData : true,
			dataType : "jsonp",
			jsonp: "jsonpCallback",
			jsonpCallback: 'handleResponse', //设置回调函数名
			data : {
				userid:userid,
				bookid:bookid,
			},
			success : function(data) {
				var result = JSON.stringify(data); //json对象转成字符串
				alert(data);
				console.log(data);
			},
		})
	});


	$(".delete").click(function(e) {
		bootbox.confirm({
			buttons : {
				confirm : {
					label : 'Delete'
				},
				cancel : {
					label : 'Cancel'
				}
			},
			message : 'Remove out of shopcart?',
			callback : function(result) {
				if (result) {

					var dataset = e.currentTarget.dataset;
					var id = dataset.id;
					jQuery.ajax({
						url : 'deleteCartPro',
						processData : true,
						dataType : "text",
						data : {
							id : id
						},
						success : function(data) {
							console.log(id);
							bootbox.alert({
								message : 'Delete Successfully! ',
								callback : function() {
									location.reload();
								}
							});
						}
					});

				}
			}
		});
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);
		$("input[name='amount']").val(dataset.amount);
		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

	$(".addwh").click(function(e) {
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);
		var title = dataset.title;
		var price = dataset.price;
		var author = dataset.author;
		var amount = dataset.amount;
		// jQuery.ajax({
		//     url : 'addWhPro',
		//     processData : true,
		//     dataType : "text",
		//     data : {
		//         title : title,
		//         id : id,
		//         price : price,
		//     },
		//     success : function(data) {
		//         bootbox.alert({
		//             message : 'Buy Successfully! ',
		//             callback : function() {
		//                 location.reload();
		//             }
		//         });
		//     },
		// })
		var name1 = "author";
		name1+="1";
		$.ajax({
			type:"PUT",
			url : 'http://localhost:8090/order',
			processData : true,
			dataType : "jsonp",
			jsonp: "jsonpCallback",
			jsonpCallback: 'handleResponse', //设置回调函数名
			data : {
				userid:1,
				bookid:id,
				title:title,
				price:price,
				author:author,
				amount:amount,
			},
			success : function(data) {
				var result = JSON.stringify(data); //json对象转成字符串
				alert(data);
				console.log(data);
			},
		})


	});

	$(".getwh").click(function(e) {
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);
		var title = dataset.title;
		var price = dataset.price;
		// Add
		jQuery.ajax({
			url : 'getWhPro',
			processData : true,
			dataType : "text",
			data : {
				title : title,
				id : id,
				price : price,
			},
			success : function(data) {
				bootbox.alert({
					message : 'Buy Successfully! ',
					callback : function() {
						location.reload();
					}
				});
			},

		})


	});

});
