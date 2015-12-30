package me.raynorjames.ai.structure;

/**
 * Created by productionaccount on 12/15/15.
 */
public interface IBlock {
    void setID(int id, byte meta);

    void setPosition(int x, int y, int z);

    void setBuildOrder(int order);

    int getID();

    byte getMeta();

    int getX();

    int getY();

    int getZ();

    int BuildOrder();
}
