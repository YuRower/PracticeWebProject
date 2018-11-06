<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/header.jspf"%>
<head>
<title>Web Practice</title>
</head>
<body>


	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<jsp:useBean id="_media" scope="request"
		class="ua.shvidkoy.webproject.model.entity.Media" />
	<table id="myTable">
		<tr>
			<td class="content center">
				<h3>Media List</h3>
				<table class="center">


					<c:forEach var="_media" items="${media}">
						<c:choose>

							<c:when test="${ _media.type eq 'video' }">
								<td>
								<tr>
									<c:out value="Video" />
								</tr>
								<br />

								<video controls width="720" height="480">
									<source src="video/${_media.name}" />
									<source src="video/vsvideo.ogg" />
									<source src="video/vsvideo.webm" />
									Your browser does not support HTML5 video.
								</video>


								<p>
									<b>Note:</b> The autoplay attribute does not work on some
									mobile devices.
								</p>
								<br />
								</td>
							</c:when>
							<c:when test="${ _media.type eq 'audio' }">
								<td>
								<tr>
									<c:out value="Audio" />
								</tr>
								<br />
								<audio controls>
									<source src="audio/${_media.name}">
									<source src="audio/Vivaldi.ogg">
									Your browser does not support HTML5 audio .
								</audio>

								<p>
									<b>Note:</b> The autoplay attribute does not work on some
									mobile devices.
								</p>
								<br />
								</td>
							</c:when>
							<c:when test="${ _media.type eq 'img' }">
								<br />
							</c:when>
							<c:otherwise>
								<c:out value="not such media" />
							</c:otherwise>
						</c:choose>


					</c:forEach>
					<tr>
						<c:out value="IFrame" />
					</tr>
					<td><iframe width="720" height="480"
							src="https://www.youtube.com/embed/BtkaR_YLV9M"> </iframe></td>


				</table>
		</tr>

	</table>

</body>
</html>