package sound;

import model.EGameEvents;

public interface ISoundObserver {

    void notifySoundEvent(EGameEvents event);

}
