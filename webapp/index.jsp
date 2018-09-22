<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html lang="en">
<head>
<title></title>
<meta charset="UTF-8">
</head>
<body>
	<jsp:forward page="/front_controller" >
	  <jsp:param name="command" value="init_user_list" ></jsp:param>
	</jsp:forward>
</body>
</html>