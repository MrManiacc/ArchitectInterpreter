package me.raynorjames.ai.reading;

import me.raynorjames.ai.structure.IAIStructure;
import me.raynorjames.ai.structure.Primitives;

/**
 * Created by productionaccount on 12/15/15.
 */
public interface IProcessor {
    void processData(String[] lines);

    void processOrigin(String line, int lineNumber);

    Primitives.Single processSingle(String line, int lineNumber);

    Primitives.Column processColumn(String line, int lineNumber);

    Primitives.Cube processCube(String line, int lineNumber);

    Primitives.Flat processFlat(String flat, int lineNumber);

    IAIStructure getStructure();

}
