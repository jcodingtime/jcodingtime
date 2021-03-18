package jcodingtime.java.generator.builder;

public abstract class TestBuilder {

    private StringBuffer stringBuffer;

    public TestBuilder(){
    }

    public abstract String generate();
	public abstract void generateFile();
	
	public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }
}
