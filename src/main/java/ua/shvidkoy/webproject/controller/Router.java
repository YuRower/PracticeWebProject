package ua.shvidkoy.webproject.controller;

public class Router {
	private String path;
	private RouteType route;

	public enum RouteType {
		FORWARD, REDIRECT
	}

	public Router(RouteType processorMode, String path) {
		this.route = processorMode;
		this.path = path;
	}

	public void setPath(String pagePath) {
		this.path = pagePath;
	}

	public void setRoute(RouteType route) {
		this.route = route;
	}

	public String getPath() {
		return path;
	}

	public RouteType getRoute() {
		return route;
	}
}