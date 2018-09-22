
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<head>
<title>Web Practice</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

</head>
<body>


	<button onclick="document.getElementById('id01').style.display='block'"
		style="width: auto;">Login</button>

	<div id="id01" class="modal">

		<form id="login_form" action="front_controller" method="post"
			class="modal-content animate">
			<input type="hidden" name="command" value="login" />
			<div class="imgcontainer">
				<span onclick="displayModalWindow()" class="close"
					title="Close Modal">&times;</span> <img src="img/img_avatar.png"
					alt="Avatar" class="avatar">
			</div>

			<div class="container">
				<label for="login"><b>Username</b></label> <input type="text"
					placeholder="Enter Login" name="login" required> <label
					for="psw"><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="password" required>

				<button type="submit" value="Login">Login</button>
				<label> <input type="checkbox" checked="checked"
					name="remember"> Remember me
				</label>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button" onclick="displayModalWindow()"
					class="cancelbtn">Cancel</button>
				<span class="password">Forgot <a href="#">password?</a></span>
			</div>
		</form>
	</div>

<body>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<table>
	<tr>
		<td class="content center">
		
				<h3>User List</h3>
				<table class="center">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Surname</th>
						<th>Login</th>
						<th colspan="2">Role</th>
					</tr>
					<c:forEach  var="user" items="${users}">
						<tr>
							<td><fmt:formatNumber type="number" minIntegerDigits="3"
									value="${user.id}" /></td>
							<td><c:out value="${user.firstName}" /></td>
							<td><c:out value="${user.lastName}" /></td>
							<td><c:out value="${user.login}" /></td>
							<td><c:out value="${role.role(user.userRoleId).name}" /></td>
							
					</c:forEach>
				</table>
	</tr>
</table>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>


</body>
</html>
