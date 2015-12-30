package me.raynorjames.ai.structure;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by productionaccount on 12/15/15.
 */
public class AIStructure implements IAIStructure {
    private List<Primitives.Column> columns = new ArrayList<>();
    private List<Primitives.Single> singles = new ArrayList<>();
    private List<Primitives.Cube> cubes = new ArrayList<>();
    private List<Primitives.Flat> flats = new ArrayList<>();
    private int xOrigin = 0, yOrigin = 0, zOrigin = 0;
    private String name = "undefined";
    private String permission = "undefined";
    private String author = "undefined";
    private String description = "undefined";

    public void addFlat(Primitives.Flat flat) {
        flats.add(flat);
    }

    public void addCube(Primitives.Cube cube) {
        cubes.add(cube);
    }

    public void addSingle(Primitives.Single single) {
        singles.add(single);
    }

    public void addColumn(Primitives.Column column) {
        columns.add(column);
    }

    public int getBlockCount() {
        int count = 0;
        for (int i = 0; i < columns.size(); i++) count += columns.get(i).getBlocks().size();
        for (int i = 0; i < flats.size(); i++) count += flats.get(i).getBlocks().size();
        for (int i = 0; i < cubes.size(); i++) count += cubes.get(i).getBlocks().size();
        count += singles.size();
        return count;
    }

    public int getxOrigin() {
        return xOrigin;
    }

    public int getyOrigin() {
        return yOrigin;
    }

    public int getzOrigin() {
        return zOrigin;
    }

    public void setOrigin(int xOrigin, int yOrigin, int zOrigin) {
        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;
        this.zOrigin = zOrigin;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPermission() {
        return permission;
    }

    @Override
    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public void setAuthor(String name) {
        this.author = name;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<Primitives.Column> getColumns() {
        return columns;
    }

    @Override
    public List<Primitives.Single> getSingles() {
        return singles;
    }

    @Override
    public List<Primitives.Cube> getCubes() {
        return cubes;
    }

    @Override
    public List<Primitives.Flat> getFlats() {
        return flats;
    }

    @Override
    public String toString() {
        return "AIStructure [" +
                "columns=" + columns +
                ", singles=" + singles +
                ", cubes=" + cubes +
                ", flats=" + flats +
                ", xOrigin=" + xOrigin +
                ", yOrigin=" + yOrigin +
                ", zOrigin=" + zOrigin +
                ", name=" + name +
                ", permission=" + permission +
                ", author=" + author +
                ", description=" + description +
                ']';
    }
}
