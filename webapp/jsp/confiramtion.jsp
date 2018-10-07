
<html>


<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Bootstrap Confirmation 3 Plugin Demos</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width">

<link href="../style/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<%@ include file="/WEB-INF/jspf/header.jspf"%>
	<div class="container" style="margin-top: 150px;">
		<div class="row">
			<div class="col-md-12">
				<h2>Default options with data toggle</h2>
				<button type="submit" class="demo"
										data-toggle="confirmation" 
										style="color: White; background-color: #d9534f; width: 50%;">
										Delete</button>
			</div>
		</div>

	</div>

	<script src="../js/jquery-1.js"></script>
	<script src="../js/bootstrap.js"></script>
	<script src="../js/bootstrap-confirmation.js"></script>

	<script>
		$(function() {
			$('.demo').confirmation({
				  onConfirm: function() {
					  alert("hi");
				  }
					  
					 
					});
		});
	</script>
</html>