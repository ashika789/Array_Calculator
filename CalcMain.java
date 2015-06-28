

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class CalcMain {

    JFrame guiFrame;
    JPanel buttonPanel;
    JTextField numberCalc;
    static String tempString;
    static String userDefinedTitle;
    static String userDefinedLayout;
    static int tempIntBeginning;
    static int tempIntEnd;
    static int windowSizeX;
    static int windowSizeY;
    static int numRows;
    static int numColumns;
    static int horizontalGap;
    static int verticalGap;
    static int textBoxSize;
    static Stack buttonStack = new Stack();
    static Stack reversedButtonStack = new Stack();

    @SuppressWarnings("empty-statement")
    public static void main(String[] args) throws FileNotFoundException {


        try {
            //change filepath to point at the input file
            File f1 = new File("/Users/AshikaGanesh/Documents/eclipse 2/CS1332/Chegg/src/input.txt");
            Scanner scan1 = new Scanner(f1);
            //scan1.useDelimiter("\\(\\)");

            while (scan1.hasNextLine() == true) {

                // Grabbing title and removing quotes
                if (scan1.next().equalsIgnoreCase("Window") == true);
                {
                    tempString = scan1.next();
                    userDefinedTitle = tempString.substring(1, tempString.length() - 1);

                    System.out.println("The Title= " + userDefinedTitle);

                    //grabbing Window Size X
                    tempString = scan1.next();
                    tempString = tempString.substring(1, tempString.length() - 1);
                    windowSizeX = Integer.parseInt(tempString);
                    System.out.println(windowSizeX);

                    //grabbing Window Size Y
                    tempString = scan1.next();
                    tempString = tempString.substring(0, tempString.length() - 1);
                    windowSizeY = Integer.parseInt(tempString);
                    System.out.println(windowSizeY);

                    // skipping the lines to get to the user defined TextField
                    tempString = scan1.nextLine();
                    System.out.println(tempString + " Skipped");

                    //Finding the Textfield size and assigning it to textBoxSize
                    tempString = scan1.nextLine();
                }
                if (tempString.contains("Textfield") == true);
                {
                    tempIntBeginning = tempString.indexOf("Textfield ") + 10;
                    textBoxSize = Integer.parseInt(tempString.substring(tempIntBeginning, tempString.length() - 1));
                    System.out.println("The Textfield is " + textBoxSize);

                    //assigning tempString for layout to be checked
                    tempString = scan1.nextLine();
                }

                //Finding the layout and assigning it to userDefinedLayout
                if (tempString.contains("Layout") == true);
                {
                    tempIntBeginning = tempString.indexOf("Layout ") + 7;
                    tempIntEnd = tempString.indexOf('(');
                    userDefinedLayout = tempString.substring(tempIntBeginning, tempIntEnd);

                    System.out.println(tempIntBeginning);
                    System.out.println(tempIntEnd);

                    tempIntBeginning = tempIntEnd + 1;
                    tempIntEnd = tempString.indexOf(',');
                    System.out.println(" tempIntBeginning = " + tempIntBeginning);
                    System.out.println(" tempIntEnd = " + tempIntEnd);
                    numRows = Integer.parseInt(tempString.substring(tempIntBeginning, tempIntEnd));
                    System.out.println(numRows);

                    //additional plus one on front and back to account for space
                    tempIntBeginning = tempIntEnd + 2;
                    tempIntEnd = (tempString.substring(tempIntBeginning).indexOf(',')) + tempIntBeginning;
                    System.out.println(" tempIntBeginning = " + tempIntBeginning);
                    System.out.println(" tempIntEnd = " + tempIntEnd);
                    numColumns = Integer.parseInt(tempString.substring(tempIntBeginning, tempIntEnd));
                    System.out.println(numColumns);

                    //Getting optional argument 1
                    if (tempString.charAt(tempIntEnd + 0) != ')') {
                        tempIntBeginning = tempIntEnd + 2;
                        tempIntEnd = (tempString.substring(tempIntBeginning).indexOf(',')) + tempIntBeginning;
                        System.out.println(" tempIntBeginning = " + tempIntBeginning);
                        System.out.println(" tempIntEnd = " + tempIntEnd);
                        horizontalGap = Integer.parseInt(tempString.substring(tempIntBeginning, tempIntEnd));
                        System.out.println(" horizontalGap = " + horizontalGap);

                        //Getting optional argument 2
                        System.out.println("Testing second optional parameters: " + tempString.charAt(tempIntEnd - 0));
                        if (tempString.charAt(tempIntEnd - 0) != ')') {
                            tempIntBeginning = tempIntEnd + 2;
                            tempIntEnd = (tempString.substring(tempIntBeginning).indexOf(')')) + tempIntBeginning;
                            System.out.println(" tempIntBeginning = " + tempIntBeginning);
                            System.out.println(" tempIntEnd = " + tempIntEnd);
                            //System.out.println(" TestTest = " + Integer.parseInt(tempString.substring(tempIntBeginning, tempIntEnd)));
                            verticalGap = Integer.parseInt(tempString.substring(tempIntBeginning, tempIntEnd));
                            System.out.println(" verticalGap = " + verticalGap);
                        }
                    }
                    System.out.println("The Layout= " + userDefinedLayout);
                    System.out.println();
                }

                //going through buttons and labels
                while (scan1.hasNext("Button") || scan1.hasNext("Label") == true) {
                    // adding buttons
                    if (scan1.hasNext("Button") == true) {
                        tempString = scan1.nextLine();
                        System.out.println("temp string" + tempString);
                        tempIntBeginning = Integer.valueOf(tempString.substring(12, 13));
                        System.out.println("tempIntBeginning = " + tempIntBeginning);
                        buttonStack.push(tempIntBeginning);
                    }
                    //adding labels
                    if (scan1.hasNext("Label") == true) {
                        tempString = scan1.nextLine();
                        System.out.println("temp string" + tempString);
                        System.out.println("tempIntBeginning = " + tempIntBeginning);
                        buttonStack.push("");
                    }
                }
                if (scan1.hasNext("End") == true) {
                    System.out.println("End of Line");
                }

                //Reversing the order of the Stack. I wanted to use a stack because i'd never used one
                //and later realized it was in the oppposite order than needed, so i'm reversing
                //it instead of changing it to something else
                int buttonStackSize = buttonStack.size();
                System.out.println("buttonStack = " + buttonStack);
                for (int i = 0; i < buttonStackSize; i++) {
                    reversedButtonStack.push(buttonStack.pop());
                    //System.out.println("The button Stack size after = " + buttonStack.size());
                }
                //System.out.println("reversedButtonStack = " + reversedButtonStack);

                System.out.println("1" + scan1.nextLine());
                //System.out.println("2" + scan1.nextLine());
                //System.out.println("3" + scan1.nextLine());
            }

            // making window visiable
            CalcMain frame = new CalcMain(userDefinedTitle, windowSizeX, windowSizeY, numRows,
                    numColumns, horizontalGap, verticalGap, textBoxSize, reversedButtonStack);
            frame.guiFrame.setVisible(true);

        } catch (FileNotFoundException fnf) {
            System.out.println(fnf.getMessage() + " File not found!");
        }
    }

    public CalcMain(String userDefinedTitle, int windowSizeX, int windowSizeY, int numRows,
            int numColumns, int horizontalGap, int verticalGap, int textBoxSize, Stack buttonStack) {
        guiFrame = new JFrame();

        //make sure the program exits when the frame closes
        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        guiFrame.setTitle(userDefinedTitle);
        guiFrame.setSize(windowSizeX, windowSizeY);

        //This will center the JFrame in the middle of the screen
        guiFrame.setLocationRelativeTo(null);

        numberCalc = new JTextField();
        numberCalc.setHorizontalAlignment(JTextField.RIGHT);
        numberCalc.setEditable(true);
        numberCalc.setSize(textBoxSize, 1);

        guiFrame.add(numberCalc, BorderLayout.NORTH);

        buttonPanel = new JPanel();

        //Make a Grid that has three rows and four columns
        buttonPanel.setLayout(new GridLayout(numRows, numColumns, horizontalGap, verticalGap));
        System.out.println("numRows = " + numRows);
        System.out.println("numColumns = " + numColumns);
        System.out.println("horizontalGap = " + horizontalGap);
        System.out.println("verticalGap = " + verticalGap);
        guiFrame.add(buttonPanel, BorderLayout.CENTER);

        //Add the number and label buttons, not sure what the Label"" is supposed
        //to be so i'm leaving it in to show i "can" account for it
        System.out.println("Button Stack before going into calc = " + buttonStack);
        int buttonStacksize = buttonStack.size();
        for (int i = 1; i <= buttonStacksize; i++) {
            addButton(buttonPanel, buttonStack.pop().toString());
        }
    }

    private void addButton(Container parent, String name) {
        JButton but = new JButton(name);
        but.setActionCommand(name);
        parent.add(but);
    }

    /**
     * An object of type ParseError represents a syntax error found in the
     * user's input.
     */
    private static class ParseError extends Exception {

        ParseError(String message) {
            super(message);
        }
    }
}