import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Acer Customer on 4/18/2017.
 */
public class BinarySearchLauncher {
    public static void main(String[] args) {
        BST tree = new BST();  // creates empty tree structure (root == null)


        boolean notDone = true; // program loop switch
        Scanner sc = new Scanner(System.in);

        printMenu(); // print initial menu

        // main program loop
        while(notDone) {
            System.out.print("Command? ");
            String input = sc.nextLine();
            input = input.replaceAll("\\s+", " "); // remove any extra spaces from user input
            String[] parsedInput = input.split(" "); // split input into options

            // insertion
            if(parsedInput[0].toLowerCase().equals("i")){
                try {
                    int sizeBeforeMod = tree.getSize(); // size before insertion
                    for(int i = 1; i < parsedInput.length; i++) {
                        int data = Integer.parseInt(parsedInput[i]);
                        tree.insert(new Node(data));
                    }
                    int sizeAfterMod = tree.getSize(); // size after insertion

                    // if the tree has changed, print in order
                    if(sizeBeforeMod != sizeAfterMod){
                        tree.printInorder();
                    }
                } catch (Exception e) {
                    printInvalidInputMessage(input, parsedInput[0].toLowerCase());
                }
            }

            // deletion
            else if(parsedInput[0].toLowerCase().equals("d")) {
                try {
                    for(int i = 1; i < parsedInput.length; i++) {
                        int sizeBeforeMod = tree.getSize(); // size before deletion
                        int data = Integer.parseInt(parsedInput[i]);
                        tree.delete(data);
                        int sizeAfterMod = tree.getSize(); // size after deletion

                        // if the tree has changed, print in order
                        if(sizeAfterMod != sizeBeforeMod){
                            tree.printInorder();
                        }
                    }
                } catch(NullPointerException e){
                    System.out.println("Error in BST.deleteRecursively() method");
                    e.printStackTrace();
                } catch (Exception e) {
                    printInvalidInputMessage(input, parsedInput[0].toLowerCase());
                }
            }

            // find predecessor
            else if(parsedInput[0].toLowerCase().equals("p")){
                try {
                    int data = Integer.parseInt(parsedInput[1]);
                    Node predecessor = tree.getPredecessor(data);
                    if(predecessor == null){
                        System.out.println("No predecessor found");
                    }
                    else{
                        System.out.println(predecessor.getData());
                    }
                } catch (Exception e) {
                    printInvalidInputMessage(input, parsedInput[0].toLowerCase());
                }
            }

            // find successor
            else if(parsedInput[0].toLowerCase().equals("s")){
                try {
                    int data = Integer.parseInt(parsedInput[1]);
                    Node successor = tree.getSuccessor(data);
                    if(successor == null){
                        System.out.println("No successor found");
                    }
                    else{
                        System.out.println(successor.getData());
                    }
                } catch (Exception e) {
                    printInvalidInputMessage(input, parsedInput[0].toLowerCase());
                }
            }

            // exit program
            else if(parsedInput[0].toLowerCase().equals("e")){
                notDone = false;
                System.out.println("Thank you for using my program!");
            }
            else if(parsedInput[0].toLowerCase().equals("h")){
                printMenu();
            }
            else if(parsedInput[0].toLowerCase().equals("t")) {
                System.out.print("Preorder: ");
                tree.printPreorder();
                System.out.print("Inorder: ");
                tree.printInorder();
                System.out.print("Postorder: ");
                tree.printPostorder();
            }
            else {
                printInvalidInputMessage(input, "all");
            }
        }
    }

    /*
    Prints the selection menu
     */
    public static void printMenu() {
        System.out.println("I Insert a value \n" +
                        "D Delete a value \n" +
                        "P Find predecessor \n" +
                        "S Find successor \n" +
                        "T Print traversals \n" +
                        "E Exit the program \n" +
                        "H Display this message");
    }

    /*
    Prints input error messages on a case by case basis
     */
    public static void printInvalidInputMessage(String selection, String option){
        String INSERT = "i";
        String DELETE = "d";
        String PREDECESSOR = "p";
        String SUCCESSOR = "s";
        String PRINTTRAVERSALS = "t";

        if(option.equals(INSERT)){
            System.out.println("Invalid insertion input: '" + selection + "' \n" +
                               "Please use: 'i' <int1 int2...>");
        }
        else if(option.equals(DELETE)){
            System.out.println("Invalid deletion input: '" + selection + "' \n" +
                               "Please use: 'd' <int1 int2...>");
        }
        else if(option.equals(PREDECESSOR)){
            System.out.println("Invalid input to find predecessor: '" + selection + "' \n" +
                    "Please use: 'p' <int>");
        }
        else if(option.equals(SUCCESSOR)){
            System.out.println("Invalid to find successor: '" + selection + "' \n" +
                    "Please use: 's' <int>");
        }
        else {
            System.out.println("Invalid Input: '" + selection + "' \n" +
                               "Options Available: \n" +
                               "Insert: 'i' <int1 int2...> \n" +
                               "Delete: 'd' <int1 int2...> \n" +
                               "Find Predecessor to: 'p' <int> \n" +
                               "Find Successor to: 's' <int>");
        }

    }
}
