import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sprites.Player;

import java.io.IOException;

public class PlayerTest {
    Player player;
    @BeforeEach
    void init() throws IOException {
        player = new Player(1, 1, 100); // Sets default starting values for player.
    }
    @Test
    public void test_if_x_increases_when_d_is_pressed(){

    }


}
