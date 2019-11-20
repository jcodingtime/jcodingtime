package generator.builder;

public abstract class TestBuilder {

    private String description;
    private StringBuffer stringBuffer;

    public TestBuilder(String description){
        this.description = description;
    }

    public abstract String generate();

    public StringBuffer getStringBuffer() {
        return stringBuffer;
    }

    public void setStringBuffer(StringBuffer stringBuffer) {
        this.stringBuffer = stringBuffer;
    }
}
