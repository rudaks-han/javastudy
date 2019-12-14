package baeldung.ifelse.type3_factory;

public class Addition implements Operation {
    @Override
    public int apply(int a, int b) {
        return a + b;
    }
}
