<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>

<html ng-app="AngularTestApp">
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<head>
<title>Web Practice</title>
</head>

<body ng-controller="ChangeCtrl">
	<h1 align="center">{{message}}</h1>
	Input your greeting with angular
	<input ng-model="text" />
	<button ng-click="clickHandler()" style="color: White; width: 25%;">
		Update</button>
</body>

<body>
	<c:set var="registered_user" value="${user.id}" scope="session" />
	<div id="id01" class="modal" ng-controller="validateCtrl">

		<form id="login_form" name="myForm" action="front_controller"
			method="post" class="modal-content animate" novalidate
			ng-submit="addNewUser(newUser, myForm.$valid)">

			<input type="hidden" name="command" value="login" />
			<div class="imgcontainer">
				<span onclick="closeModalWindow()" class="close" title="Close Modal">&times;</span>
				<img src="img/img_avatar.png" alt="Avatar" class="avatar">
			</div>

			<div class="container">
				<div class="form-group">
					<label for="login"><b>Username</b></label> <input type="text"
						name="login" required ng-model="newUser.name">
					<div class="error"
						ng-show="myForm.login.$invalid && myForm.login.$dirty">
						{{getError(myForm.login.$error)}}</div>
				</div>
				<div class="form-group">

					<label for="psw">Password</label> <input type="password"
						name="userPassword" id="psw" required ng-model="newUser.password">
					<div class="error"
						ng-show="myForm.userPassword.$invalid && myForm.userPassword.$dirty">
						{{getError(myForm.userPassword.$error)}}</div>
				</div>

				<div class="form-group">
					<label>Email:</label> <input name="userEmail" type="email"
						class="form-control" required ng-model="newUser.email">
					<div class="error"
						ng-show="myForm.userEmail.$invalid && myForm.userEmail.$dirty">
						{{getError(myForm.userEmail.$error)}}</div>
				</div>

				<button type="submit" value="Login" ng-disabled="myForm.$invalid">Login</button>

			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button" onclick="closeModalWindow()" class="cancelbtn">Cancel</button>
				<span class="password">Forgot <a href="#">password?</a></span>
			</div>
			<div class="well">
				Message: {{message}}
				<div>
					Valid: <span class="summary"
						ng-class="myForm.$valid ? 'ng-valid' : 'ng-invalid'">
						{{myForm.$valid}} </span>
				</div>
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

<body ng-controller="ajaxCtrl">
	<div class="panel panel-primary" ng-controller="ajaxCtrl">
		<div class="panel-heading">
			<div class="panel-title">
				<button class="btn btn-success" style="color: White; width: 25%;"ng-click="sendRequest()">
					<h2>Get Currency</h2>
				</button>
			</div>
		</div>
		<table class="table">
			<tr>
				<th>ccy</th>
				<th>base_ccy</th>
				<th>buy</th>
				<th>sale</th>
			</tr>
			<tr ng-repeat="item in items">
				<td>{{item.ccy}}</td>
				<td>{{item.base_ccy}}</td>
				<td>{{item.buy}}</td>
				<td>{{item.sale}}</td>

			</tr>
		</table>
	</div>
</body>

<p>
	<c:out value="Sort by: " />
	<select name="sort_by" onchange="sortUser(this)">
		<option value="ID_ASC">Ascending</option>
		<option value="ID_DSC">Descending</option>
	</select>
</p>

<div ng-controller="viewTableCtrl" class="container"
	style="margin-top: 10px;">
	<div class="btn-group">
		<button class="btn btn-default" ng-click="showList()"
			style="color: White; width: 25%;">List</button>
		<button class="btn btn-default" ng-click="showTable()"
			style="color: White; width: 25%;">Table</button>
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