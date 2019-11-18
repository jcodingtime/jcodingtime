public class Main {

    public static void main(String[] args) {
        try
        {
            Parser parser = new Parser(System.in);
            parser.Program();
        }
        catch(ParseException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
