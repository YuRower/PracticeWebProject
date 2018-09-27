
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
<body class="">

	<div class="container">
		<section style="padding-bottom: 50px; padding-top: 50px;">
			<div class="row">
				<form id="login_form" action="front_controller" method="post">
					<div class="col-md-7">
						<div class="alert alert-info">
							<h2>User Profile :</h2>
						</div>
						<input type="hidden" name="command" value="update_user" />
						<div class="form-group col-md-8">
							<br> <br> <label>First Name</label> <input
								class="form-control" name="First Name" placeholder="First Name"
								type="text"> <label>Last Name</label> <input
								class="form-control" name="Last Name" placeholder="Last Name"
								type="text"> <label>login</label> <input
								class="form-control" name="Login" placeholder="Login"
								type="text"> <label>Role</label> <input
								class="form-control" name="Role" placeholder="Role" type="text">
							<br>
							<button type="submit" value="Add User" class="btn btn-success">Update
								Details</button>
							<br> <br>
						</div>
					</div>
					<div class="col-md-5">
						<!--  <form action="">
							<input type="file" name="pic" accept="image/*">
						</form>-->
						<div class="form-group col-md-8">
							<h3>Change Your Password</h3>
							 <br>
                            <label>Enter Old Password</label>
                            <input class="form-control" name= "old_password" type="password">
                            <label>Enter New Password</label>
                            <input class="form-control"  name= "new_password" type="password">
                            <label>Confirm New Password</label>
                            <input class="form-control"  name ="confirmed_password" type="password">
<br>
 <a href="#" class="btn btn-warning">Change Password</a>
						</div>
					</div>
				</form>


			</div>
		</section>
	</div>

	<%@ include file="/WEB-INF/jspf/footer.jspf"%>

</body>
</html>