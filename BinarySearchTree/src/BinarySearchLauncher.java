import java.util.Scanner;

/**
 * Created by Acer Customer on 4/18/2017.
 */
public class BinarySearchLauncher {
    public static void main(String[] args) {
        Node root = new Node(37);
        BST tree = new BST(root);


        boolean notDone = true;
        Scanner sc = new Scanner(System.in);

        while(notDone) {
            System.out.println("Insert a node or type e to exit");
            String input = sc.next();

            if(input.equals("e")){
                notDone = false;
            }
            else {
                try {
                    Node newNode = new Node(Integer.parseInt(input));
                    tree.insert(newNode, tree.getRoot());
                } catch (NumberFormatException e) {
                    System.out.println("Input must be an integer");
                }
            }
        }

        try {
            Node node = tree.search(98);
            System.out.print(node.getData());
        } catch (NullPointerException e) {
            System.out.print("Node not found");
        }


    }
}
