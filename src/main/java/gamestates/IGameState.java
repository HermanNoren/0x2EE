package gamestates;


import view.panelstates.EPanelState;

public interface IGameState {
    EPanelState getStateTag();

    void updateButtons();
    void update();
}
