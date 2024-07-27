public class MovieDatabase {
    private AVLTree movieTree;

    public MovieDatabase() {
        movieTree = new AVLTree();
    }

    public void addMovie(String title, String director, int year, double rating) {
        Movie movie = new Movie(title, director, year, rating);
        movieTree.insert(movie);
    }

    public void deleteMovie(String title) {
        movieTree.delete(title);
    }

    public Movie searchMovie(String title) {
        return movieTree.search(title);
    }

    public void listMovies() {
        movieTree.listMovies();
    }
}