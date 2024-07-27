class AVLTreeNode {
    Movie movie;
    AVLTreeNode left;
    AVLTreeNode right;
    int height;

    public AVLTreeNode(Movie movie) {
        this.movie = movie;
        this.height = 1;
    }
}

class AVLTree {
    private AVLTreeNode root;

    // Helper functions for AVL Tree rotations
    private AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;
        
        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(AVLTreeNode N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    private int height(AVLTreeNode N) {
        if (N == null)
            return 0;
        return N.height;
    }

    // Method to insert a movie into the AVL tree
    public void insert(Movie movie) {
        root = insert(root, movie);
    }

    private AVLTreeNode insert(AVLTreeNode node, Movie movie) {
        if (node == null)
            return new AVLTreeNode(movie);
        
        if (movie.getTitle().compareTo(node.movie.getTitle()) < 0)
            node.left = insert(node.left, movie);
        else if (movie.getTitle().compareTo(node.movie.getTitle()) > 0)
            node.right = insert(node.right, movie);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && movie.getTitle().compareTo(node.left.movie.getTitle()) < 0)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && movie.getTitle().compareTo(node.right.movie.getTitle()) > 0)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && movie.getTitle().compareTo(node.left.movie.getTitle()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && movie.getTitle().compareTo(node.right.movie.getTitle()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Method to delete a movie by title
    public void delete(String title) {
        root = deleteNode(root, title);
    }

    private AVLTreeNode deleteNode(AVLTreeNode root, String title) {
        if (root == null)
            return root;

        if (title.compareTo(root.movie.getTitle()) < 0)
            root.left = deleteNode(root.left, title);
        else if (title.compareTo(root.movie.getTitle()) > 0)
            root.right = deleteNode(root.right, title);
        else {
            if ((root.left == null) || (root.right == null)) {
                AVLTreeNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                AVLTreeNode temp = minValueNode(root.right);
                root.movie = temp.movie;
                root.right = deleteNode(root.right, temp.movie.getTitle());
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        // Balance the tree
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    private AVLTreeNode minValueNode(AVLTreeNode node) {
        AVLTreeNode current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }

    // Method to search for a movie by title
    public Movie search(String title) {
        return searchNode(root, title);
    }

    private Movie searchNode(AVLTreeNode node, String title) {
        if (node == null || node.movie.getTitle().equals(title))
            return node != null ? node.movie : null;

        if (title.compareTo(node.movie.getTitle()) < 0)
            return searchNode(node.left, title);

        return searchNode(node.right, title);
    }

    // Method to list movies in alphabetical order
    public void listMovies() {
        inOrder(root);
    }

    private void inOrder(AVLTreeNode root) {
        if (root != null) {
            inOrder(root.left);
            System.out.println(root.movie);
            inOrder(root.right);
        }
    }
}