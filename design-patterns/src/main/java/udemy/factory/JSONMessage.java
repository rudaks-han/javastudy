package udemy.factory;

public class JSONMessage extends Message {

    @Override
    public String getContent() {
        return "{\"JSON\":[]}";
    }
}