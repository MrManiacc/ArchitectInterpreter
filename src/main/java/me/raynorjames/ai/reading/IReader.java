package me.raynorjames.ai.reading;

import me.raynorjames.ai.structure.AIStructure;
import me.raynorjames.ai.structure.IAIStructure;

import java.io.File;

/**
 * Created by productionaccount on 12/15/15.
 */
public interface IReader {
    int read();

    int read(String[] lines);

    IAIStructure getStructure();
}
