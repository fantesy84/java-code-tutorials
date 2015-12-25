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
	var token = "";
	$.ajax({
		type: "GET",
		url: "../token/generate",
		dataType:"json",
		success: function(data){
			token = data;
		}
	});
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
				xhr.setRequestHeader("token", token);
			},
			success: function(data){
				var userResults = data.users;
				for (var i = 0; i < userResults.length; i++) {
					var u = userResults[i];
					$("#newUser-id").html(u.id);
					$("#newUser-name").html(u.name);
				}
			},
			error: function(){
				console.error("error");
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