class BSTNode {
    Comparable data;
    BSTNode left, right;

    public BSTNode(Comparable data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class BinarySearchTree {
    private BSTNode root;
    private int nodecount;

    public BinarySearchTree() {
        root = null;
        nodecount = 0;
    }

    // Insert a node iteratively
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
                // If the data is already present, we do not insert duplicates
                return;
            }
        }
    }


    public void inorderPrint() {
        inorderPrintHelper(root);
    }

    private void inorderPrintHelper(BSTNode node) {
        if (node != null) {
            inorderPrintHelper(node.left);
            System.out.print(node.data + " ");
            inorderPrintHelper(node.right);
        }
    }
}


public class Problem1 {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        //test
        bst.insert(40);
        bst.insert(25);
        bst.insert(10);
        bst.insert(32);
        bst.insert(50);
        bst.insert(45);
        bst.insert(60);


        bst.inorderPrint();
    }
}
