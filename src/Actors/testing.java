/* Name: Mouafak Alsaid Hasan.
 * ID Numer: 0066939.
 */


package Actors;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class testing {

	public static void main(String[] args) {

		try (Scanner reader = new Scanner(Paths.get("./src/movies_sample.txt"));) {

			while (reader.hasNextLine()) {
				String line = reader.nextLine();

				movie = new Movie(extractTheMovieName(line), extractReleaseYear(line), extractTheCastTeam(line));
				movieList.add(movie);

				movie.actors.stream().forEach(actor -> {

					if (hashCast.keySet().contains(actor)) {
						hashCast.get(actor).add(movie);
					} else {

						hashCast.put(actor, new ArrayList<Movie>());
						hashCast.get(actor).add(movie);
					}
				});

			}

		} catch (NoSuchFileException ex) {
			System.out.println("The file could not be found.");

		} catch (IOException e) {

			System.err.println(e.getMessage());
		}
		
// calling the five methods from class Utils.
		
//	Utils.sharedMovies(movieList).stream().forEach(System.out::println);
//	System.out.println();
//	Utils.sortMovies(movieList).stream().forEach(System.out::println);
//	System.out.println();
	Utils.searhForMovies();
	Utils.searchByYear(movieList);
	Utils.getActiveActor();
	



	}

	
	
	
//  getting the movie name using the method substring from every line in the file.
	public static String extractTheMovieName(String line) {
		String name = line.substring(0, line.indexOf("("));
		return name;
	}

	
	
	
//	getting the release year of the movies using matcher and pattern from every line in the file.
	public static int extractReleaseYear(String line) {
		Matcher result = forReleaseDate.matcher(line);
		int releaseYear = 0;
		while (result.find()) {
			releaseYear = Integer.parseInt(result.group());
		}
		return releaseYear;
	}

	
	
	
//	getting the cast team of every movie from every line in the file.
	public static List<Actor> extractTheCastTeam(String line) {
		List<Actor> actors = new ArrayList<Actor>();
		String[] casts = line.substring(line.indexOf("/") + 1).split("/");

		String[] newCast = null;
		for (String name : casts) {
			if (name.contains(",")) {
				newCast = name.split(",\\s");
				actors.add(new Actor(newCast[1], newCast[0]));
				cast.addAll(actors);
			} else {
				actors.add(new Actor(name, ""));
			}

		}


		return actors;

	}
	
	
	
	

	

	
	
	
// instance variables and objects.
	public static Pattern forReleaseDate = Pattern.compile("[0-9]{4}");
	public static Set<Actor> cast = new HashSet<Actor>();
	public static Movie movie = new Movie();
	public static List<Movie> movieList = new ArrayList<>();
	public static HashMap<Actor, List<Movie>> hashCast = new HashMap<>();
	public static Scanner reader;

}
