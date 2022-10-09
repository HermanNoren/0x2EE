package mapclasses;

import model.mapclasses.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TerrainTest {

    Terrain terrain;
    @BeforeEach
    void init(){
        terrain = new Terrain(10, 10);
    }
}
