

/**
 * Created by Acer Customer on 4/18/2017.
 */
public class BST {
    private Node root;
    private int size;

    public BST(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isRoot(Node node) {
        if(node == this.root) {
            return true;
        }

        return false;
    }



    public void insert(Node newNode, Node parent) {
        if(search(newNode.getData()) != null ) {
            System.out.println(newNode.getData() + " already exists, ignore.");
        }
        if(newNode.getData() <= parent.getData()){
            if(parent.getLeft() == null){
                parent.setLeft(newNode); // inserts new node
                parent.getLeft().setParent(parent); // backreferences the parent node
                System.out.println("newNode set as left child under " + parent.getData());
            }
            else{
                this.insert(newNode, parent.getLeft());
            }
        }
        else{
            if(parent.getRight() == null){
                parent.setRight(newNode); // inserts new node to the right
                parent.getRight().setParent(parent); // back references the parent node
                System.out.println("newNode set as right child under " + parent.getData());
            }
            else{
                this.insert(newNode, parent.getRight());
            }
        }

    }

    void delete(int data) {
        Node toBeDeleted = search(data);

        if(toBeDeleted == null){
            System.out.println(data + " not found");
            printInorder();
        }
        else {
            // leaf node removal
            if (toBeDeleted.getLeft() == null && toBeDeleted.getRight() == null) {
                if (toBeDeleted.getData() <= toBeDeleted.getParent().getData()) {
                    toBeDeleted.getParent().setLeft(null);
                }
                if (toBeDeleted.getData() > toBeDeleted.getParent().getData()) {
                    toBeDeleted.getParent().setRight(null);
                }
            // single child node removal
            } else if (toBeDeleted.getLeft() != null ^ toBeDeleted.getRight() != null) {
                // left child
                if (toBeDeleted.getData() < toBeDeleted.getParent().getData()) {
                    if (toBeDeleted.getLeft() != null) {
                        toBeDeleted.getParent().setLeft(toBeDeleted.getLeft());
                    } else {
                        toBeDeleted.getParent().setLeft(toBeDeleted.getRight());
                    }
                }
                //right child
                if (toBeDeleted.getData() > toBeDeleted.getParent().getData()) {
                    if (toBeDeleted.getLeft() != null) {
                        toBeDeleted.getParent().setRight(toBeDeleted.getLeft());
                    } else {
                        toBeDeleted.getParent().setRight(toBeDeleted.getRight());
                    }
                }
            }
            // two child node removal
            else {
                Node maxLeftTreeNode = toBeDeleted.getLeft();

                while (maxLeftTreeNode.getRight() != null) {
                    maxLeftTreeNode = maxLeftTreeNode.getRight();
                }

                //left child
                if (toBeDeleted.getData() < toBeDeleted.getParent().getData()) {
                    maxLeftTreeNode.setRight(toBeDeleted.getRight());
                    toBeDeleted.getParent().setLeft(maxLeftTreeNode);

                }
                //right child
                if (toBeDeleted.getData() > toBeDeleted.getParent().getData()) {
                    maxLeftTreeNode.setRight(toBeDeleted.getRight());
                    toBeDeleted.getParent().setRight(maxLeftTreeNode);
                }
            }
        }
    }

    void printInorder() {
        printInorderRecursive(this.root);
    }

    void printInorderRecursive(Node currentNode) {
        if(currentNode.getLeft() != null){
            printInorderRecursive(currentNode.getLeft());
        }

        System.out.print(currentNode.getData() + " ");

        if(currentNode.getRight() != null){
            printInorderRecursive(currentNode.getRight());
        }
    }

    void printPostorder() {
        printPostorderRecursively(this.root);
    }

    void printPostorderRecursively(Node currentNode) {
        System.out.print(currentNode.getData() + " ");

        if(currentNode.getLeft() != null) {
            printPostorderRecursively(currentNode.getLeft());
        }

        if(currentNode.getRight() != null) {
            printPostorderRecursively(currentNode.getRight());
        }
    }

    void printPreorder() {
        printPreorderRecursively(this.root);
    }

    void printPreorderRecursively(Node currentNode) {
        if(currentNode.getLeft() != null) {
            printPostorderRecursively(currentNode.getLeft());
        }

        if(currentNode.getRight() != null) {
            printPostorderRecursively(currentNode.getRight());
        }

        System.out.print(currentNode.getData() + " ");
    }

    Node search(int value) {
        return searchRecursively(value, this.root);
    }

    Node searchRecursively(int value, Node currentNode) {
        if(value == currentNode.getData()){
            return currentNode;
        }
        else if(currentNode.getLeft() == null && currentNode.getRight() == null) {
            return null;
        }
        else if(value < currentNode.getData()){
            if(currentNode.getLeft() != null){
                return searchRecursively(value, currentNode.getLeft());
            }
            else {
                return null;
            }
        }
        else if(value > currentNode.getData()) {
            if(currentNode.getRight() != null) {
                return searchRecursively(value,currentNode.getRight());
            }
            else {
                return null;
            }
        }

        return null;
    }


}
