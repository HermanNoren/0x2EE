package model.gameinterfaces;

import java.util.List;

/**
 * Interface for communicating with a highscore-file
 */

public interface IHasHighscore {
    List<String> getHighscoreName();
    void updateHighscoreList();
    void deleteLetter();
    void updateName(String letter);
}
