import java.util.ArrayList;
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

        printMenu();

        while(notDone) {
            String input = sc.next();
            String[] parsedInput = input.split("s");


            if(parsedInput[0].toLowerCase().equals("i")){
                for(int i = 0; i < parsedInput.length; i++) {
                    System.out.println(parsedInput[i] + " " + 1);
                }
                //                try {
//                    int data = Integer.parseInt(parsedInput[1]);
//                    tree.insert(new Node(data), tree.getRoot());
//                } catch (NumberFormatException e) {
//                    e.printStackTrace();
//                }
            }
            else if(parsedInput[0].toLowerCase().equals("d")){
                System.out.println("D");
            }
            else if(parsedInput[0].toLowerCase().equals("p")){
                System.out.println("P");
            }
            else if(parsedInput[0].toLowerCase().equals("s")){
                System.out.println("S");
            }
            else if(parsedInput[0].toLowerCase().equals("e")){
                notDone = false;
            }
            else if(parsedInput[0].toLowerCase().equals("h")){
                printMenu();
            }
            else {
                System.out.println("Invalid Input");
            }



        }


        tree.printInorder();


    }

    public static void printMenu() {
        System.out.println("Command? \n " +
                        "I Insert a value \n " +
                        "D Delete a value \n " +
                        "P Find predecessor \n " +
                        "S Find successor \n " +
                        "E Exit the program \n " +
                        "H Display this message");
    }
}
