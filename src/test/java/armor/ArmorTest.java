package armor;

import model.upgradables.armor.Armor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for armor.
 */
public class ArmorTest {
    @Test
    void test_armor_upgrades(){
        Armor armor = new Armor();
        armor.upgrade();
        armor.upgrade();
        assertEquals(3, armor.currentStats(), 0);
    }
}
