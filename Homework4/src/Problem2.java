public class Problem2 {
    static class BSTNode {
        Comparable data;
        BSTNode left, right;

        public BSTNode(Comparable data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    static class BinarySearchTree {
        private BSTNode root;
        private int nodecount;

        public BinarySearchTree() {
            root = null;
            nodecount = 0;
        }

        public void insert(Comparable data) {
            BSTNode newNode = new BSTNode(data);

            if (root == null) {
                root = newNode;
                nodecount++;
                return;
            }

            BSTNode current = root;
            BSTNode parent = null;

            while (true) {
                parent = current;
                int cmp = data.compareTo(current.data);

                if (cmp < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = newNode;
                        nodecount++;
                        return;
                    }
                } else if (cmp > 0) {
                    current = current.right;
                    if (current == null) {
                        parent.right = newNode;
                        nodecount++;
                        return;
                    }
                } else {
                    // No duplicates allowed
                    return;
                }
            }
        }

        public void remove(Comparable value) {
            root = remove(root, value);
        }

        private BSTNode remove(BSTNode node, Comparable value) {
            if (node == null) return null;  // Value not found

            int cmp = value.compareTo(node.data);
            if (cmp < 0) {
                node.left = remove(node.left, value);
            } else if (cmp > 0) {
                node.right = remove(node.right, value);
            } else {
                // Node with no children
                if (node.left == null && node.right == null) {
                    System.out.println("Removing a leaf node: " + node.data);
                    return null;
                }
                // Node with one child
                if (node.left == null) {
                    System.out.println("Removing a node with one child: " + node.data);
                    return node.right;
                } else if (node.right == null) {
                    System.out.println("Removing a node with one child: " + node.data);
                    return node.left;
                }
                // Node with two children
                System.out.println("Removing a node with two children: " + node.data);
                BSTNode maxNode = findMax(node.left);
                node.data = maxNode.data;
                node.left = remove(node.left, maxNode.data);
            }
            return node;
        }

        private BSTNode findMax(BSTNode node) {
            while (node.right != null) {
                node = node.right;
            }
            return node;
        }

        public void inorderPrint() {
            inorderPrintHelper(root);
            System.out.println();
        }

        private void inorderPrintHelper(BSTNode node) {
            if (node != null) {
                inorderPrintHelper(node.left);
                System.out.print(node.data + " ");
                inorderPrintHelper(node.right);
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Inserting nodes
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.insert(10);
        bst.insert(35);
        bst.insert(65);
        bst.insert(45);  // Node to the right of 40

        System.out.println("Initial tree (inorder):");
        bst.inorderPrint();

        // Removing nodes
        bst.remove(50);
        bst.inorderPrint();

        bst.remove(10);
        bst.inorderPrint();

        bst.remove(70);
        bst.inorderPrint();
    }
}