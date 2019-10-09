package di;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();

        controller.setService(new ServiceImpl());
        controller.setService(new Service() {
            @Override
            public void doSomething() {
                System.out.println("Anonymous class is doing something");
            }
        });

        controller.setService(() -> System.out.println("Lambda implementation is doing somethings"));

        controller.callService();
    }
}
