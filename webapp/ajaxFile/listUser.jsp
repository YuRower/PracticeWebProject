<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<h3>User List</h3>

	<ul id="myTable">
				<li class="center">
			
					<c:forEach var="user" items="${users}">
						<input type="hidden" name="user" value="${users}"
							form="search_user">
						
							<li id="UserID"><c:out value="${user.id}" />- ID</li>
							<li><c:out value="${user.firstName}" />- Name </li>
							<li><c:out value="${user.lastName}" />- Surname</li>
							<li><c:out value="${user.login}" />- Login</li>
							<c:choose>
								<c:when test="${user.userRoleId== 1}">
									<li><c:out value="Admin" />- Role</li>
								</c:when>
								<c:when test="${user.userRoleId== 2}">
									<li><c:out value="User" />- Role</li>
								</c:when>
								<c:otherwise>
									<li><c:out value="Guest" /></li>
								</c:otherwise>
							</c:choose>
							<c:if test="${user.userPhotoId eq '0'}">
								<li><img src="img/img_avatar.png"
									style="width: 128px; height: 128px;" />- Photo</li>
							</c:if>
							<c:forEach var="photo" items="${photos}">
								<c:if test="${user.userPhotoId eq photo.id}">
									<li><img src="img/${photo.name}"
										onerror="if (this.src != 'img/img_avatar.png') this.src = 'img/img_avatar.png';"
										style="width: 128px; height: 128px;" />- Photo</li>
								</c:if>
							</c:forEach>

							<c:if test="${role.name eq 'admin'}">
								<li>
									<button type="submit" class="demo"
										onclick="ConfirmDelete(${user.id})"
										style="color: White; background-color: #d9534f; width: 50%;">
										Delete</button>
								</li>
							</c:if>
					</c:forEach>
					
					</li>
	</ul>