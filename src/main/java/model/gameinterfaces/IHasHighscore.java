package model.gameinterfaces;

import java.util.List;

public interface IHasHighscore {
    List<String> getHighscoreName();
    void updateHighscoreList();
    void deleteLetter();
    void updateName(String letter);
}
