package mapclasses;

public class CollidableObject implements ICollidable{
    boolean isCollidable;

    public CollidableObject(boolean iscollidable){
        this.isCollidable = iscollidable;
    }

    public boolean iscollidable(){
        return this.isCollidable;
    }
}

