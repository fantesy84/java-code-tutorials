/**
 * 
 */

$(document).ready(function(){
	$("#add_user").click(function(){
		var name = $("#add_u_name").val();
		var user = {"name":name};
		$.ajax({
			type: "POST",
			contentType: 'application/json',
			url: "../user/update",
			dataType:"json",
			data:JSON.stringify(user),
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
	$("#clean_cache").click(function(){
		var id = $("#query_u_id").val();
		var catUrl = "../user/cache_clean/";
		if (id == null || id.length == 0) {
			catUrl = catUrl + "0";
		} else {
			catUrl = catUrl + id;
		}
		$.ajax({
			type: "GET",
			contentType: 'application/json',
			url: catUrl,
			dataType:"json",
			//data:JSON.stringify(user),
			success: function(data){
				alert(data.message);
			},
			error: function(){
				console.error("error");
			}
		});
	});
	$("#update_cache").click(function(){
		var id = $("#query_u_id").val();
		var catUrl = "../user/cache_update/";
		if (id == null || id.length == 0) {
			catUrl = catUrl + "0";
		} else {
			catUrl = catUrl + id;
		}
		$.ajax({
			type: "GET",
			contentType: 'application/json',
			url: catUrl,
			dataType:"json",
			//data:JSON.stringify(user),
			success: function(data){
				alert(data.message);
			},
			error: function(){
				console.error("error");
			}
		});
	});
});