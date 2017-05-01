

/**
 * Created by Acer Customer on 4/18/2017.
 */
public class BST {
    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public BST(Node root) {
        this.root = root;
        this.size = 1;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getSize(){return size;}

    /*
    increments the size variable of tree by 1
     */
    public void incrementSize(){this.size++;}

    /*
    decrements the size variable of tree by 1
     */
    public void decrementSize(){this.size--;}

    /*
    checks if a given node is the root of this tree structure
    @param node: the node being inspected
    @return: true if node is the root, false otherwise
     */
    public boolean isRoot(Node node) {
        if (node == this.root) {
            return true;
        }

        return false;
    }

    /*
    launches the insertRecursively function
     */
    public void insert(Node newNode){
        insertRecursively(newNode, this.root);
    }

    /*
    inserts a node into this tree recursively while following the rules of a binary search tree
    @param newNode: the node being inserted
    @param parent: the node being passed recursively and inspected for an open child node for newNode
     */
    private void insertRecursively(Node newNode, Node parent) {
        if (getRoot() == null) {
            setRoot(newNode);
            incrementSize();
        } else if (search(newNode.getData()) != null) {
            System.out.println(newNode.getData() + " already exists, ignore.");
            return;
        } else if (newNode.getData() <= parent.getData()) {
            if (parent.getLeft() == null) {
                parent.setLeft(newNode); // inserts new node
                parent.getLeft().setParent(parent); // backreferences the parent node
//                System.out.println("newNode set as left child under " + parent.getData());
                incrementSize();
            } else {
                this.insertRecursively(newNode, parent.getLeft());
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(newNode); // inserts new node to the right
                parent.getRight().setParent(parent); // back references the parent node
//                System.out.println("newNode set as right child under " + parent.getData());
                incrementSize();
            } else {
                this.insertRecursively(newNode, parent.getRight());
            }
        }
    }

    /*
    launches the deleteRecursively function
     */
    public Node delete(int data) {
        return deleteRecursively(data, this.root);
    }

    /*
    deletes a node containing the argument data while preserving the properties of a binary search tree
    @param data: the data being removed from the tree
    @param node: the current node in the recursive stack being inspected
    @return: deleted node
     */
    private Node deleteRecursively(int data, Node node) {
        // node not located in tree
        if (node == null) {
            System.out.println("Node " + data + " not found");
            return null;  // attempted to traverse into empty node
        }
        // function recursively searching for data
        if (data < node.getData()) {
            node.setLeft(deleteRecursively(data, node.getLeft()));
        } else if (data > node.getData()) {
            node.setRight(deleteRecursively(data, node.getRight()));
        }
        // node found
        else {
            Node temp = null;

            // leaf node case
            if (node.getRight() == null && node.getLeft() == null) {
                temp = null;
            }
            // single child case
            else if (node.getLeft() == null ^ node.getRight() == null) {
                if (node.getRight() != null) {
                    temp = node.getRight();
                } else if (node.getLeft() != null) {
                    temp = node.getLeft();
                }
            }

            // two child case
            else {
                temp = getMaxNode(node.getLeft());

                // handles a special case in which the max node has a left child
                if(temp.getLeft() != null) {
                    System.out.println("temp: " + temp.getData() + "\ntemp.getParent: " + temp.getParent().getData());
                    temp.getParent().setRight(temp.getLeft());
                    System.out.println("Parent right child: " + temp.getParent().getRight().getData());

                }
                else {
                    temp.getParent().setRight(null);
                }

                if(node.getRight() != null){
                    node.getRight().setParent(temp);
                }
                temp.setRight(node.getRight());

                // ensures that the node does not create an infinite reference to itself
                if(node.getLeft().getData() != temp.getData()){
                    temp.setLeft(node.getLeft());
                }
            }


            if (isRoot(node)) {
                this.setRoot(temp);
            }

            decrementSize();
            return temp;
        }
        return node;
    }

    /*
    returns the max value of a subtree starting with the provided node recursively
    @param node: the starting node
    @return: the node with the maximum value in the tree or subtree
     */
    private Node getMaxNode(Node node) {
        if (node.getRight() == null) {
            return node;
        }


        return getMaxNode(node.getRight());
    }

    /*
    returns the min value of a subtree starting with the provided node recursively
    @param node: the starting node
    @return: the node with the minimum value in the tree or subtree
     */
    private Node getMinNode(Node node) {
        if (node.getLeft() == null) {
            return node;
        }

        return getMinNode(node.getLeft());
    }

    /*
    Launches the printInOrderRecursive function
     */
    public void printInorder() {
        printInorderRecursive(this.root);
        System.out.print("\n");
    }

    /*
    @return: returns true if tree doesn't have any nodes
     */
    private boolean isEmpty() {
        if (root == null) {
            return true;
        }

        return false;
    }

    /*
    prints the contents of tree in order from least to greatest
    @param currentNode: The node of the current recursive call
     */
    private void printInorderRecursive(Node currentNode) {
        // empty tree
        if (isEmpty()) {
            System.out.print("Tree is empty: root = null");
            return;
        }

        if (currentNode.getLeft() != null) {
            printInorderRecursive(currentNode.getLeft());
        }

        System.out.print(currentNode.getData() + " ");

        if (currentNode.getRight() != null) {
            printInorderRecursive(currentNode.getRight());
        }
    }

    /*
    launches the printPostOrderRecursively function
     */
    public void printPostorder() {
        printPostorderRecursively(this.root);
        System.out.print("\n");
    }

    /*
    prints the contents of the tree postorder
    @param currentNode: the node of the current recursive call
     */
    private void printPostorderRecursively(Node currentNode) {
        // empty tree
        if (isEmpty()) {
            System.out.print("Tree is empty: root = null");
            return;
        }

        if (currentNode.getLeft() != null) {
            printPostorderRecursively(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            printPostorderRecursively(currentNode.getRight());
        }

        System.out.print(currentNode.getData() + " ");
    }

    /*
    launches the printPreorderRecursively function
     */
    public void printPreorder() {
        printPreorderRecursively(this.root);
        System.out.print("\n");
    }

    /*
    prints the contents of the tree preorder
    @param currentNode: the node of the current recursive call
     */
    private void printPreorderRecursively(Node currentNode) {
        // empty tree
        if (isEmpty()) {
            System.out.print("Tree is empty: root = null");
            return;
        }

        System.out.print(currentNode.getData() + " ");

        if (currentNode.getLeft() != null) {
            printPreorderRecursively(currentNode.getLeft());
        }

        if (currentNode.getRight() != null) {
            printPreorderRecursively(currentNode.getRight());
        }
    }

    /*
    launches the searchRecursively function
     */
    public Node search(int value) {
        return searchRecursively(value, this.root);
    }

    /*
    traverses the tree for the node containing the data "value"
    @param value: the data being searched for
    @return: the node containing the data "value", null if not found
     */
    private Node searchRecursively(int value, Node currentNode) {
        if (value == currentNode.getData()) {
            return currentNode;
        } else if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            return null;
        } else if (value < currentNode.getData()) {
            if (currentNode.getLeft() != null) {
                return searchRecursively(value, currentNode.getLeft());
            } else {
                return null;
            }
        } else if (value > currentNode.getData()) {
            if (currentNode.getRight() != null) {
                return searchRecursively(value, currentNode.getRight());
            } else {
                return null;
            }
        }

        return null;
    }

    /*
    Locates and returns the node succeeding a given node
    @param data: the data contained in the preceding node
    @return: the node succeeding the node with the argument data
     */
    public Node getSuccessor(int data) {
        Node node = search(data);

        // node not found with search function
        if (node == null) {
            System.out.println("Node " + data + " not found");
            return null;
        }
        // node without right child case
        else if (node.getRight() == null) {
            // either a parent is the successor of he node, or successor does not exist
            Node parentNode = node.getParent();

            // while parentNode is not the root and it's data is less than the node data
            while(!isRoot(parentNode) && parentNode.getData() < node.getData()){
                parentNode = parentNode.getParent();
            }

            // if a parent node is found with data greater than the node's data, it is the predecessor
            if(parentNode.getData() > node.getData()){
                return parentNode;
            }
            // loop has checked parent nodes until root node was reached
            else {
                System.out.println("Node " + node.getData() + " is the minimum tree value");
                return null;
            }
        } else {
            // success is the minimum key value of the right subtree
            return getMinNode(node.getRight());
        }
    }

    /*
    Locates and returns the node preceding a given node
    @param data: the data contained in the succeeding node
    @return: the node preceding the node with the argument data
     */
    public Node getPredecessor(int data) {
        Node node = search(data);

        // node not found with search function
        if (node == null) {
            System.out.println("Node " + data + " not found");
            return null;
        }
        // node without left child case
        else if (node.getLeft() == null) {
            // Either a parent node is the predecessor or predecessor does not exist
            Node parentNode = node.getParent();

            // while parentNode is not the root and it's data is greater than the node data
            while(!isRoot(parentNode) && parentNode.getData() > node.getData()){
                parentNode = parentNode.getParent();
            }

            // if a parent node is found with data less than the node's data, it is the predecessor
            if(parentNode.getData() < node.getData()){
                return parentNode;
            }
            else {
                System.out.println("Node " + node.getData() + " is the minimum tree value");
                return null;
            }
        }
        else {
            // predecessor is the maximum key value of the left subtree
            return getMaxNode(node.getLeft());
        }
    }
}



