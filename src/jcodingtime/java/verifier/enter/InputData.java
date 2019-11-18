package verifier.enter;

public class InputData {

    private String scenario;
    private String input;
    private String output;
    private String method;
    private String parameters;

    public String buildData(){
        String inputData = "@scenario " + getScenario() + "\n" +
                "@input "+ getInput() + "\n" +
                "@output "+ getOutput() + "\n" +
                "public static " + getMethod() + "("+ getParameters() +")";
        return inputData;
    }

    public InputData() {
    }

    public InputData(String scenario, String input, String output, String method, String parameters) {
        this.scenario = scenario;
        this.input = input;
        this.output = output;
        this.method = method;
        this.parameters = parameters;
    }

    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }
}
