var canvas = document.getElementById('my_canvas');
var ctx = canvas.getContext('2d');
var myVar;

var start_position = {
	x : 20,
	y : 20
};
var velocity = 3, corner = 50, radius = 20;
var ball = {
	x : start_position.x,
	y : start_position.y
};
var rectangle = {
		x : start_position.x,
		y : start_position.y
	};

var moveX = Math.cos(Math.PI / 180 * corner) * velocity;
var moveY = Math.sin(Math.PI / 180 * corner) * velocity;

function initCanvasCircle() {
	ctx.beginPath();// launch new path of figure
	ctx.fillStyle = window.sessionStorage.getItem("key3");
	ctx.arc(ball.x, ball.y, radius * window.sessionStorage.getItem("key1"), 0,
			Math.PI * 2, false);// draw curve
	// context.rect(rect.x, rect.y, rect.width, rect.height);
	ctx.fill();// fills the current drawing (path)
	ctx.closePath();// close the path of figure
}

function drawCanv() {
	ctx.clearRect(0, 0, 400, 300);
	if (ball.x > canvas.width - radius || ball.x < radius)
		moveX = -moveX;
	if (ball.y > canvas.height - radius || ball.y < radius)
		moveY = -moveY;

	ball.x += moveX * window.sessionStorage.getItem("key2");

	ball.y += moveY * window.sessionStorage.getItem("key2");

	initCanvasCircle();
}


var dx = 2;
var dy = 4;
function rect(x, y, w, h) {
	ctx.beginPath();
	ctx.fillStyle = window.sessionStorage.getItem("key3");
	ctx.rect(x, y, w, h);
	ctx.closePath();
	ctx.fill();
}
function drawRect() {
	ctx.clearRect(0, 0, 400, 300);

	if (rectangle.x + dx > canvas.width || rectangle.x + dx < 0)
		dx = -dx;
	if (rectangle.y + dy > canvas.height || rectangle.y + dy < 0)
		dy = -dy;

	rectangle.x += dx * window.sessionStorage.getItem("key2");
	rectangle.y += dy* window.sessionStorage.getItem("key2") ;
	rect(rectangle.x, rectangle.y, 20*window.sessionStorage.getItem("key1"), 20*window.sessionStorage.getItem("key1"));

}

function check(radio) {
	if (document.getElementById('circle').checked) {
		clearInterval(myVar);
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		myVar = setInterval(drawCanv, 1000 / 60);
	}
	
	if (document.getElementById('rectangle').checked) {
		clearInterval(myVar);
		ctx.clearRect(0, 0, canvas.width, canvas.height);
		myVar = setInterval(drawRect, 1000 / 60);
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

// setInterval(drawCanv, 1000 / 60);

