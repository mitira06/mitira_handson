public class Exercise9_Command {

    // Command interface
    interface Command {
        void execute();
        void undo();
    }

    // Receiver - the actual device
    static class Light {
        private String location;
        public Light(String location) { this.location = location; }
        public void turnOn()  { System.out.println("  [Light] " + location + " light is ON"); }
        public void turnOff() { System.out.println("  [Light] " + location + " light is OFF"); }
    }

    static class Fan {
        private String location;
        public Fan(String location) { this.location = location; }
        public void turnOn()  { System.out.println("  [Fan]   " + location + " fan is ON"); }
        public void turnOff() { System.out.println("  [Fan]   " + location + " fan is OFF"); }
    }

    // Concrete commands
    static class LightOnCommand implements Command {
        private Light light;
        public LightOnCommand(Light light) { this.light = light; }
        public void execute() { light.turnOn(); }
        public void undo()    { light.turnOff(); }
    }

    static class LightOffCommand implements Command {
        private Light light;
        public LightOffCommand(Light light) { this.light = light; }
        public void execute() { light.turnOff(); }
        public void undo()    { light.turnOn(); }
    }

    static class FanOnCommand implements Command {
        private Fan fan;
        public FanOnCommand(Fan fan) { this.fan = fan; }
        public void execute() { fan.turnOn(); }
        public void undo()    { fan.turnOff(); }
    }

    // Invoker - remote control
    static class RemoteControl {
        private Command lastCommand = null;

        public void pressButton(Command command) {
            command.execute();
            lastCommand = command;
        }

        public void pressUndo() {
            if (lastCommand != null) {
                System.out.println("  [Remote] Undo last action:");
                lastCommand.undo();
                lastCommand = null;
            } else {
                System.out.println("  [Remote] Nothing to undo.");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("===== Exercise 9: Command Pattern =====\n");

        Light  livingLight = new Light("Living Room");
        Light  bedroomLight = new Light("Bedroom");
        Fan    ceilingFan   = new Fan("Ceiling");

        RemoteControl remote = new RemoteControl();

        System.out.println("  Pressing buttons:");
        remote.pressButton(new LightOnCommand(livingLight));
        remote.pressButton(new FanOnCommand(ceilingFan));
        remote.pressButton(new LightOnCommand(bedroomLight));
        remote.pressButton(new LightOffCommand(livingLight));

        System.out.println("\n  Pressing Undo:");
        remote.pressUndo();

        System.out.println("\n  Command pattern wraps actions as objects - supports undo too!");
    }
}
