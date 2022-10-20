package sound;

import model.EGameEvents;
import model.SoundObserver;

public class SoundPlayer implements SoundObserver {

    private SoundEffect soundEffect;

    private SoundPlayer() {
        soundEffect = new SoundEffect();
    }

    @Override
    public void notifySoundEvent(EGameEvents event) {
        switch (event) {
            case BOSS_SPAWN -> soundEffect.setSoundFile("src/main/resources/sound/shrek.wav");
        }
    }
}
