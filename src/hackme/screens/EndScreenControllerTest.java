package hackme.screens;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EndScreenControllerTest {

    private EndScreenController endScreenController;
    @BeforeEach
    void setUp() {
        endScreenController = new EndScreenController();
    }

    @AfterEach
    void tearDown() {
        endScreenController = null;
    }

    @Test
    void testGetScoreboard() {
        ArrayList<String[]> arrLstScoreboard = endScreenController.getScoreboard(System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard1.txt");
        Iterator<String[]> itr = arrLstScoreboard.iterator();
        int iIndex = 1;
        while(itr.hasNext())
        {
            String[] strPair = itr.next();

            if(iIndex == 1) {
                if(!strPair[0].equals("james") || !strPair[1].equals("6")) {
                    fail();
                }
            } else if(iIndex == 2) {
                if(!strPair[0].equals("bob") || !strPair[1].equals("3")) {
                    fail();
                }
            } else if(iIndex == 3) {
                if(!strPair[0].equals("aaa") || !strPair[1].equals("0")) {
                    fail();
                }
            } else if(iIndex > 3) {
                fail();
            }

            iIndex++;
        }
        if(iIndex != 4) {
            fail();
        }
    }

    @Test
    void testInsertIntoScoreboard() {

        endScreenController.saveScoreboard(new ArrayList<String[]>(), System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard2.txt");//wipe it

        ArrayList<String[]> arrListScoreboard = endScreenController.getScoreboard(System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard2.txt");
        if(!arrListScoreboard.isEmpty()) {
            fail();
        }

        arrListScoreboard = endScreenController.insertIntoScoreboard("qqq", 10, arrListScoreboard);
        arrListScoreboard = endScreenController.insertIntoScoreboard("aa", 11, arrListScoreboard);
        arrListScoreboard = endScreenController.insertIntoScoreboard("bb", 9, arrListScoreboard);
        Iterator<String[]> itr = arrListScoreboard.iterator();
        int iIndex = 1;
        while(itr.hasNext())
        {
            String[] strPair = itr.next();
            if(iIndex == 1) {
                if(!strPair[0].equals("aa") || !strPair[1].equals("11")) {
                    fail();
                }
            } else if(iIndex == 2) {
                if(!strPair[0].equals("qqq") || !strPair[1].equals("10")) {
                    fail();
                }
            } else if(iIndex == 3) {
                if(!strPair[0].equals("bb") || !strPair[1].equals("9")) {
                    fail();
                }
            }
            iIndex++;
        }
        if(iIndex != 4) {
            fail();
        }
    }

    @Test
    void testSaveScoreboard() {
        endScreenController.saveScoreboard(new ArrayList<String[]>(), System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard2.txt");//wipe it
        ArrayList<String[]> arrListScoreboard = endScreenController.getScoreboard(System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard2.txt");
        if(!arrListScoreboard.isEmpty()) {
            fail();
        }

        arrListScoreboard = new ArrayList<String []>();
        arrListScoreboard.add(new String[] {"aa", "3"});
        arrListScoreboard.add(new String[] {"aa", "3"});
        arrListScoreboard.add(new String[] {"aa", "3"});
         endScreenController.saveScoreboard(arrListScoreboard, System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard2.txt");
        arrListScoreboard = endScreenController.getScoreboard(System.getProperty("user.dir") + "/src/hackme/resources/_test_scoreboard2.txt");
         if(arrListScoreboard.size() != 3)
        {
            fail();
        }
    }
}