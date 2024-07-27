import java.util.Scanner;

public class MovieApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static MovieDatabase database = new MovieDatabase();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    addMovie();
                    break;
                case 2:
                    deleteMovie();
                    break;
                case 3:
                    searchMovie();
                    break;
                case 4:
                    listMovies();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("--- Movie Database ---");
        System.out.println("1. Add Movie");
        System.out.println("2. Delete Movie");
        System.out.println("3. Search Movie");
        System.out.println("4. List Movies");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addMovie() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter director: ");
        String director = scanner.nextLine();
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        System.out.print("Enter rating: ");
        double rating = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        database.addMovie(title, director, year, rating);
        System.out.println("Movie added successfully.");
    }

    private static void deleteMovie() {
        System.out.print("Enter title of the movie to delete: ");
        String title = scanner.nextLine();
        database.deleteMovie(title);
        System.out.println("Movie deleted successfully.");
    }

    private static void searchMovie() {
        System.out.print("Enter title of the movie to search: ");
        String title = scanner.nextLine();
        Movie movie = database.searchMovie(title);
        if (movie != null) {
            System.out.println("Movie found: " + movie);
        } else {
            System.out.println("Movie not found.");
        }
    }

    private static void listMovies() {
        System.out.println("--- Movie List ---");
        database.listMovies();
    }
}