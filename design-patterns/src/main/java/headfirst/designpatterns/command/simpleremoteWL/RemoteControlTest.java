package headfirst.designpatterns.command.simpleremoteWL;

public class RemoteControlTest {
    public static void main(String[] args) {
        SimpleRemoteControl remoteControl = new SimpleRemoteControl();

        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();

        remoteControl.setCommand(light::on);
        remoteControl.buttonWasPressed();
        remoteControl.setCommand(garageDoor::up);
        remoteControl.buttonWasPressed();
        remoteControl.setCommand(garageDoor::lightOn);
        remoteControl.buttonWasPressed();
    }

}
