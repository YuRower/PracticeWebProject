
<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table id="main container">
		<tr>
			<td class="content center">
			<c:out value = "${action} " />
			</td>
			</tr>
	</table>
	
	<a href = "front_controller?command=init_user_list" >Back </a>

</body>
<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</html>