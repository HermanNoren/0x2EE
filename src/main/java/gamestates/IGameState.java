package gamestates;


import view.panelstates.EStateTag;

public interface IGameState {
    EStateTag getStateTag();
    void update();
}
