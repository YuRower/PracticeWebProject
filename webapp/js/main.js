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

/*function deleteConfirmation() {
	$("#delete_confirm_modal").modal("show");
	$("#delete_confirm_modal input#user_id").val($(element).attr('user_id')); 
}*/

$(function() {
	$('del_user').confirmation({
		selector: '[data-toggle="confirmation"]'
	});

});

function makeLink() {
	$("#myTable #UserID").css({
		"cursor" : "pointer",
		"color" : "blue",
		"text-decoration" : "underline"
	});

}
