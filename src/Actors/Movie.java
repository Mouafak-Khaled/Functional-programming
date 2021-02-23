package Actors;

import java.util.List;

public class Movie {

//	Class's instances variables.
	private String name;
	private int releaseYear;
	public List<Actor> actors;

	public Movie(String name, int releaseYear,List<Actor> actors) {
		setName(name);
		setReleaseYear(releaseYear);
		setActors(actors);

	}
	public Movie() {
		
	}

	
	
	public String getName() {
		return name;
	}

	
	
	public void setName(String name) {
		this.name = name;
	}

	
	
	public int getReleaseYear() {
		return releaseYear;
	}

	
	
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	
	
	public List<Actor> getActors() {
		return actors;
	}

	
	
	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return String.format("%-5s(%d)", getName(), getReleaseYear());
	}

}
