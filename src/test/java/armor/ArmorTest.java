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
        assertEquals(0.06, armor.damageReduction(3), 0.00001);
    }
}
