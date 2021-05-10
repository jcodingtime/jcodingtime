package com.github.jcodingtime.generator.builder;

/**
 * Abstract class for builder the blocks of code of test file
 */
public abstract class TestBuilder {

    /**
     * Variable for get buffer of files
     */
    private StringBuffer stringBuffer;

    /**
     * Empty constructor class
     */
    public TestBuilder(){
    }

    /**
     * Abstract method for generate string content test
     * @return string content test generated
     */
    public abstract String generate();

    /**
     * Absctract method for generate file in path in tests
     * directory of source project
     */
	public abstract void generateFile();

    /**
     * Get string buffer of file
     * @return string buffer of file
     */
	public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    /**
     * Set string buffer of file
     * @param stringBuffer
     */
    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }
}
