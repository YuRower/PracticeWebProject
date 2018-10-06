<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Bootstrap Confirmation 3 Plugin Demos</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">

<link href="../style/bootstrap.min.css" rel="stylesheet">
</head>
<body>

	<div class="container" style="margin-top: 150px;">
		<div class="row">
			<div class="col-md-12">
				<h2>Default options with data toggle</h2>
				<button class="btn btn-danger" data-toggle="confirmation">Click
					me</button>
			</div>
		</div>


	</div>

	<script src="../js/jquery-1.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-confirmation.js"></script>

	<script>
		$(function() {
			$('body').confirmation({
				selector : '[data-toggle="confirmation"]'
			});

		});
	</script>
</html>