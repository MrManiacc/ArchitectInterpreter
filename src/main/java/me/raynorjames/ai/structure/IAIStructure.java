package me.raynorjames.ai.structure;

import java.util.List;

/**
 * Created by productionaccount on 12/15/15.
 */
public interface IAIStructure {

    void addFlat(Primitives.Flat flat);

    void addCube(Primitives.Cube cube);

    void addSingle(Primitives.Single single);

    void addColumn(Primitives.Column column);

    int getBlockCount();

    int getxOrigin();

    int getyOrigin();

    int getzOrigin();

    void setOrigin(int xOrigin, int yOrigin, int zOrigin);

    void setName(String name);

    String getName();

    void setPermission(String permission);

    String getPermission();

    void setAuthor(String name);

    String getAuthor();

    void setDescription(String description);

    String getDescription();

    List<Primitives.Flat> getFlats();

    List<Primitives.Column> getColumns();

    List<Primitives.Cube> getCubes();

    List<Primitives.Single> getSingles();


}

