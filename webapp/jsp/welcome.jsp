<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<link href="../style/bootstrap.min.css" rel="stylesheet">

<head>
<title>Web Practice</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	<jsp:useBean id="photo" scope="request"
		class="ua.shvidkoy.webproject.model.entity.Photo" />
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
										<button type="submit" class="demo" data-toggle="confirmation"
											style="color: White; background-color: #d9534f; width: 50%;">
											Delete</button>
									</form> <script>
										<script>
										$(function() {
											$('.demo').confirmation({
												onConfirm : function() {
													alert("hi");
												}

											});
										});
									</script>
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
	<script src="../js/jquery-1.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-confirmation.js"></script>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>