import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;

public class CompProject {
    private static int points = 0;
    private static int totalQuestionsAnswered = 0;
    private static String[] questionSet = new String[0];
    private static String[] answerSet = new String[0];
    public static void main(String[] args) throws FileNotFoundException {
        Scanner getInput = new Scanner(System.in);
        Boolean runLoop = true;

        commandList(); // lists out commands at the start

        while (runLoop == true) {
            System.out.print("Enter next command: ");
            String userInput = getInput.nextLine();

            if (userInput.equals("STOP")) {
                System.out.println("Stopping...");
                System.out.println("You answered a total of " + totalQuestionsAnswered + " questions!");
                runLoop = false;
            } else if (userInput.equals("Command List") || userInput.equals("1")) {
                System.out.println("");
                commandList();
            } else if (userInput.equals("Question List") ||
                    userInput.equals("Answer List") ||
                    userInput.equals("Question/Answer List") ||
                    userInput.equals("2")) {
                System.out.println("");
                displayList();
            } else if (userInput.equals("Add") || userInput.equals("Add to List") || userInput.equals("3")) {
                System.out.println("");
                addToList(getInput);
            } else if (userInput.equals("Edit") || userInput.equals("Edit List")|| userInput.equals("4")) { 
                System.out.println("");
                editList(getInput);
            } else if (userInput.equals("Delete") || userInput.equals("Delete from List") || userInput.equals("5")) {
                System.out.println("");
                deleteFromList(getInput);
            } else if (userInput.equals("Begin") ||userInput.equals("Begin a Study Set") || userInput.equals("6")) {
                beginStudySet(getInput);
            } else if (userInput.equals("Check Points") || userInput.equals("7")) {
                System.out.println("Points: " + points);
            } else if (userInput.equals("Display Tree") || userInput.equals("8")) {
                displayTree(points);
            } else {    
                System.out.println("Invalid Input...");
            } // if/else for menu navigation
        } // while loop continually will get user input until user ends program
    } // end of main method


    // Prints out command options when called
    public static void commandList() {
        System.out.println("Here is the list of commands!");
        System.out.println("Enter the number corresponding to the desired command to use it.");
        System.out.println("Enter \"STOP\" to end the program.");
        System.out.println("(1) Command List");
        System.out.println("(2) Question/Answer List");
        System.out.println("(3) Add to List");
        System.out.println("(4) Edit List");
        System.out.println("(5) Delete from List");
        System.out.println("(6) Begin a Study Set");
        System.out.println("(7) Check Points");
        System.out.println("(8) Display Tree");
    } // end of command list method

    // Displays the list of questions and their corresponding answers when called
    public static void displayList() {
        // loop to print every index num of list, one by one in a neat column
        System.out.println("Question and Answer List: ");
        if (questionSet.length == 0) {
            System.out.println("Empty!");
        } // case of empty list
        for (int i = 0; i < questionSet.length; i++) {
            String currentQuestion = questionSet[i];
            String currentAnswer = answerSet[i];
            System.out.println(i + ") " + currentQuestion + " | " + currentAnswer);
        } // prints out question and corresponding answer 
    } // end of displayList method

    /*
     * Receives input from user
     * Inserts question into question list
     * Inserts answer into answer list
     */
    public static void addToList(Scanner getInput) {
        System.out.println("Please enter a question: ");
        String insertedQuestion = getInput.nextLine();
        System.out.println("Please enter the answer: ");
        String insertedAnswer = getInput.nextLine();
        System.out.println("Adding...");
        
        // Create new arrays with length of original array plus one
        String[] newQuestionSet = new String[questionSet.length + 1];
        String[] newAnswerSet = new String[answerSet.length + 1];
        
        // copies original array values to new arrays
        for (int i = 0; i < questionSet.length; i++) {
            newQuestionSet[i] = questionSet[i];
            newAnswerSet[i] = answerSet[i];
        }
        
        // adds new question and answer at the end of the new arrays
        newQuestionSet[questionSet.length] = insertedQuestion;
        newAnswerSet[answerSet.length] = insertedAnswer;
        
        // updates original arrays, copies new arrays onto the original (doesn't update the originals until the very end)
        questionSet = newQuestionSet;
        answerSet = newAnswerSet;
    
        System.out.println("Added Question: " + insertedQuestion);
        System.out.println("Added Answer: " + insertedAnswer);
    } // end of addToList method
    

    /*
     * Receives input from user
     * Deletes question at specified index
     * Deletes corresponding answer to the question at the specified index
     */
    public static void deleteFromList(Scanner getInput) {
        // Prompt the user to enter the index of the question to delete
        if (questionSet.length > 0) {
            displayList();
            System.out.println("");
            System.out.println("Please enter question to delete: ");
            
            int indexToDelete = -1;
            if (getInput.hasNextInt()) {
                indexToDelete = getInput.nextInt();
            } // protects against non-integer inputs

            // Check if the index is within valid range
            if (indexToDelete >= 0 && indexToDelete < questionSet.length) {
                // Create new arrays with reduced size
                String[] newQuestionSet = new String[questionSet.length - 1];
                String[] newAnswerSet = new String[answerSet.length - 1];
                
                // Copy elements from original arrays to new arrays, excluding the element at the specified index
                for (int i = 0, j = 0; i < questionSet.length; i++) {
                    if (i != indexToDelete) {
                        newQuestionSet[j] = questionSet[i];
                        newAnswerSet[j] = answerSet[i];
                        j++;
                    }
                }

                questionSet = newQuestionSet;
                answerSet = newAnswerSet;
                // Updates the original arrays with the new ones

                System.out.println("Question at index " + indexToDelete + " has been deleted.");
                getInput.nextLine();
            } else {
                System.out.println("Invalid index. No question deleted.");
                getInput.nextLine();
            }
        } else {
        System.out.println("Set is empty!");
    }
    } // end of deleteFromList method

