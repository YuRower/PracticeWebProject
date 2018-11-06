package ua.shvidkoy.webproject.model.entity;


public class Media extends AbstractEntity {
	@Override
	public String toString() {
		return "Photo [name=" + name + "]";
	}

	private String name;
    private String type;
	
	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type;
	}

	Media(int id) {
		super.id = id;
	}

	public Media(int id, String name) {
		this(id);
		this.name = name;
	}

	public Media() {

	}

	public Media(String photoName) {
		this.name = photoName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
