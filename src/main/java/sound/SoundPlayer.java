package sound;

import model.EGameEvents;
import model.ISoundObserver;

import java.util.HashMap;
import java.util.Map;

public class SoundPlayer implements ISoundObserver {
    private SoundEffect activeSound;
    private final Map<EGameEvents, SoundEffect> soundEffects;

    public SoundPlayer() {
        soundEffects = new HashMap<>(Map.of(
             EGameEvents.BOSS_SPAWN, new SoundEffect("src/main/resources/sound/shrek.wav"),
             EGameEvents.PLAYER_SHOOT, new SoundEffect("src/main/resources/sound/laserShoot.wav")
        ));
    }

    @Override
    public void notifySoundEvent(EGameEvents event) {
        activeSound = soundEffects.get(event);
        activeSound.play();
    }
}