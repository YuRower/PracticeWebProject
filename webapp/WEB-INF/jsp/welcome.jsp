
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
					placeholder="Enter Login" name="login" required> <label
					for="psw"><b>Password</b></label> <input type="password"
					placeholder="Enter Password" name="password" required>

				<button type="submit" value="Login">Login</button>

				<div name="Logout"
					style="height: 0px; width: 0px; overflow: hidden;">
					<a href="controller?command=logout">Log out</a>
				</div>
				<label> <input type="checkbox" checked="checked"
					name="remember"> Remember me
				</label>
			</div>

			<div class="container" style="background-color: #f1f1f1">
				<button type="button" onclick="closeModalWindow()" class="cancelbtn">Cancel</button>
				<span class="password">Forgot <a href="#">password?</a></span>
			</div>
		</form>
	</div>
<body>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
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
						<th>Role</th>
						<th colspan="5">Photo</th>
						
					</tr>
					<c:forEach var="user" items="${users}">
						<tr>
							<td><fmt:formatNumber type="number" minIntegerDigits="3"
									value="${user.id}" /></td>
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
							<td><c:out value="${user.userPhotoId}" /></td>
					</c:forEach>
				</table>
		</tr>
	</table>

	<!--  <div class="row">

		<form action="front_controller" method="get">

			<input type="hidden" name="command" value="add_user" />

			<div class="col-xs-5">
				<div class="text-right">
					<button type="button" class="btn btn-default">Add User</button>
				</div>
			</div>
		</form>
	</div>-->

	<c:if test="${role.name eq 'admin'}">


		<div class="container">
			<a href="front_controller?command=add_user" class="btn btn-info"
				role="button">Add User</a>

		</div>
	</c:if>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>
