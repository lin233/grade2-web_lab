$(function() {

	$("#save").click(function(e) {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();

		var dataset = e.currentTarget.dataset;


        if(username==""){
            alert("Name can't be empty");
            return;
        }

        if(password ==""){
            alert("Password can't be empty");
            return;
        }
			jQuery.ajax({
				url : 'LoginUserPro',
				processData : true,
				dataType : "text",
				data : {
					username : username,
					password : password,
				},
                success : function(data) {
                    bootbox.alert({
                        message : ' ',
                        callback : function() {
                            location.reload();
                        }
                    });
                }
			});


	});

});
