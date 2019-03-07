$(function() {

	$("#save").click(function(e) {


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

    $(".buy").click(function(e) {

        var dataset = e.currentTarget.dataset;
        var id = dataset.id;
        console.log(id);

        var title = dataset.title;
        var author = dataset.author;
        var price = dataset.price;
        var publisher = dataset.publisher;
        var language = dataset.language;
        var amount = dataset.amount;
        console.log(title, author, price, publisher, language,amount);


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
					amount: amount
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


});
