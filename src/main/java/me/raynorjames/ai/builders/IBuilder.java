package me.raynorjames.ai.builders;

import me.raynorjames.ai.structure.IAIStructure;
import me.raynorjames.ai.structure.Block;
import me.raynorjames.ai.structure.Primitives;
import me.raynorjames.ai.structure.TaskFinish;

import java.util.List;

/**
 * Created by productionaccount on 12/16/15.
 */
public interface IBuilder {
    void Build(TaskFinish taskFinish);

    void BuildSingles(List<Primitives.Single> singles);

    void BuildColumns(List<Primitives.Column> columns);

    void BuildCubes(List<Primitives.Cube> cubes);

    void BuildFlats(List<Primitives.Flat> flats);

    void DestroySingles(List<Primitives.Single> singles);

    void DestroyColumns(List<Primitives.Column> columns);

    void DestroyCubes(List<Primitives.Cube> cubes);

    void DestroyFlats(List<Primitives.Flat> flats);

    void sortBlock(Block block);

    void PlaceBlock(Block block);

    void RemoveBlock(Block block);

    void destroy(TaskFinish taskFinish);

    void setOrigin(int x, int y, int z);

    int getOriginX();

    int getOriginY();

    int getOriginZ();

    IAIStructure getStructure();

}
