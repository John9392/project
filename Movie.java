public class Movie {
    private String title;
    private String director;
    private int year;
    private double rating;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return title + " (" + year + ") directed by " + director + " - Rating: " + rating;
    }
}