package hackme.screens;

import com.sun.jdi.InvalidTypeException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class EndScreenController {
    public ScreenReturner returner;

    @FXML
    Label gameResultLabel;

    /**
     * Tells the screen the result of the finished game
     * @param result Result of the game
     */
    public void setResult(String result) {
        if (result.equals("lost"))
            gameResultLabel.setText("Game Over!");
    }

    /**
     * get the scoreboard as name,score pairs
     * @return array list containing name,score pairs
     */
    public ArrayList<String[]> getScoreboard(String strFilePath)
    {
        Scanner scnWords = null;
        try {
            scnWords = new Scanner(new File(strFilePath));//System.getProperty("user.dir") + "/src/hackme/resources/scoreboard.txt"
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String []> arrLstLines = new ArrayList<String []>();
        while (scnWords.hasNextLine()) {
            String strLine = scnWords.nextLine();
            int iIndex = strLine.indexOf(" ");
            String [] strArrLine = {strLine.substring(0, iIndex), strLine.substring(iIndex+1, strLine.length())};//split line into name, score array.
            arrLstLines.add(strArrLine);
        }
        return arrLstLines;
    }

    public ArrayList<String[]> insertIntoScoreboard(String strName, int iScore, ArrayList<String[]> arrLstLines)
    {
        Iterator<String[]> itrLines = arrLstLines.iterator();
        int iIndex = 0;

        while(itrLines.hasNext())
        {
            boolean bool = false;

            try {
                bool = iScore > Integer.parseInt(itrLines.next()[1]);
            } catch(Exception e) {
                // if itrLines.next()[2] was not a string
                e.printStackTrace();
                System.out.println("Incorrect type in values column");
                return new ArrayList<>();
            }

            if(bool) {
                arrLstLines.add(iIndex, new String[] {strName, Integer.toString(iScore)});
                break;
            }

            iIndex++;
        }
        if(iIndex == arrLstLines.size()) //add to the end, score was less than (or equal to) all others or no lines
        {
            arrLstLines.add(new String[] {strName, Integer.toString(iScore)});
        }

        return arrLstLines;
    }

    /**
     * Write the scoreboard back to a file
     * @param arrLstLines The array list of name/value pairs to write back to file
     * @param strFilePath The path of the file
     */
    public void saveScoreboard(ArrayList<String[]> arrLstLines, String strFilePath)
    {
        PrintStream printStream = null;

        try {
            printStream = new PrintStream(strFilePath); //overwrites file
        } catch (IOException e) {
            e.printStackTrace();
        }

        Iterator<String[]> itrLines = arrLstLines.iterator();
        while(itrLines.hasNext())
        {
            String[] strArrPairs = itrLines.next();
            printStream.println(strArrPairs[0] + " " + strArrPairs[1]);
        }
        printStream.close();
    }

    /**
     * Handle the play again button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void playAgainButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("restart");
    }

    /**
     * Handle the exit button on click
     * @param actionEvent The button click
     * @throws Exception
     */
    public void exitButtonClicked(ActionEvent actionEvent) throws Exception {
        returner.onScreenReturn("exit");
    }
}