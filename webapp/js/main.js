function closeModalWindow() {
	var modal = document.getElementById('id01').style.display='none';
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}
function displayModalWindow() {
	var modal = document.getElementById('id01').style.display='block';
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
		if (event.target == modal) {
			modal.style.display = "none";
		}
	}
}

function deleteConfirmation() {
    $("#delete_confirm_modal").modal("show");
    /*$("#delete_confirm_modal input#user_id").val($(element).attr('user_id'));*/
}
function makeLink(){
	$(function() {
		$("td:nth-child(2)").css(
	            {
	            "cursor":"pointer",
	            "color":"blue",
	            "text-decoration": "underline"
	            });

	     $("td").click(function(){
	    	var currentUser=$(this).text()
	    	 alert(currentUser);
	    	 /*alert($(this).text());*/
	    	
	       window.location = "front_controller?command=redirect_profile";
	     });
});}
