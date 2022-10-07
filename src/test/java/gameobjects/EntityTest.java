package gameobjects;

import model.Game;
import model.gameobjects.IEntity;
import model.gameobjects.Entity;
import model.gameobjects.Player;
import model.helperclasses.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityTest {
    IEntity entity;
    @BeforeEach
    void init(){
        //Creating player since Entity is abstract, and Player is an Entity.
        entity = new Player(48, 48, new Game());
    }
    @Test
    void test_getPosX_returns_Entity_current_x_position(){
        //Entity starting position is x=48
        assertEquals(48, entity.getPosX());
    }
    @Test
    void test_getPosY_returns_Entity_current_Y_position(){
        //Entity starting position is y=48
        assertEquals(48, entity.getPosY());
    }
    @Test
    void test_setPosX_sets_Entity_x_position(){
        //Entity starting position is x=48
        entity.setPosX(96);
        assertEquals(96, entity.getPosX());
    }
    @Test
    void test_setPosY_sets_Entity_y_position(){
        //Entity starting position is x=48
        entity.setPosY(96);
        assertEquals(96, entity.getPosY());
    }
//    @Test
//    void test_getPos_returns_vector2_with_Entity_current_pos(){
//        //Entity starting pos is x=48, y=48
//        Vector2 testPosVector = new Vector2(48, 48);
//        assertEquals(testPosVector.getX(), entity.getPos());
//        assertEquals(testPosVector.getY(), entity.getPos());
//    }
    @Test
    void test_setPos_sets_vector2_with_x_and_y_values(){
        entity.setPos(new Vector2(10, 10));
        assertEquals(10, entity.getPosX());
        assertEquals(10, entity.getPosY());
    }

    @Test
    void test_getVel_returns_vector2_with_Entity_current_vel(){
        //Entity starting vel is x=0, y=0
        Vector2 testVelVector = new Vector2(0, 0);
        assertEquals(testVelVector.getX(), entity.getVel().getX());
        assertEquals(testVelVector.getY(),entity.getVel().getY());
    }
    @Test
    void test_setVel_sets_vector2_with_x_and_y_values(){
        entity.setVel(new Vector2(10, 10));
        assertEquals(10, entity.getVelX());
        assertEquals(10, entity.getVelY());
    }

    @Test
    void test_getAcc_returns_vector2_with_Entity_current_acc(){
        //Entity starting acc is x=0, y=0
        Vector2 testAccVector = new Vector2(0, 0);
        assertEquals(testAccVector.getX(), entity.getAcc().getX());
        assertEquals(testAccVector.getY(),entity.getAcc().getY());
    }
    @Test
    void test_setAcc_sets_vector2_with_x_and_y_values(){
        entity.setAcc(new Vector2(10, 10));
        assertEquals(10, entity.getAccX());
        assertEquals(10, entity.getAccY());
    }
    @Test
    void test_getVelX_returns_Entity_current_velX(){
        //Entity starts with velX = 0
        assertEquals(0, entity.getVelX());
    }

    @Test
    void test_getVelY_returns_Entity_current_velY(){
        //Entity starts with velY = 0
        assertEquals(0, entity.getVelY());
    }
    @Test
    void test_setVelX_sets_sets_velX(){
        //Entity starts with velX = 0;
        entity.setVelX(1);
        assertEquals(1, entity.getVelX());
    }

    @Test
    void test_setVelY_sets_sets_velY(){
        //Entity starts with velY = 0;
        entity.setVelY(1);
        assertEquals(1, entity.getVelY());
    }
    @Test
    void test_getAccX_returns_Entity_current_accX(){
        //Entity starts with accX = 0
        assertEquals(0, entity.getAccX());
    }

    @Test
    void test_getAccY_returns_Entity_current_accY(){
        //Entity starts with accY = 0
        assertEquals(0, entity.getAccY());
    }
    @Test
    void test_setAccX_sets_sets_accX(){
        //Entity starts with accX = 0;
        entity.setAccX(1);
        assertEquals(1, entity.getAccX());
    }
    @Test
    void test_setAccY_sets_sets_accY(){
        //Entity starts with accY = 0;
        entity.setAccY(1);
        assertEquals(1, entity.getAccY());
    }

    @Test
    void test_getWidth_returns_width_of_Entity(){

    }



}