    /*
     * Receives input from user
     * Edits question at specified index
     * Edits corresponding answer to the question at the specified index
     */
    public static void editList(Scanner getInput) {
        // Prompt the user to enter the index of the question to delete
        if (questionSet.length > 0) {
            displayList();
            System.out.println("");
            System.out.println("Please enter question to edit: ");
            
            int editIndex = -1;
            if (getInput.hasNextInt()) {
                editIndex = getInput.nextInt();
                getInput.nextLine();
            } // protects against non-integer inputs

            // Check if the index is within valid range
            if (editIndex >= 0 && editIndex < questionSet.length) {
                System.out.print("You are editing the following question: ");
                System.out.println(editIndex + ") " + questionSet[editIndex] + " | " + answerSet[editIndex]);
                System.out.println("Please enter a question: ");
                String insertedQuestion = getInput.nextLine();

                System.out.println("Please enter the answer: ");
                String insertedAnswer = getInput.nextLine();
                
                questionSet[editIndex] = insertedQuestion;
                answerSet[editIndex] = insertedAnswer;
                
                // Updates the array with the new entry

                System.out.println("Updated entry: " + questionSet[editIndex] + " | " + answerSet[editIndex]);
            } else {
                System.out.println("Invalid index. No question was updated.");
            }
        } else {
        System.out.println("Set is empty!");
    }
    } // end of editList method
    
    /*
     * When called takes the user's response through a scanner and compares to the answer recorded in
     * the answer list
     * returns true or false based on whether the response matched the recorded answer
     */
    public static Boolean checkAnswer(String enteredResponse, String answer) {
        return answer.equals(enteredResponse);
    } // end of checkAnswer method


    /*
     * This code will take input via a scanner
     * Randomly chooses questions from the entire question set
     * It can give you the same question in the same set
     */
    public static void beginStudySet(Scanner getInput) {
        if (questionSet.length > 0) {

            int countCorrect = 0;
            int countIncorrect = 0;
            int setSize = -1;
            System.out.println("Please enter desired question amount: ");
            if (getInput.hasNextInt()) {
                setSize = getInput.nextInt();
            }
            getInput.nextLine();
            if (setSize > 0) {
                totalQuestionsAnswered += setSize;
                Random random = new Random();

                for (int i = 0; i < setSize; i++) {
                    int questionNumber = i+1;
                    int randomNumber = random.nextInt(questionSet.length);
                    String answer = answerSet[randomNumber];
                    System.out.println("Question " + questionNumber + ": ");
                    System.out.println(questionSet[randomNumber]);

                    System.out.print("Enter Answer: ");
                    String enteredResponse = getInput.nextLine();
                    boolean checkTrue = checkAnswer(answer, enteredResponse);

                    if (checkTrue) {
                        System.out.println("Correct!");
                        System.out.println("");
                        points++;
                        countCorrect++;
                    } else {
                        System.out.println("Incorrect!");
                        countIncorrect++;
                        System.out.println("The correct answer was: " + answer);
                        System.out.println("");
                    } // displays right answer
                    // generates a random number then uses random number to get an entry from the list
                    // from here, call the check answer function
                    // needs to save user input to a local variable in loop, and put in the corresponding
                    // correct answer, save the answer index in another local variable
                    // corresponds to the randomNumber that was selected!!!
                    // repeat until loop is done
                }
                //return total points once loop has finished running
                System.out.println("");
                System.out.println("Summary:");
                System.out.println("You completed " + setSize + " questions!");
                System.out.println("Correct Answers: " + countCorrect + ", Incorrect Answers: " + countIncorrect);
            } else {
                System.out.println("Invalid Set Length!");
            }
        } else {
            System.out.println("Question list is empty!");
        }
    } // end of beginStudySet method

    /*
     * Takes in points, and based on points total, a scanner reads specific lines from text file "trees.txt"
     */
    public static void displayTree(int points) throws FileNotFoundException {
        Scanner printTree = new Scanner(new File("trees.txt"));
        System.out.println("");
        String currentLine = "";

        if (points == 0) {
            for (int i = 0; i < 12; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Next level: 1 point");
        } else if (points >= 1 && points < 5) {
            for (int i = 0; i < 13; i++) {
                printTree.nextLine();
            } // skips first 13 lines
            for (int i = 0; i < 12; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Next level: 5 points");
        } else if (points >= 5 && points < 15) {
            for (int i = 0; i < 26; i++) {
                printTree.nextLine();
            } // skips first 26 lines
            for (int i = 0; i < 27; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Next level: 15 points");
        } else if (points >= 15 && points < 30) {
            for (int i = 0; i < 54; i++) {
                printTree.nextLine();
            } // skips first 54 lines
            for (int i = 0; i < 33; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Next level: 30 points");
        } else if (points >= 30 && points < 50) {
            for (int i = 0; i < 88; i++) {
                printTree.nextLine();
            } // skips first 88 lines
            for (int i = 0; i < 49; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Next level: 50 points");
        } else if (points >= 50 && points < 100) {
            for (int i = 0; i < 138; i++) {
                printTree.nextLine();
            } // skips first 138 lines
            for (int i = 0; i < 60; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Next level: 100 points");
        } else if (points >= 100) {
            for (int i = 0; i < 199; i++) {
                printTree.nextLine();
            } // skips first 199 lines
            for (int i = 0; i < 66; i++) {
                currentLine = printTree.nextLine();
                System.out.println(currentLine);
            }
            System.out.println("Tree is at Max Level!");
        }
    } // end of displayTree method
} // end of CompProject class
