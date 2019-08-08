package headfirst.designpatterns.adapter.ducks;

public class DuckAdapter implements Turkey {
    Duck duck;

    public DuckAdapter(Duck duck) {
        this.duck = duck;
    }

    @Override
    public void gobble() {

    }

    @Override
    public void fly() {

    }
}
