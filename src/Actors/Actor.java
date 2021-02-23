package Actors;



public class Actor {

//	Class's instances variables.
	private String name;
	private String lastName;

	
	
	
	public Actor(String name, String lastName) {
		setName(name);
		setLastName(lastName);
	}

	public Actor() {

	}
	
//	Class's getters an setters.

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return String.format("%s %s", getName(), getLastName());

	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Actor)
			return this.getFullName().equals(((Actor) o).getFullName());
		return false;
	}

	@Override
	public int hashCode() {

		return getFullName().hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s", getFullName());
	}

}
