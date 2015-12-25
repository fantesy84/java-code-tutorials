/**
 * 
 */

$(document).ready(function(){
//	$("#add_user").click(function(){
//		var name = $("#add_u_name").val();
//		var user = {"name":name};
//		$.ajax({
//			type: "POST",
//			contentType: 'application/json',
//			url: "../user/update",
//			dataType:"json",
//			data:JSON.stringify(user),
//			success: function(data){
//				var userResults = data.users;
//				for (var i = 0; i < userResults.length; i++) {
//					var u = userResults[i];
//					$("#newUser-id").html(u.id);
//					$("#newUser-name").html(u.name);
//				}
//			},
//			error: function(){
//				console.error("error");
//			}
//		});
//	});
	var token;
	$("#add_user").click(function(){
		var name = $("#add_u_name").val();
		var user = {"name":name};
		$.ajax({
			type: "POST",
			contentType: 'application/json',
			url: "../user/save",
			dataType:"json",
			data:JSON.stringify(user),
			beforeSend: function(xhr) {
				token = getToken("add_user");
				xhr.setRequestHeader("token", token);
			},
			success: function(data, textStatus, jqXHR){
				console.info(data);
				if (data != null) {
					var code = data.code;
					if ("SUCCESS" == code) {
						var users = data.users;
						for (var int = 0; int < users.length; int++) {
							var u = users[i];
							$("#newUser-id").html(u.id);
							$("#newUser-name").html(u.name);
						}
					}
				}
//				console.info(textStatus);
//				console.info(jqXHR);
//				console.info(jqXHR.getAllResponseHeaders());
			},
			error: function(e){
				console.error(e);
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

function getToken(id){
	var isValidate = $("#" + id).attr("duplicateSubmitValidate");
	if (isValidate) {
		$.ajax({
			type : "GET",
			contentType: 'application/json',
			url : "../token/generate",
			dataType:"json",
			success: function(data, textStatus, jqXHR){
				if (textStatus == 200) {
					return data;
				}
			},
			error: function(e){
				console.error(e);
			}
		});
	} else {
		return null;
	}
}