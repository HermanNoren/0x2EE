import controllers.Direction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import sprites.Player;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class PlayerTest {
    Player player;
    @BeforeAll
    void init() throws IOException {
        player = new Player(1, 1, 100); // Sets default starting values for player.
    }

    @Test
    void test_if_x_is_greater_than_1_when_move_direction_right_is_called(){
//        player.move(Direction.RIGHT);
//        AssertTrue(player.getPos().x > 1);

    }


}
