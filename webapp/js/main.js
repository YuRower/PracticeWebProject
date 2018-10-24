function closeModalWindow() {
	var modal = document.getElementById('id01').style.display = 'none';
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}
function displayModalWindow() {
	var modal = document.getElementById('id01').style.display = 'block';
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}

$(function() {
	$('del_user').confirmation({
		selector : '[data-toggle="confirmation"]'
	});

});
function addUserFunc() {
	document.getElementById("add_update").value = "add_user";
	$.ajax({
		url : 'front_controller',
		type : 'GET',
		data : {
			command : "delete_user",
			action : "User deleted",
			userId: user_Id
		},
		contentType : 'application/json; charset=utf-8'
	});
}
function updateUserFunc() {
	document.getElementById("add_update").value = "update_user";

}
function ConfirmDelete(user_Id) {
	alert(user_Id)
	var x = confirm("Are you sure you want to delete?");
	if (x) {
		$.ajax({
			url : 'front_controller',
			type : 'GET',
			data : {
				command : "delete_user",
				action : "User deleted",
				userId: user_Id
			},
			contentType : 'application/json; charset=utf-8'
		});
		return true;

	} else {
		return false;
	}
}

function makeLink() {
	$("#myTable #UserID").css({
		"cursor" : "pointer",
		"color" : "blue",
		"text-decoration" : "underline"
	});
	$("#myTable #UserID").click(function() {
		var currentUser = $(this).text();
		document.getElementById('hiddenField').value = currentUser;
		document.getElementById("myForm").submit();
	});

}
function goBack() {
	window.history.back();
}

/*
 * AJAX QUERY
 */
function searchUser(str) {
	var xhttp;
	xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			// document.getElementById("user_lastName").innerHTML =
			// this.responseText;
			alert(this.responseText);
		}
	};
	xhttp.open("GET", "front_controller?command=search_user", true);
	xhttp.send();
}
