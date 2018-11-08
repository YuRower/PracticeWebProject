var canvas = document.getElementById('my_canvas');
var ctx = canvas.getContext('2d');

var start_position = {
	x : 20,
	y : 20
};
var rect = {
	x : 0,
	y : 75,
	width : 100,
	height : 50,
	borderWidth : 5
};
var velocity = 3, corner = 50, radius = 20;
var ball = {
	x : start_position.x,
	y : start_position.y
};

function initCanvasCircle() {
	ctx.beginPath();// launch new path of figure
	ctx.fillStyle = window.sessionStorage.getItem("key3");
	ctx.arc(ball.x, ball.y, radius * window.sessionStorage.getItem("key1"), 0,
			Math.PI * 2, false);// draw curve
    context.rect(rect.x, rect.y, rect.width, rect.height);
	ctx.fill();// fills the current drawing (path)
	ctx.closePath();// close the path of figure
	// animFrame();
}
var moveX = Math.cos(Math.PI / 180 * corner) * velocity;
var moveY = Math.sin(Math.PI / 180 * corner) * velocity;

function drawCanv() {
	ctx.clearRect(0, 0, 400, 300);
	if (ball.x > canvas.width - radius || ball.x < radius)
		moveX = -moveX;
	if (ball.y > canvas.height - radius || ball.y < radius)
		moveY = -moveY;

	ball.x += moveX * window.sessionStorage.getItem("key2");
	;
	ball.y += moveY * window.sessionStorage.getItem("key2");

	initCanvasCircle();
}

function drawCanvRect(rect, canvas, context, startTime) {
    var time = (new Date()).getTime() - startTime;
    var linearSpeed = 100;
    var newX = linearSpeed * time / 1000;

    if (newX < canvas.width - rect.width - rect.borderWidth / 2) {
        rect.x = newX;
    }
    context.clearRect(0, 0, canvas.width, canvas.height);
    drawRectangle(rect, context);
}

function check(radio) {
	if (document.getElementById('circle').checked) {

		
	}
	if (document.getElementById('rectangle').checked) {
		/*drawCanvRect(rect, canvas, ctx, new Date().getTime());
		setInterval(drawCanvRect(rect, canvas, ctx, new Date().getTime()), 1000 / 60);*/

	}
}
var speed = document.getElementById('fig_speed');
speed.onchange = function() {
	window.sessionStorage.setItem("key2", speed.value);
	// ocation.reload();
}
var size = document.getElementById('fig_size');
size.onchange = function() {
	window.sessionStorage.setItem("key1", size.value);
}

var select = document.getElementById('color_select');
select.onchange = function() {
	window.sessionStorage.setItem("key3", select.value);

	/*
	 * for (var i = 0; i < select.options.length; i++) { if (i ==
	 * select.selectedIndex) { drawCanv(select.options[i].value); } }
	 */
}
setInterval(drawCanv, 1000 / 60);

