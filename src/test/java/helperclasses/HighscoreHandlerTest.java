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
    void if_the_file_is_cleared_the_file_should_be_empty(){
        highscoreHandler.clearFile();
        assertTrue(highscoreHandler.getHighscoreList().size() == 0);
    }

    @Test
    void if_a_highscore_is_saved_the_file_should_have_updated(){
        highscoreHandler.saveHighscore("Test40", 5000);
        assertTrue(highscores != highscoreHandler.getHighscoreList());
    }

    @Test
    void if_the_file_is_empty_and_new_highscores_are_saved_it_should_have_updated(){
        highscoreHandler.clearFile();
        highscoreHandler.saveHighscore("Test40", 5000);
        highscoreHandler.saveHighscore("Test40", 4500);
        assertTrue(highscoreHandler.getHighscoreList().size() == 2);
    }

    @Test
    void if_the_saved_it_should_have_updated(){
        highscoreHandler.clearFile();
        highscoreHandler.saveHighscore("Test40", 5000);
        assertTrue(highscoreHandler.getHighscoreList().size() == 1);
    }


}
