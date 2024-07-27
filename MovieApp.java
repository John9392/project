import java.util.Scanner;

// Movie class with its properties
class Movie {
    String title;
    String director;
    int year;
    double rating;

    public Movie(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return title + " (Director: " + director + ", Year: " + year + ", Rating: " + rating + ")";
    }
}

// Node class for the BST
class Node {
    Movie movie;
    Node left, right;

    public Node(Movie movie) {
        this.movie = movie;
        left = right = null;
    }
}

// Binary Search Tree class
class BST {
    private Node root;

    public BST() {
        root = null;
    }

    public void insert(Movie movie) {
        root = insertRec(root, movie);
    }

    private Node insertRec(Node root, Movie movie) {
        if (root == null) {
            root = new Node(movie);
            return root;
        }
        if (movie.title.compareTo(root.movie.title) < 0) {
            root.left = insertRec(root.left, movie);
        } else if (movie.title.compareTo(root.movie.title) > 0) {
            root.right = insertRec(root.right, movie);
        }
        return root;
    }

    public void delete(String title) {
        root = deleteRec(root, title);
    }

    private Node deleteRec(Node root, String title) {
        if (root == null) return root;

        if (title.compareTo(root.movie.title) < 0)
            root.left = deleteRec(root.left, title);
        else if (title.compareTo(root.movie.title) > 0)
            root.right = deleteRec(root.right, title);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.movie = minValue(root.right);
            root.right = deleteRec(root.right, root.movie.title);
        }
        return root;
    }

    private Movie minValue(Node root) {
        Movie minv = root.movie;
        while (root.left != null) {
            minv = root.left.movie;
            root = root.left;
        }
        return minv;
    }

    public Movie search(String title) {
        return searchRec(root, title);
    }

    private Movie searchRec(Node root, String title) {
        if (root == null || root.movie.title.equals(title))
            return root != null ? root.movie : null;
        
        if (root.movie.title.compareTo(title) > 0)
            return searchRec(root.left, title);

        return searchRec(root.right, title);
    }

    public void listMovies() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.println(root.movie);
            inOrderRec(root.right);
        }
    }
}

// MovieDatabase class using the BST
class MovieDatabase {
    private BST bst;

    public MovieDatabase() {
        bst = new BST();
    }

    public void addMovie(Movie movie) {
        bst.insert(movie);
    }

    public void deleteMovie(String title) {
        bst.delete(title);
    }

    public Movie searchMovie(String title) {
        return bst.search(title);
    }

    public void listMovies() {
        bst.listMovies();
    }
}

// Main application with CLI for user interaction
public class MovieApp {
    public static void main(String[] args) {
        MovieDatabase db = new MovieDatabase();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("1. Add Movie");
            System.out.println("2. Delete Movie");
            System.out.println("3. Search Movie");
            System.out.println("4. List Movies");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter Year: ");
                    int year = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Rating: ");
                    double rating = Double.parseDouble(scanner.nextLine());
                    db.addMovie(new Movie(title, director, year, rating));
                    break;
                case 2:
                    System.out.print("Enter Title to Delete: ");
                    String delTitle = scanner.nextLine();
                    db.deleteMovie(delTitle);
                    break;
                case 3:
                    System.out.print("Enter Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    Movie movie = db.searchMovie(searchTitle);
                    if (movie != null) {
                        System.out.println("Found: " + movie);
                    } else {
                        System.out.println("Movie not found");
                    }
                    break;
                case 4:
                    System.out.println("Movies in database:");
                    db.listMovies();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
        scanner.close();
    }
}