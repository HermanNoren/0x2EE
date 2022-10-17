package view;

import view.panelstates.EPanelState;

/**
 * Used to instantiate the method which changes the panel state.
 */
public interface IChangeableStatePanel {

    /**
     * method used to change the panel state, for example main panel to pause panel.
     * @param state The panel which will be changed to.
     */
    void changePanelState(EPanelState state);
}
