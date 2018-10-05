
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<html>
<head>
<link href="style/bootstrap.min.css" rel="stylesheet">
<link href="style/font-awesome.css" rel="stylesheet">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<title>user profile</title>
<c:set var="user_profile" value="${userProfile} " />
<body class="">
	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<div class="container">
		<section style="padding-bottom: 50px; padding-top: 50px;">
			<div class="row">
				<form id="photo_form" action="front_controller" method="post">
				</form>
				<form id="login_form" action="front_controller" method="post">
					<div class="col-md-7">
						<div class="alert alert-info">
							<h2>User Profile :</h2>
						</div>
						<input type="hidden" id="add_update" name="command" value="" /> <input
							type="hidden" name="action" value="User addition" /> <input
							type="hidden" name="userId" value="${userProfile.id}" />


						<div class="form-group col-md-8">
							<br> <br> <label>First Name</label> <input
								class="form-control" name="FirstName" placeholder="First Name"
								required type="text" value="${userProfile.firstName} ">
							<label>Last Name</label> <input class="form-control"
								name="LastName" placeholder="Last Name" required type="text"
								value="${userProfile.lastName}"> <label>login</label> <input
								class="form-control" name="Login" placeholder="Login" required
								type="text" value="${userProfile.login}"> <label>Role</label>
							<input class="form-control" name="Role" placeholder="Role"
								required type="text" value="${userProfile.userRoleId}">
							<br>

							<c:if test="${role.name == 'admin' }">
								<button type="submit" value="Add User" class="btn btn-success">Add
									Details</button>
								<script>
									$(function() {
										document.getElementById("add_update").value = "add_user";

									});
								</script>
								<br>
								<button type="submit" value="Update User"
									class="btn btn-success">Update Details</button>
								<script>
									$(function() {
										document.getElementById("add_update").value = "update_user";

									});
								</script>
								<br>
							</c:if>
							<c:if test="${role.name eq 'user' }">
								<c:if test="${userProfile.id eq registered_user }">
									<button type="submit" value="Update User"
										class="btn btn-success">Update Details</button>
									<script>
										$(function() {
											document
													.getElementById("add_update").value = "update_user";

										});
									</script>
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
										style="width: 128px; height: 128px;" />
								</div>
							</c:when>
							<c:otherwise>
								<img src="img/img_avatar.png" alt="not found"
									style="width: 128px; height: 128px;" />
							</c:otherwise>

						</c:choose>

						<c:if test="${userProfile.id eq registered_user}">
							<!-- <input id="user_load_photo" type="hidden" id="update_photo"
								name="command" value="" /> -->
							<br>
							<input type="file" name="pic" accept="img/*" value=""
								form="photo_form">
							<script>
								/*$(function() {
									var photo = $('.img1 img').attr('src');
									alert(photo)
									document.getElementById("user_load_photo").value = photo;

								});*/
							</script>
							<input type="submit" value="Update Photo" form="photo_form">

						</c:if>
						<br>
						<div class="form-group col-md-8">
							<h3>Change Your Password</h3>
							<br>
							<c:if test="${role.name != 'admin' }">
								<label>Enter Old Password</label>
								<input class="form-control" name="old_password" type="password"
									required>
								<label>Enter New Password</label>
								<input class="form-control" name="new_password" type="password"
									required>
							</c:if>
							<label>Confirm New Password</label> <input class="form-control"
								name="confirmed_password" type="password" required
								value="${userProfile.userRoleId}"> <br> <a href="#"
								class="btn btn-warning">Change Password</a>
							<pre>Password format:<br />8 characters minimum.<br />At least 1 small, 1 capital and 1 digit required.<br />Only latin letters allowed.
						</pre>
							&#9;
						</div>
					</div>
				</form>


			</div>
		</section>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>