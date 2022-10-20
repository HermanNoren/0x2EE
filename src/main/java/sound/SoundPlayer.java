package sound;

import model.EGameEvents;

import java.util.HashMap;
import java.util.Map;

public class SoundPlayer implements ISoundObserver {
    private SoundEffect activeSound;
    private final Map<EGameEvents, SoundEffect> soundEffects;

    public SoundPlayer() {
        soundEffects = new HashMap<>(Map.of(
             EGameEvents.BOSS_SPAWN, new SoundEffect("src/main/resources/sound/shrek.wav"),
             EGameEvents.PLAYER_SHOOT, new SoundEffect("src/main/resources/sound/shot.wav"),
             EGameEvents.ENEMY_HIT, new SoundEffect("src/main/resources/sound/hit.wav"),
             EGameEvents.ENEMY_DEAD, new SoundEffect("src/main/resources/sound/oof.wav"),
             EGameEvents.ITEM_PICKUP, new SoundEffect("src/main/resources/sound/item_pickup.wav")
        ));
    }

    @Override
    public void notifySoundEvent(EGameEvents event) {
        activeSound = soundEffects.get(event);
        if (activeSound != null) {
            activeSound.play();
        }
    }
}
