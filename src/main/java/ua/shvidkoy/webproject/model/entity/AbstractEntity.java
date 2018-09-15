package ua.shvidkoy.webproject.model.entity;

public abstract class AbstractEntity {
	
	protected int id;
	
	public AbstractEntity(int id) {
		this.id = id;
	}

	public AbstractEntity() {}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}