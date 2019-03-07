$(function() {

	$("#save").click(function(e) {
		var username = $("input[name='username']").val();
		var password = $("input[name='password']").val();
        var password2 = $("input[name='password2']").val();
        var phone = $("input[name='phone']").val();
        var email = $("input[name='email']").val();


		var dataset = e.currentTarget.dataset;
		var id = dataset.id;


        if(username==""){
            alert("Name can't be empty");
            return;
        }

        if(password ==""){
            alert("Password can't be empty");
            return;
        }

        if(password!=password2){
            alert("Repeated password fail");
            return;
        }
        if(phone==""){
            alert("Phone can't be empty");
            return;
        }
        if(email==""){
            alert("Email can't be empty");
            return;
        }
		  // Add

        console.log(username, password, phone,email);
			jQuery.ajax({
				url : 'addUserPro',
				processData : true,
				dataType : "text",
				data : {
					username : username,
					password : password,
					role : "user",
                    phone :phone,
                    email : email
				},

				success : function(data) {
				    alert(data);
					bootbox.alert({
						message : 'Sign Up Successfully! ',
						callback : function() {
							location.reload();
						}
					});
				},

			})

	});



});
