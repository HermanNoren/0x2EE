package sound;

public class ShootSound implements ISoundObserver {
    SoundEffect se = new SoundEffect();
    public ShootSound(){
        se.setSoundFile("sound/laserShoot.wav");
    }

    @Override
    public void update() {
        se.play();
    }
}
