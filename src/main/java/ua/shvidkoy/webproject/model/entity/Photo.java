package ua.shvidkoy.webproject.model.entity;

public class Photo extends AbstractEntity {
	@Override
	public String toString() {
		return "Photo [name=" + name + "]";
	}

	private String name;

	Photo(int id) {
		super.id = id;
	}

	public Photo(int id, String name) {
		this(id);
		this.name = name;
	}

	public Photo() {

	}

	public Photo(String photoName) {
		this.name = photoName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
