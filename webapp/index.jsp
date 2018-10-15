<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<title></title>
<meta charset="UTF-8">
</head>
<body>
	<c:set var="title" value="Login" scope ="session" />
	<jsp:forward page="/front_controller">
		<jsp:param name="command" value="init_user_list"></jsp:param>
	</jsp:forward>
</body>
</html>