$(function() {

	$("#save").click(function(e) {
		var title = $("input[name='title']").val();
		var author = $("input[name='author']").val();
		var price = $("input[name='price']").val();
		var publisher = $("input[name='publisher']").val();
		var language = $("input[name='language']").val();
		console.log(title, author, price, publisher, language);

		var dataset = e.currentTarget.dataset;
		var id = dataset.id;

		if(title == ""){
            alert("title can't be empty!!");
            return;
		}
        if(author== ""){
            alert("author can't be empty!!");
            return;
        }
        if(price == ""){
            alert("price can't be empty!!");
            return;
        }
        if(publisher == ""){
            alert("publisher can't be empty!!");
            return;
        }
        if(language== ""){
            alert("language can't be empty!!");
            return;
        }
		if (id != "") { // Edit
			jQuery.ajax({
				url : 'updateBookPro',
				processData : true,
				dataType : "text",
				data : {
					id : id,
					title : title,
					author : author,
					price : price,
					publisher : publisher,
					language : language
				},
				success : function(data) {
					console.log(id);
					bootbox.alert({
						message : 'Modify Successfully! ',
						callback : function() {
							location.reload();
						}
					});
				}
			});
		} else { // Add
			jQuery.ajax({
				url : 'addBookPro',
				processData : true,
				dataType : "text",
				data : {
					title : title,
					author : author,
					price : price,
					publisher : publisher,
					language : language
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
		}
		$('#modal').modal('hide');
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
			message : 'Sure to delete?1111111111111',
			callback : function(result) {
				if (result) {

					var dataset = e.currentTarget.dataset;
					var id = dataset.id;
					jQuery.ajax({
						url : 'deleteBookPro',
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

	$("#add").click(function(e) {
		$('#modalTitle').html("Add");

		$("input[name='title']").val("");
		$("input[name='author']").val("");
		$("input[name='price']").val("");
		$("input[name='publisher']").val("");
		$("input[name='language']").val("");

		$("#save").attr("data-id", "");
		$('#modal').modal('show');
	});

	$(".edit").click(function(e) {
		$('#modalTitle').html("Edit");
		var dataset = e.currentTarget.dataset;
		var id = dataset.id;
		console.log(id);

		$("input[name='title']").val(dataset.title);
		$("input[name='author']").val(dataset.author);
		$("input[name='price']").val(dataset.price);
		$("input[name='publisher']").val(dataset.publisher);
		$("input[name='language']").val(dataset.language);

		$("#save").attr("data-id", dataset.id);
		$('#modal').modal('show');
	});

    $(".buy").click(function(e) {

        var dataset = e.currentTarget.dataset;

        var title = dataset.title;
        var author = dataset.author;
        var price = dataset.price;
        var publisher = dataset.publisher;
        var language = dataset.language;
        console.log(title, author, price, publisher, language);


      // Add
            jQuery.ajax({
                url : 'addCartPro',
                processData : true,
                dataType : "text",
                data : {
                    title : title,
                    author : author,
                    price : price,
                    publisher : publisher,
                    language : language,
					amount: 1
                },
                success : function(data) {
                    bootbox.alert({
                        message : 'Add to Shopcart Successfully! ',
                        callback : function() {
                            location.reload();
                        }
                    });
                },

            })


    });

    $("#edit2").click(function(e) {
        $('#modalTitle').html("Edit");
        var dataset = e.currentTarget.dataset;
        var id = dataset.id;
        console.log(id);

        $("input[name='name']").val(dataset.username);
        $("input[name='password']").val(dataset.password);
        $("input[name='email']").val(dataset.email);
        $("input[name='phone']").val(dataset.phone);

        $("#save2").attr("data-id", dataset.id);
        $('#mmodal').modal('show');
    });

    $("#save2").click(function(e) {
        var username = $("input[name='username']").val();
        var password = $("input[name='password']").val();
        var phone = $("input[name='phone']").val();
        var email = $("input[name='email']").val();

        console.log(username,password,phone,email);
        var dataset = e.currentTarget.dataset;
        var id = dataset.id;

        if(username == ""){
            alert("username can't be empty!!");
            return;
        }
        if(password== ""){
            alert("password can't be empty!!");
            return;
        }
        if(phone == ""){
            alert("phone can't be empty!!");
            return;
        }
        if(email == ""){
            alert("email can't be empty!!");
            return;
        }

        if (id != "") { // Edit
            jQuery.ajax({
                url : 'updateUserPro',
                processData : true,
                dataType : "text",
                data : {
                	id:id,
                    username:username,
                    password:password,
					phone:phone,
                    email:email,
                },
                success : function(data) {
                    console.log(id);
                    bootbox.alert({
                        message : 'Modify Successfully! ',
                        callback : function() {
                            location.reload();
                        }
                    });
                }
            });
        }
        $('#mmodal').modal('hide');
    });

});
