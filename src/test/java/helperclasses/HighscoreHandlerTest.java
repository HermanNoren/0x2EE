package helperclasses;

import model.helperclasses.HighscoreHandler;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class HighscoreHandlerTest {

    private HighscoreHandler highscoreHandler = new HighscoreHandler();

    private List<String> highscores = highscoreHandler.getHighscoreList();

    private final List<String> emptyContent = new ArrayList<>();

    @AfterEach
    void resetFile(){
        highscoreHandler.rollBackFile(highscores);
    }

    @Test
    void test_if_the_file_is_cleared_the_file_should_be_empty(){
        highscoreHandler.rollBackFile(emptyContent);
        assertEquals(0, highscoreHandler.getHighscoreList().size());
    }

    @Test
    void test_if_a_highscore_is_saved_the_file_should_have_update(){
        highscoreHandler.rollBackFile(emptyContent);
        highscoreHandler.saveHighscore("Test40", 5000);
        int prevSize = highscoreHandler.getHighscoreList().size();
        highscoreHandler.saveHighscore("Test30", 6000);
        assertTrue(highscoreHandler.getHighscoreList().size() > prevSize);
    }

    @Test
    void test_if_the_file_is_empty_and_new_highscores_are_saved_it_should_update(){
        highscoreHandler.rollBackFile(emptyContent);
        highscoreHandler.saveHighscore("Test40", 5000);
        highscoreHandler.saveHighscore("Test40", 4500);
        assertEquals(2, highscoreHandler.getHighscoreList().size());
    }

   @Test
    void test_if_the_biggest_score_gets_placed_first(){
        highscoreHandler.rollBackFile(emptyContent);
        int lowScore = 100;
        int midScore = 500;
        int highScore = 2000;
        highscoreHandler.saveHighscore("TEST34", lowScore);
        highscoreHandler.saveHighscore("TEST23", highScore);
        highscoreHandler.saveHighscore("TEST11", midScore);
        String firstPlacement = highscoreHandler.getHighscoreList().get(0);
        assertEquals(highscoreHandler.getScore(firstPlacement), highScore);

   }
   @Test
    void test_that_an_exception_is_thrown_if_file_does_not_exist(){
        highscoreHandler.deleteFile();
        assertThrows(RuntimeException.class, () -> {highscoreHandler.getHighscoreList();});

   }

   @Test
   void test_that_the_highscore_file_should_not_exceed_5_scores(){
        highscoreHandler.rollBackFile(emptyContent);
       highscoreHandler.saveHighscore("TEST34", 1000);
       highscoreHandler.saveHighscore("TEST23", 2000);
       highscoreHandler.saveHighscore("TEST11", 3000);
       highscoreHandler.saveHighscore("TEST34", 4000);
       highscoreHandler.saveHighscore("TEST23", 5000);
       highscoreHandler.saveHighscore("TEST11", 6000);
       assertEquals(5, highscoreHandler.getHighscoreList().size());
   }

   @Test
    void test_if_file_creation_works(){
        highscoreHandler.deleteFile();
       try {
           highscoreHandler.createNewFile();
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       assertEquals(0, highscoreHandler.getHighscoreList().size());
   }

   @Test
    void test_if_constructor_creates_a_new_file_if_the_file_does_not_exist(){
        highscoreHandler.deleteFile();
        HighscoreHandler handler = new HighscoreHandler();
        assertEquals(0, handler.getHighscoreList().size());
   }

}
