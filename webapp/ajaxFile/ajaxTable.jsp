<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title>Web Practice</title>
</head>
<body>
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
									<button type="submit" class="demo"
										onclick="ConfirmDelete(${user.id})"
										style="color: White; background-color: #d9534f; width: 50%;">
										Delete</button>
								</td>
							</c:if>
					</c:forEach>
				</table>
		</tr>
	</table>	
</body>
</html>