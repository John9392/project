enum Color {
    RED, BLACK
}

class RedBlackTreeNode {
    Task task;
    RedBlackTreeNode left, right, parent;
    Color color;

    public RedBlackTreeNode(Task task) {
        this.task = task;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.color = Color.RED;
    }
}

class RedBlackTree {
    private RedBlackTreeNode root;
    private RedBlackTreeNode TNULL;

    public RedBlackTree() {
        TNULL = new RedBlackTreeNode(null);
        TNULL.color = Color.BLACK;
        root = TNULL;
    }

    // Preorder traversal
    private void preOrderHelper(RedBlackTreeNode node) {
        if (node != TNULL) {
            System.out.println(node.task);
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    // Balance the tree after deletion of a node
    private void fixDelete(RedBlackTreeNode x) {
        RedBlackTreeNode s;
        while (x != root && x.color == Color.BLACK) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    x = x.parent;
                } else {
                    if (s.right.color == Color.BLACK) {
                        s.left.color = Color.BLACK;
                        s.color = Color.RED;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    s.right.color = Color.BLACK;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == Color.RED) {
                    s.color = Color.BLACK;
                    x.parent.color = Color.RED;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == Color.BLACK && s.right.color == Color.BLACK) {
                    s.color = Color.RED;
                    x = x.parent;
                } else {
                    if (s.left.color == Color.BLACK) {
                        s.right.color = Color.BLACK;
                        s.color = Color.RED;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = Color.BLACK;
                    s.left.color = Color.BLACK;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = Color.BLACK;
    }

    private void rbTransplant(RedBlackTreeNode u, RedBlackTreeNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deleteNodeHelper(RedBlackTreeNode node, int key) {
        RedBlackTreeNode z = TNULL;
        RedBlackTreeNode x, y;
        while (node != TNULL) {
            if (node.task.getId() == key) {
                z = node;
            }

            if (node.task.getId() <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't find key in the tree");
            return;
        }

        y = z;
        Color yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbTransplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbTransplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbTransplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbTransplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == Color.BLACK) {
            fixDelete(x);
        }
    }

    // Balance the tree after insertion of a node
    private void fixInsert(RedBlackTreeNode k) {
        RedBlackTreeNode u;
        while (k.parent.color == Color.RED) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left; // uncle
                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right; // uncle

                if (u.color == Color.RED) {
                    u.color = Color.BLACK;
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = Color.BLACK;
                    k.parent.parent.color = Color.RED;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = Color.BLACK;
    }

    private void printHelper(RedBlackTreeNode root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == Color.RED ? "RED" : "BLACK";
            System.out.println(root.task + "(" + sColor + ")");
            printHelper(root.left, indent, false);
            printHelper(root.right, indent, true);
        }
    }

    public RedBlackTreeNode minimum(RedBlackTreeNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public void leftRotate(RedBlackTreeNode x) {
        RedBlackTreeNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(RedBlackTreeNode x) {
        RedBlackTreeNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(Task task) {
        RedBlackTreeNode node = new RedBlackTreeNode(task);
        node.parent = null;
        node.task = task;
        node.left = TNULL;
        node.right = TNULL;
        node.color = Color.RED;

        RedBlackTreeNode y = null;
        RedBlackTreeNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.task.getPriority() < x.task.getPriority()) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.task.getPriority() < y.task.getPriority()) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = Color.BLACK;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    public RedBlackTreeNode getRoot() {
        return this.root;
    }

    public void deleteTask(int data) {
        deleteNodeHelper(this.root, data);
    }

    public void printTree() {
        printHelper(this.root, "", true);
    }

    public Task searchTask(int id) {
        RedBlackTreeNode current = root;
        while (current != TNULL) {
            if (current.task.getId() == id) {
                return current.task;
            } else if (current.task.getId() < id) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return null;
    }

    public void listTasks() {
        inOrderHelper(this.root);
    }

    private void inOrderHelper(RedBlackTreeNode node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.println(node.task);
            inOrderHelper(node.right);
        }
    }
}