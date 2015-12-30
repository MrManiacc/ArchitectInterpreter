package me.raynorjames.ai.json;

import me.raynorjames.ai.structure.IAIStructure;

import java.io.File;

/**
 * Created by productionaccount on 12/16/15.
 */
public interface IJson {
    IAIStructure read(File file);

    int write(File file, Object object);

}
