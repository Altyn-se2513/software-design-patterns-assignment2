public class PartA {
    // 1part Factory Method)
    public static void run() {
        
        SensorHub tempHub = new TemperatureHub();
        tempHub.inspectArea();

        SensorHub motionHub = new MotionHub();
        motionHub.inspectArea();
    }
}
// product interface
interface Sensor {
    void readData();
}

// Concrete Product 1
class TemperatureSensor implements Sensor {
    @Override
    public void readData() {
        System.out.println("Temp Reading temperature: 22.5°C");
    }
}

// Concrete Product 2
class MotionSensor implements Sensor {
    @Override
    public void readData() {
        System.out.println("Motion Motion detected in living room!");
    }
}

// abstract creator
abstract class SensorHub {
    // abstract factory method
    public abstract Sensor createSensor();


    public void inspectArea() {
        Sensor sensor = createSensor();
        sensor.readData();
    }
}
// Concrete Creator 1
class TemperatureHub extends SensorHub {
    @Override
    public Sensor createSensor() {
        return new TemperatureSensor();
    }
}
// Concrete Creator 2
class MotionHub extends SensorHub {
    @Override
    public Sensor createSensor() {
        return new MotionSensor();
    }
}

/*
 CLEAN CODE PRINCIPLES USED

 1. Meaningful and intention-revealing names
    Example:
        Sensor sensor = createSensor();
        sensor.readData();
    Names such as Sensor, createSensor(), readData(), and inspectArea()
    clearly describe their purpose without additional explanation.

 2. Small methods — one responsibility
    Example:
        public void inspectArea() {
            Sensor sensor = createSensor();
            sensor.readData();
        }
    inspectArea() performs one clear workflow:
    create a sensor and request its data.

 3. Single Responsibility Principle
    TemperatureSensor is responsible only for temperature behavior,
    while MotionSensor is responsible only for motion behavior.
    Object creation is handled by SensorHub subclasses.

 4. Program to interfaces, not implementations
    Example:
        private final SmartLight light;
        private final SmartLock lock;

        public SmartHomeController(SmartHomeFactory factory)
    SmartHomeController depends on abstractions rather than
    AppleLight, GoogleLight, AppleLock, or GoogleLock.

 5. Constructor injection and immutable dependencies
    Example:
        private final SmartLight light;
        private final SmartLock lock;
    Dependencies are supplied through the constructor and stored
    as final fields, making the controller easier to maintain and test.

 6. Avoid duplicated creation logic
    The common inspectArea() algorithm stays in SensorHub.
    Concrete subclasses override only createSensor(),
    instead of duplicating the entire workflow.
*/
