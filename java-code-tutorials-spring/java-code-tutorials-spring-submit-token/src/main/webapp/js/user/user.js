/**
 * 
 */
$(document).ready(function(){
	var clientToken = getToken("add_user");
	$("#add_user").click(function(){
		var name = $("#add_u_name").val();
		var user = {"name":name};
		$.ajax({
			type: "POST",
			contentType: 'application/json',
			url: "../user/save",
			dataType:"json",
			data:JSON.stringify(user),
			beforeSend: function(XHR) {
				console.info("token header: " + clientToken);
				XHR.setRequestHeader("token", clientToken);
			},
			success: function(data, textStatus, jqXHR){
				if (data != null) {
					var code = data.code;
					if ("SUCCESS" == code) {
						var users = data.users;
						if (users != null && users.length > 0) {
							for (var int = 0; int < users.length; int++) {
								var u = users[i];
								$("#newUser-id").html(u.id);
								$("#newUser-name").html(u.name);
							}
						}
					}
				}
			},
			error: function(XHR, errorTexts, exception){
				
			},
			complete : function(XHR, textStatus){
				
			}
		});
	});
	$("#query_user").click(function(){
		var id = $("#query_u_id").val();
		var name = $("#query_u_name").val();
		var user = {"id":id,"name":name};
		$.ajax({
			type: "POST",
			contentType: 'application/json',
			url: "../user/query",
			dataType:"json",
			data:JSON.stringify(user),
			success: function(data){
				var userResults = data.users;
				for (var i = 0; i < userResults.length; i++) {
					var u = userResults[i];
					$("#user-table-body").html("<tr><td>" + u.id + "</td><td>" + u.name + "</td></tr>");
				}
			},
			error: function(){
				console.error("error");
			}
		});
	});
});

function getToken(id) {
	var isValidate = $("#" + id).attr("duplicateSubmitValidate");
	var clientToken = "";
	if (isValidate) {
		$.ajax({
			type : "GET",
			contentType: 'application/json',
			url : "../token/generate",
			dataType:"json",
			ifModified : true,
			async : false,
			success: function(data, textStatus, jqXHR){
				clientToken = data.token;
				console.info("Token from server: " + clientToken);
			}
		});
		return clientToken;
	}
}