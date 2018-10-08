<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/font-awesome.css" rel="stylesheet">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<c:set var="user_profile" value="${userProfile}" />
<body class="">
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<div class="container">
		<section style="padding-bottom: 50px; padding-top: 50px;">
			<div class="row">
				<form id="photo_form" action="front_controller" method="post">
				</form>
				<form id="password_form" action="front_controller" method="post">
				</form>
				<form id="login_form" action="front_controller" method="post">
					<div class="col-md-7">
						<div class="alert alert-info">
							<h2>User Profile :</h2>
						</div>
						<input type="hidden" id="add_update" name="command" value="" />
						 <input type="hidden" name="action" value="User added" />
						  <input type="hidden" name="userId" value="${userProfile.id}" />
						  <input type="hidden" name="roleUser" value="${role.name}" />

						<div class="form-group col-md-8">
							<br>
							<br>
							 <label>First Name</label>
							  <input class="form-control" name="FirstName" placeholder="First Name"
								required type="text" value="${userProfile.firstName} "
								 pattern="^[a-zA-Z0-9_-]{3,15}$"
				  title="Match characters and symbols in the list, a-z,A-Z, 0-9, underscore, hyphen
             {3,15} and Length at least 3 characters and maximum length of 15 ">
							<label>Last Name</label> 
							<input class="form-control" name="LastName" placeholder="Last Name" required type="text"
								value="${userProfile.lastName}" pattern="^[a-zA-Z0-9_-]{3,15}$"
				  title="Match characters and symbols in the list, a-z,A-Z, 0-9, underscore, hyphen
             {3,15} and Length at least 3 characters and maximum length of 15 ">
							 <label>login</label>
							  <input class="form-control" name="Login" placeholder="Login" required
								type="text" value="${userProfile.login}"  pattern="^[a-zA-Z0-9_-]{3,15}$"
				  title="Match characters and symbols in the list, a-z,A-Z, 0-9, underscore, hyphen
             {3,15} and Length at least 3 characters and maximum length of 15 "> 
							<br>
							
							<c:if test="${role.name == 'admin' }">
							<label>Role</label>
							  <input class="form-control" name="Role" placeholder="Role"
								required type="text" value="${userProfile.userRoleId}" pattern="^\d{1}$">
								<button type="submit" onclick="addUserFunc()" value="Add User"
									class="btn btn-success">Add User</button>
								<br>
								
							</c:if>
							<c:if test="${ (role.name eq 'user') || (role.name eq 'admin') }">
								<c:if test="${(userProfile.id eq registered_user) || (role.name eq 'admin')}">									
								<button type="submit" onclick="updateUserFunc()" value="Update User" 
								class="btn btn-success">Update User Details </button>
									<br>
								</c:if>
							</c:if>
							<br>
						</div>
					</div>

					<div class="col-md-5">
						<c:choose>
							<c:when test="${ not empty userPhoto.name}">
								<div class="img1">
									<img src="img/${userPhoto.name}" alt="not found"
										<c:out value="${userPhoto.name}" />
										style="width: 128px; height: 128px;" />
								</div>
							</c:when>
							<c:otherwise>
								<img src="img/img_avatar.png" alt="not found"
									style="width: 128px; height: 128px;" />
							</c:otherwise>
						</c:choose>

						<c:if test="${(userProfile.id eq registered_user )|| (role.name eq 'admin') }">
							<br>
							<input type="file" name="pic" accept="img/*" value=""
								form="photo_form">
							<script>
								$('#photo_form').submit(
												function() {
													var photo = $('.img1 img').attr('src');
													document.getElementById("user_load_photo").value = photo;
												});
							</script>

							<input type="hidden" name="command" value="update_photo"
								form="photo_form">
							<input type="hidden" name="user_photo_id"
								value="${userProfile.id}" form="photo_form" />
							<input type="hidden" name="action" value="Photo updated"
								form="photo_form" />
							<input type="submit" value="Update Photo" form="photo_form">
							<br>

							<div class="form-group col-md-8">
								<h3>Change Your Password</h3>
								<br>
								
								<c:if test="${role.name != 'admin' }">
									<label>Enter Old Password</label>
									<input class="form-control" name="old_password" type="password"
										form="password_form" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}">
									<label>Enter New Password</label>
									<input class="form-control" name="new_password" type="password"
										form="password_form" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}" >
									<input type="hidden" name="userId" value="${userProfile.id}" />
								</c:if>

								<label>Confirm New Password</label>
								 <input class="form-control" name="confirmed_password" type="password" form="password_form" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{4,}"
									required>
									<input type="hidden" name="command" value="change_password" form="password_form" />	 
									<input type="hidden" name="userId" value="${userProfile.id}" form="password_form" />
									 <br>
									<input type="hidden" name="roleUser" value="${role.name}" form="password_form" />
								<button type="submit" class="btn btn-warning"
									form="password_form">Change Password</button>
								<pre>Password format:<br />4 characters minimum.<br />At least one lowercase letter, one uppercase and one number required.<br />Only latin letters allowed.
						</pre>					
							</div>
						</c:if>
						<c:if test="${userProfile.id ne registered_user}">
							<c:remove var="userProfile" scope="session" />
						</c:if>
					</div>
				</form>
			</div>
		</section>
	</div>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>