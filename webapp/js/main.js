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


function sortUser(select) {
	var request = $.ajax({
		url : 'front_controller',
		type : 'GET',
		data : {
			command : "sortUserId",
			sort_by : select.options[select.selectedIndex].text
		},
		success : function(data) {
			$('#myTable').html(data);

		}
	});
}

function performAjaxSubmit(select) {
	var sampleFile = document.getElementById("sampleFile").files[0].name;
	 $.ajax({
		url : 'front_controller',
		type : 'POST',
		data : {
			command : "update_photo",
			user_photo_id : select,
			pic : sampleFile
		},
		success : function(data) {
			$("#logo").attr("src", data);
		}
	});
}

function addUserFunc() {
	document.getElementById("add_update").value = "add_user";
}
function updateUserFunc1() {
	document.getElementById("add_update").value = "update_user";
}

function updateUserFunc() {
	var user = document.getElementById("user_ID").value;
	var role = document.getElementById("role_ID").value;
	var fn = document.getElementById("First_Name").value;
	var ln = document.getElementById("Last_Name").value;
	var log = document.getElementById("Login_").value;
	var oldP = document.getElementById("oldPass").value;
	var newP = document.getElementById("newPass").value;
	var conP = document.getElementById("conPass").value;
    var picture = document.getElementById("sampleFile").files[0]?document.getElementById("sampleFile").files[0].name:'';
var command = "update_user";
	 $.ajax({
		url : 'front_controller',
		type : 'POST',
		data : {
			command : command,
			userId : user,
			FirstName : fn,
			LastName : ln,
			Login : log,
			pic : picture,
			roleUser : role,
			old_password : oldP,
			new_password : newP,
			confirmed_password : conP
		},
		success : function(data) {
			alert(data);
			$('#userStatusUpdated').html(data);
		}
	});
}
function ConfirmDelete() {
	var x = confirm("Are you sure you want to delete?");
	if (x)
		return true;
	else
		return false;
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


var completeField;
var completeTable;
var autoRow;
var req;
var isIE;

function init() {
	completeField = document.getElementById("complete-field");
	completeTable = document.getElementById("complete-table");
	autoRow = document.getElementById("auto-row");
	completeTable.style.top = getElementY(autoRow) + "px";
}

function doCompletion() {
	var url = "SearchServlet?action=complete&id=" + escape(completeField.value);
	req = initRequest();
	req.open("GET", url, true);
	req.onreadystatechange = callback;
	req.send(null);
}

function initRequest() {
	if (window.XMLHttpRequest) {
		if (navigator.userAgent.indexOf('MSIE') != -1) {
			isIE = true;
		}
		return new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		isIE = true;
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function callback() {
	clearTable();
	if (req.readyState == 4) {
		if (req.status == 200) {
			parseMessages(req.responseXML);
		}
	}
}

function appendUser(firstName, lastName, userSearchId) {
	var row;
	var cell;
	var linkElement;

	if (isIE) {
		completeTable.style.display = 'block';
		row = completeTable.insertRow(completeTable.rows.length);
		cell = row.insertCell(0);
	} else {
		completeTable.style.display = 'table';
		row = document.createElement("tr");
		cell = document.createElement("td");
		row.appendChild(cell);
		completeTable.appendChild(row);
	}

	cell.className = "popupCell";

	linkElement = document.createElement("a");
	linkElement.className = "popupItem";
	linkElement.setAttribute("href", "SearchServlet?action=lookup&id="
			+ userSearchId);
	linkElement
			.appendChild(document.createTextNode(firstName + " " + lastName));
	cell.appendChild(linkElement);
}

function getElementY(element) {
	var targetTop = 0;
	if (element.offsetParent) {
		while (element.offsetParent) {
			targetTop += element.offsetTop;
			element = element.offsetParent;
		}
	} else if (element.y) {
		targetTop += element.y;
	}
	return targetTop;
}

function clearTable() {
	if (completeTable.getElementsByTagName("tr").length > 0) {
		completeTable.style.display = 'none';
		for (loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
			completeTable.removeChild(completeTable.childNodes[loop]);
		}
	}
}

function parseMessages(responseXML) {
	if (responseXML == null) {
		return false;
	} else {
		var usersSearch = responseXML.getElementsByTagName("searchedUsers")[0];
		if (usersSearch.childNodes.length > 0) {
			completeTable.setAttribute("bordercolor", "black");
			completeTable.setAttribute("border", "1");
			for (loop = 0; loop < usersSearch.childNodes.length; loop++) {
				var userSearch = usersSearch.childNodes[loop];
				var firstName = userSearch.getElementsByTagName("firstName")[0];
				var lastName = userSearch.getElementsByTagName("lastName")[0];
				var userSearchId = userSearch.getElementsByTagName("id")[0];
				appendUser(firstName.childNodes[0].nodeValue,
						lastName.childNodes[0].nodeValue,
						userSearchId.childNodes[0].nodeValue);
			}
		}
	}
}