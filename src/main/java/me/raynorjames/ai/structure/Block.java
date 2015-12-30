package me.raynorjames.ai.structure;

/**
 * Created by productionaccount on 12/15/15.
 */
public class Block implements IBlock {
    private int id, order, x, y, z;
    private byte meta;

    @Override
    public void setID(int id, byte meta) {
        this.id = id;
        this.meta = meta;
    }

    @Override
    public void setPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void setBuildOrder(int order) {
        this.order = order;
    }

    @Override
    public int BuildOrder() {
        return order;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public byte getMeta() {
        return meta;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public String toString() {
        return "Block [" +
                "id=" + id +
                ", order=" + order +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", meta=" + meta +
                ']';
    }
}
