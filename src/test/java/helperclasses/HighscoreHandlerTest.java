package helperclasses;

import model.helperclasses.HighscoreHandler;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class HighscoreHandlerTest {

    private HighscoreHandler highscoreHandler = new HighscoreHandler();

    private List<String> highscores = highscoreHandler.getHighscoreList();

    @AfterEach
    void resetFile(){
        highscoreHandler.rollBackFile(highscores);
    }

    @Test
    void test_if_the_file_is_cleared_the_file_should_be_empty(){
        highscoreHandler.clearFile();
        assertEquals(0, highscoreHandler.getHighscoreList().size());
    }

    @Test
    void test_if_a_highscore_is_saved_the_file_should_have_update(){
        highscoreHandler.saveHighscore("Test40", 5000);
        assertNotSame(highscores, highscoreHandler.getHighscoreList());
    }

    @Test
    void test_if_the_file_is_empty_and_new_highscores_are_saved_it_should_update(){
        highscoreHandler.clearFile();
        highscoreHandler.saveHighscore("Test40", 5000);
        highscoreHandler.saveHighscore("Test40", 4500);
        assertEquals(2, highscoreHandler.getHighscoreList().size());
    }

   @Test
    void test_if_the_biggest_score_gets_placed_first(){
        highscoreHandler.clearFile();
        int lowScore = 100;
        int midScore = 500;
        int highScore = 2000;
        highscoreHandler.saveHighscore("TEST34", lowScore);
        highscoreHandler.saveHighscore("TEST23", highScore);
        highscoreHandler.saveHighscore("TEST11", midScore);
        String firstPlacement = highscoreHandler.getHighscoreList().get(0);
        assertEquals(highscoreHandler.getScore(firstPlacement), highScore);

   }


}
