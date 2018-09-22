
<html><head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
<%@ include file="/WEB-INF/jspf/header.jspf"%>
    <title>user profile</title>
<body class="">
    <div class="container">
        <section style="padding-bottom: 50px; padding-top: 50px;">
            <div class="row">
                <div class="col-md-7">
                    <div class="alert alert-info">
                        <h2>User Profile : </h2>
                    </div>
                    <div class="form-group col-md-8">

                        <br>
                        <br>
                        <label>First Name</label>
                        <input class="form-control" placeholder="First Name" type="text">
                        <label>Last Name</label>
                        <input class="form-control" placeholder="Last Name" type="text">
                        <label>login</label>
                        <input class="form-control" placeholder="Login" type="text">
                        <label>Role</label>
                        <input class="form-control" placeholder="Role" type="text">
                        <br>
                        <a href="#" class="btn btn-success">Update Details</a>
                        <br><br>
                        </div>
                    </div>
                    <div class="col-md-5">
                        <form action="">
                            <input type="file" name="pic" accept="image/*">
                        </form>                  
                        <div class="form-group col-md-8">
                            <h3>Change Your Password</h3>
                            <br>
                            <label>Enter Old Password</label>
                            <input class="form-control" type="password">
                            <label>Enter New Password</label>
                            <input class="form-control" type="password">
                            <label>Confirm New Password</label>
                            <input class="form-control" type="password">
                            <br>
                            <a href="#" class="btn btn-warning">Change Password</a>
                        </div>
                    </div>

                </div>
        </section>
    </div>
    	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
    
    </body>
    </html>