package di;

public class Controller {
    private Service service;

    public void setService(Service service) {
        this.service = service;
    }

    public void callService() {
        service.doSomething();
    }
}
