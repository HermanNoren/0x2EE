package Collision;

public class CollidableObject implements ICollidable {
    public boolean isCollidable;

    public CollidableObject(boolean iscollidable){
        this.isCollidable = iscollidable;
    }

    public boolean iscollidable(){
        return this.isCollidable;
    }

}

