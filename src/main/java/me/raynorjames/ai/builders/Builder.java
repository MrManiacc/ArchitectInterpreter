package me.raynorjames.ai.builders;

import me.raynorjames.ai.structure.IAIStructure;
import me.raynorjames.ai.structure.Block;
import me.raynorjames.ai.structure.Primitives;
import me.raynorjames.ai.structure.TaskFinish;
import me.raynorjames.ai.utils.ProcessHelper;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by productionaccount on 12/16/15.
 */
public class Builder implements IBuilder {
    private IAIStructure structure;
    private World world;
    private int x, y, z;

    private HashMap<Integer, List<Block>> blockGroups = new HashMap<>();

    public Builder(World world, IAIStructure structure) {
        this.world = world;
        this.structure = structure;
    }

    @Override
    public void Build(final TaskFinish taskFinish) {
        final long startTime = System.currentTimeMillis();
        BuildSingles(structure.getSingles());
        BuildColumns(structure.getColumns());
        BuildCubes(structure.getCubes());
        BuildFlats(structure.getFlats());
        int[] sorted = ProcessHelper.sortedFromHashMap(blockGroups);
        for (int i = 0; i < sorted.length; i++) {
            int lookup = sorted[i];
            List<Block> blocks = blockGroups.get(lookup);
            System.out.println(lookup);
            System.out.println(blocks.size());
            for(int j = 0; j < blocks.size(); j++)
                PlaceBlock(blocks.get(j));
        }
        long now = System.currentTimeMillis() - startTime;
        taskFinish.TaskFinish(now);
    }

    @Override
    public void BuildSingles(List<Primitives.Single> singles) {
        for (Primitives.Single single : singles)
            sortBlock(single.getBlock());
    }

    @Override
    public void BuildColumns(List<Primitives.Column> columns) {
        for (Primitives.Column column : columns)
            for (Block block : column.getBlocks())
                sortBlock(block);
    }

    @Override
    public void BuildCubes(List<Primitives.Cube> cubes) {
        for (Primitives.Cube cube : cubes)
            for (Block block : cube.getBlocks())
                sortBlock(block);
    }

    @Override
    public void BuildFlats(List<Primitives.Flat> flats) {
        for (Primitives.Flat flat : flats)
            for (Block block : flat.getBlocks())
                sortBlock(block);
    }

    @Override
    public void DestroySingles(List<Primitives.Single> singles) {
        for (Primitives.Single single : singles)
            RemoveBlock(single.getBlock());
    }

    @Override
    public void DestroyColumns(List<Primitives.Column> columns) {
        for (Primitives.Column column : columns)
            for (Block block : column.getBlocks())
                RemoveBlock(block);
    }

    @Override
    public void DestroyCubes(List<Primitives.Cube> cubes) {
        for (Primitives.Cube cube : cubes)
            for (Block block : cube.getBlocks())
                RemoveBlock(block);
    }

    @Override
    public void DestroyFlats(List<Primitives.Flat> flats) {
        for (Primitives.Flat flat : flats)
            for (Block block : flat.getBlocks())
                RemoveBlock(block);
    }

    @Override
    public void RemoveBlock(Block block) {
        org.bukkit.block.Block worldBlock = world.getBlockAt(structure.getxOrigin() + this.getOriginX() + block.getX(), structure.getyOrigin() + this.getOriginY() + block.getY(), structure.getzOrigin() + this.getOriginZ() + block.getZ());
        worldBlock.setType(Material.AIR);
    }

    @Override
    public void PlaceBlock(Block block) {
        org.bukkit.block.Block worldBlock = world.getBlockAt(structure.getxOrigin() + this.getOriginX() + block.getX(), structure.getyOrigin() + this.getOriginY() + block.getY(), structure.getzOrigin() + this.getOriginZ() + block.getZ());
        worldBlock.setTypeIdAndData(block.getID(), block.getMeta(), false);
    }

    @Override
    public void sortBlock(Block block) {
        List<Block> group = blockGroups.get(block.BuildOrder());
        if (group == null) {
            List<Block> newGroup = new ArrayList<>();
            newGroup.add(block);
            blockGroups.put(block.BuildOrder(), newGroup);
        } else {
            group.add(block);
            blockGroups.remove(block.BuildOrder());
            blockGroups.put(block.BuildOrder(), group);
        }
    }

    @Override
    public void destroy(final TaskFinish taskFinish) {
        final long startTime = System.currentTimeMillis();
        DestroySingles(structure.getSingles());
        DestroyColumns(structure.getColumns());
        DestroyCubes(structure.getCubes());
        DestroyFlats(structure.getFlats());
        long now = System.currentTimeMillis() - startTime;
        taskFinish.TaskFinish(now);
    }

    @Override
    public void setOrigin(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int getOriginX() {
        return x;
    }

    @Override
    public int getOriginY() {
        return y;
    }

    @Override
    public int getOriginZ() {
        return z;
    }

    @Override
    public IAIStructure getStructure() {
        return structure;
    }

    @Override
    public String toString() {
        return "Builder [" +
                "structure=" + structure +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", blockGroups=" + blockGroups +
                ']';
    }
}
