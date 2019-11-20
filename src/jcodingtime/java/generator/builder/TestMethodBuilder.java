package generator.builder;

public class TestMethodBuilder extends TestBuilder {

    private String methodName;
    private StringBuffer stringBuffer;

    public TestMethodBuilder(String methodName){
        super(methodName);
        this.methodName = methodName;
    }

    public String generate() {
        stringBuffer = new StringBuffer();

        stringBuffer.append("@Test");
        stringBuffer.append("\npublic int test" +
                methodName.toUpperCase().substring(0,1) +
                methodName.substring(1) + "()");
        return stringBuffer.toString();
    }
}
