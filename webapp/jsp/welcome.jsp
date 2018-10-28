<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<head>
<title>Web Practice</title>
</head>
<body>
	<c:set var="registered_user" value="${user.id}" scope="session" />
	<!-- coding: ${ pageContext.request.characterEncoding }-->
	<div id="id01" class="modal">

		<form id="login_form" action="front_controller" method="post"
			class="modal-content animate">

			<input type="hidden" name="command" value="login" />
			<div class="imgcontainer">
				<span onclick="closeModalWindow()" class="close" title="Close Modal">&times;</span>
				<img src="img/img_avatar.png" alt="Avatar" class="avatar">
			</div>

			<div class="container">
				<label for="login"><b>Username</b></label> <input type="text"
					placeholder="Enter Login" name="login"
					pattern="^[a-zA-Z0-9_-]{3,15}$"
					title="Match characters and symbols in the list, a-z,A-Z, 0-9, underscore, hyphen
             {3,15} and Length at least 3 characters and maximum length of 15 ">
				<label for="psw">Password</label> <input type="password" id="psw"
					placeholder="Enter Password" name="password"
					pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}"
					title="Must contain at least one number and one uppercase and lowercase letter, and at least 4 or more characters"
					required>

				<button type="submit" value="Login">Login</button>

			</div>
			<div id="message">
				<h3>Password must contain the following:</h3>
				<p id="letter" class="invalid">
					A <b>lowercase</b> letter
				</p>
				<p id="capital" class="invalid">
					A <b>capital (uppercase)</b> letter
				</p>
				<p id="number" class="invalid">
					A <b>number</b>
				</p>
				<p id="length" class="invalid">
					Minimum <b>4 characters</b>
				</p>
			</div>
			<div class="container" style="background-color: #f1f1f1">
				<button type="button" onclick="closeModalWindow()" class="cancelbtn">Cancel</button>
				<span class="password">Forgot <a href="#">password?</a></span>
			</div>
		</form>
	</div>
<body>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<jsp:useBean id="photo" scope="request"
		class="ua.shvidkoy.webproject.model.entity.Photo" />
<body onload="init()">
	<form name="autofillform" action="SearchServlet">
	
			
		<table border="0" cellspacing="5">
			<thead>
				<tr>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><strong>User Name:</strong></td>
					<td><input type="text" size="40" id="complete-field"
						onkeyup="doCompletion();"></td>
				</tr>
				<tr>
					<td id="auto-row" colspan="2">
						<table id="complete-table" class="popupBox"></table>
					</td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
<!--  <form id="search_user">
		<input type="text" name="search" placeholder="Search.."
			onchange="searchUser(this.value)"> <input type="hidden"
			name="seeked_lastName" value="this.value" />
	</form> -->




<p>
	<c:out value="Sort by: " />
	<select name="sort_by" onchange="sortUser(this)">
		<option value="ID_ASC">Ascending</option>
		<option value="ID_DSC">Descending</option>
	</select>
</p>


<!--  <form action="front_controller" id="sortform" method="POST">
		<input type="hidden" name="command" value="sortUserId" />
		 <input type="submit" value="Sorting" style="width: 10%;" />
	</form>-->

<table id="myTable">
	<tr>

		<td class="content center">
			<h3>User List</h3>
			<table class="center">

				<tr class="headTable">
					<th>ID</th>
					<th>Name</th>
					<th>Surname</th>
					<th>Login</th>
					<th>Role</th>
					<th>Photo</th>
				</tr>

				<c:forEach var="user" items="${users}">
					<input type="hidden" name="user" value="${users}"
						form="search_user">
					<tr>

						<td id="UserID"><c:out value="${user.id}" /></td>
						<td><c:out value="${user.firstName}" /></td>
						<td><c:out value="${user.lastName}" /></td>
						<td><c:out value="${user.login}" /></td>
						<c:choose>
							<c:when test="${user.userRoleId== 1}">
								<td><c:out value="Admin" /></td>
							</c:when>
							<c:when test="${user.userRoleId== 2}">
								<td><c:out value="User" /></td>
							</c:when>
							<c:otherwise>
								<td><c:out value="Guest" /></td>
							</c:otherwise>
						</c:choose>
						<c:if test="${user.userPhotoId eq '0'}">
							<td><img src="img/img_avatar.png"
								style="width: 128px; height: 128px;" /></td>
						</c:if>
						<c:forEach var="photo" items="${photos}">
							<c:if test="${user.userPhotoId eq photo.id}">
								<td><img src="img/${photo.name}"
									onerror="if (this.src != 'img/img_avatar.png') this.src = 'img/img_avatar.png';"
									style="width: 128px; height: 128px;" /></td>
							</c:if>
						</c:forEach>

						<c:if test="${role.name eq 'admin'}">
							<td>
								<form action="front_controller" method="POST">
									<input type="hidden" name="command" value="delete_user" /> <input
										type="hidden" name="action" value="User deleted" /> <input
										type="hidden" name="userId" value="${user.id}" />
									<button type="submit" class="demo" onclick="ConfirmDelete()"
										style="color: White; background-color: #d9534f; width: 50%;">
										Delete</button>
								</form>
							</td>
						</c:if>
				</c:forEach>

			</table>
	</tr>
</table>
<c:if test="${role.name eq 'admin'}">
	<script>
		$('tr.headTable').append('<th colspan="5">Delete User</th>');
	</script>

	<div class="container">
		<a href="front_controller?command=redirect_profile" class="button">Add
			User</a>
	</div>
</c:if>

<script>
	var myInput = document.getElementById("psw");
	var letter = document.getElementById("letter");
	var capital = document.getElementById("capital");
	var number = document.getElementById("number");
	var length = document.getElementById("length");

	// When the user clicks on the password field, show the message box
	myInput.onfocus = function() {
		document.getElementById("message").style.display = "block";
	}

	// When the user clicks outside of the password field, hide the message box
	myInput.onblur = function() {
		document.getElementById("message").style.display = "none";
	}

	// When the user starts to type something inside the password field
	myInput.onkeyup = function() {
		// Validate lowercase letters
		var lowerCaseLetters = /[a-z]/g;
		if (myInput.value.match(lowerCaseLetters)) {
			letter.classList.remove("invalid");
			letter.classList.add("valid");
		} else {
			letter.classList.remove("valid");
			letter.classList.add("invalid");
		}

		// Validate capital letters
		var upperCaseLetters = /[A-Z]/g;
		if (myInput.value.match(upperCaseLetters)) {
			capital.classList.remove("invalid");
			capital.classList.add("valid");
		} else {
			capital.classList.remove("valid");
			capital.classList.add("invalid");
		}

		// Validate numbers
		var numbers = /[0-9]/g;
		if (myInput.value.match(numbers)) {
			number.classList.remove("invalid");
			number.classList.add("valid");
		} else {
			number.classList.remove("valid");
			number.classList.add("invalid");
		}

		// Validate length
		if (myInput.value.length >= 4) {
			length.classList.remove("invalid");
			length.classList.add("valid");
		} else {
			length.classList.remove("valid");
			length.classList.add("invalid");
		}
	}
</script>

<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>