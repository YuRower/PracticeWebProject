<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>js</title>

</head>
<body>
	<h2 style="color: red;">
		<b><i>Bouncing ball animation in canvas</i></b>
	</h2>
	<br>
	<p>Color:</p>
	<select id="color_select">
		<option value="#ff0000">Red</option>
		<option value="#00ff00">Green</option>
		<option value="#0000ff">Blue</option>
	</select>

	<form>
		<p>
			<input type="radio" id="circle" name="figure" value="circle" checked onclick="check(this);">
			Circle <br> <input type="radio" id="rectangle" name="figure" value="rectangle"
				onclick="check(this);">Rectangle <br>
		</p>
	</form>
	<p>
		Figure size <input type="range" id="fig_size" min="1" max="5"
			value="3">
	</p>
	<p>
		Velocity: <input type="range" id="fig_speed" min="1" max="10"
			value="5">
	</p>
	<canvas id="my_canvas" width="400" height="300"
		style="border: 5px solid blue;"></canvas>
	<script src="js/animation.js"></script>


</body>
</html>