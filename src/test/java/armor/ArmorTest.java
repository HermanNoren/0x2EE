package armor;

import model.armor.Armor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArmorTest {
    @Test
    void test_armor_upgrades(){
        Armor armor = new Armor();
        armor.levelUp();
        armor.levelUp();
        assertEquals(3, armor.currentStat(), 0);
    }
}
