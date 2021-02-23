package Actors;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {
	private static Scanner reader = new Scanner(System.in);

	
	
// this method is the answer to first question. it gives the set of movies in which two actors have co-starred in.
	public static List<Movie> sharedMovies(List<Movie> movies) {
		System.out.println("Enter the name and surname of the actor separated by comma (without a space): ");
		String nameInput = reader.nextLine();
		String first = null;
		String second = null;
		if (nameInput.contains(",")) {
			first = nameInput.substring(0, nameInput.indexOf(","));
			second = nameInput.substring(nameInput.indexOf(",") + 1);
		}

		List<Actor> actorsInput = new ArrayList<>();

		final String firstName = first;
		final String secondName = second;

		Actor firstActor = testing.cast.stream().filter(actor -> actor.getFullName().equals(firstName)).findFirst()
				.get();
		Actor secondActor = testing.cast.stream().filter(actor -> actor.getFullName().equals(secondName)).findFirst()
				.get();

		actorsInput.add(firstActor);
		actorsInput.add(secondActor);

		Predicate<Movie> byInput = movie -> (movie.getActors().containsAll(actorsInput));

		List<Movie> sharedMovies = movies.stream().filter(byInput).sorted(Comparator.comparing(Movie::getName))
				.collect(Collectors.toList());

		if (sharedMovies.size() == 1) {

			System.out.printf("‘%s’ and ‘%s’ had co-starred in one movie:%n", firstActor.getFullName(),
					secondActor.getFullName());

			return sharedMovies;

		} else {

			System.out.printf("‘%s’ and ‘%s’ had co-starred in more than one movie: %n", firstActor.getFullName(),
					secondActor.getFullName());

			return sharedMovies;

		}

	}

// this method gives the answer to the second quetion. It sorts the movies according to the input of the user.
	public static List<Movie> sortMovies(List<Movie> movies) {

		List<Movie> sortedList = new ArrayList<Movie>();
		System.out.print("Enter the first character and ordering type: ");

		String input = reader.nextLine();
		String searchCharcter = Character.toString(input.charAt(0));

		Predicate<Movie> byChar = movie -> movie.getName().startsWith(searchCharcter);

		if (input.substring(2).equals("ascending") || input.substring(2).equals("ASCENDING")) {

			sortedList = movies.stream().filter(byChar).sorted(Comparator.comparing(Movie::getName))
					.collect(Collectors.toList());
			return sortedList;
		} else {

			sortedList = movies.stream().filter(byChar).sorted(Comparator.comparing(Movie::getName).reversed())
					.collect(Collectors.toList());
			return sortedList;
		}

	}

// this method gives all the movies of every actor of a group of actors who have the same first name specified by the user.
	public static void searhForMovies() {

		System.out.print("Search movies by first name, please enter the actor’s first name: ");
		String input = reader.nextLine();
		System.out.printf("Movies played by actors with first name \"%s\": %n", input);

		Predicate<Actor> byName = actor -> input.equals(actor.getName());
		List<Actor> actorsSameName = testing.cast.stream().filter(byName).sorted(Comparator.comparing(Actor::getLastName)).collect(Collectors.toList());

		System.out.printf("Actor’s Name/Surname    Movie(s) Title(s)%n");
		System.out.println("------------------------------    ---------------------");

		actorsSameName.stream()
				.forEach(actor -> System.out.printf("%-34s%s%n", actor.getFullName(), testing.hashCast.get(actor)));
		System.out.println();

	}

//  this method search for movies using their release date that is specified by the user.
	public static void searchByYear(List<Movie> movies) {
		System.out.print("Search movies by release date. ");
		System.out.print(
				"Please enter the start year and end year of the period you want to search for separated by a space: ");

		List<Movie> searchedList = new ArrayList<Movie>();
		String input = reader.nextLine();
		String[] splitor = input.split(" ");

		int lowerLimit = Integer.parseInt(splitor[0]);
		int upperLimit = Integer.parseInt(splitor[1]);
		
		while (upperLimit < lowerLimit) {
			if (upperLimit >= lowerLimit) {
				break;
			}
			System.out.println("Not valid input, the upper limit must be bigger than the lower limit. Please try again");
			System.out.print("Search movies by release date. ");
			System.out.print(
					"Please enter the start year and end year of the period you want to search for separated by a space: ");
			
			input = reader.nextLine();
			splitor = input.split(" ");

			lowerLimit = Integer.parseInt(splitor[0]);
			upperLimit = Integer.parseInt(splitor[1]);

		}
		final int startYear = lowerLimit;
		final int endYear = upperLimit;

		Predicate<Movie> byReleaseYear = movie -> movie.getReleaseYear() >= startYear
				&& movie.getReleaseYear() <= endYear;

		searchedList = movies.stream().filter(byReleaseYear).sorted(Comparator.comparing(Movie::getReleaseYear))
				.collect(Collectors.toList());

		System.out.printf("Movies released between %d – %d: %n", startYear, endYear);

		searchedList.stream().forEach(x -> System.out.printf("%-35s%d%n", x.getName(), x.getReleaseYear()));
		System.out.println();

	}

	
	
	
	
//	This method gives the most active actor among the casts.
//	it provides information such as the number of movies he acted in and the years in which he was the most active.
	public static void getActiveActor() {

		HashMap<Integer, Integer> activeYear = new HashMap<>();
		int maximumNumberOfMovies = testing.hashCast.values().stream().mapToInt(List<Movie>::size).max().getAsInt();

		Predicate<Actor> mostActive = actor -> testing.hashCast.get(actor).size() == maximumNumberOfMovies;

		Actor activeActor = testing.hashCast.keySet().stream().filter(mostActive).findAny().get();

		testing.hashCast.get(activeActor).stream().mapToInt(Movie::getReleaseYear).forEach(x -> {
			if (activeYear.keySet().contains(x)) {
				int a = activeYear.get(x);
				activeYear.put(x, a + 1);
			} else {
				activeYear.put(x, 1);
			}

		});

//		integer maximum represents the maximum number of movies in the most active year of the active actor.
		int maximum = activeYear.values().stream().reduce((x, y) -> Math.max(x, y)).get();

		System.out.printf("The actor with the maximum number of movies played in is %s who played in %d movies.%n%n",
				activeActor.getFullName(), maximumNumberOfMovies);

		activeYear.keySet().stream().filter(x -> activeYear.get(x) == maximum)
				.forEach(value -> System.out.print(value + " "));
		System.out.printf("were his/her most productive year(s) with %d movies in each.", maximum);

	}
}
