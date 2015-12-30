package me.raynorjames.ai.reading;

/**
 * Created by productionaccount on 12/18/15.
 */
public abstract class StructureProcessor {
    private String line;
    private int lineNumber;

    public abstract void processData(String line, int lineNumber);

}
