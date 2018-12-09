<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>

<html ng-app="AngularTestApp">
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<head>
<title>Web Practice</title>
</head>
<script>
        var model = "Welcome To My WEBSITE";


        app.controller("ChangeCtrl", function ($scope) {
            $scope.message = model;

            $scope.clickHandler = function () {
                $scope.message = $scope.text;
            }
        });

    </script>
    
<body ng-controller="ChangeCtrl">
    <h1 align="center"> {{message}}</h1>
    Input your greeting with angular<input ng-model="text"  />
    <button ng-click="clickHandler()"style="color: White;  width: 25%;"> Update</button>
</body>
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


<p>
	<c:out value="Sort by: " />
	<select name="sort_by" onchange="sortUser(this)">
		<option value="ID_ASC">Ascending</option>
		<option value="ID_DSC">Descending</option>
	</select>
</p>

<div ng-controller="SampleAppCtrl" class="container" style="margin-top:10px;">
        <div class="btn-group">
            <button class="btn btn-default" ng-click="showList()"style="color: White;  width: 25%;">List</button>
            <button class="btn btn-default" ng-click="showTable()"style="color: White;  width: 25%;">Table</button>
        </div>

        <ng-include src="url"></ng-include>
    </div>

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