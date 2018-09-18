
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

	<%@ include file="/WEB-INF/jspf/user_type.jspf"%>

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

<c:set var="title" value="Users" />
<body>
<table>
	<tr>
		<td class="content center"><c:if test="${  empty users}">
				<h3>User List</h3>
				<table class="center">
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Surname</th>
						<th>Login</th>
						<th colspan="2">Actions</th>
					</tr>
					<c:forEach var="users" items="${users}">
						<tr>
							<td><fmt:formatNumber type="number" minIntegerDigits="3"
									value="${users.id}" /></td>
							<td><c:out value="${users.name}" /></td>
							<td><c:out value="${users.surname}" /></td>
							<td><c:out value="${users.email}" /></td>
					</c:forEach>
				</table>
			</c:if></td>
	</tr>
</table>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>


</body>
</html>
