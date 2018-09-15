package ua.shvidkoy.webproject.model.entity;

public class Photo extends AbstractEntity {
	private String name;

	Photo(int id) {
		super.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
