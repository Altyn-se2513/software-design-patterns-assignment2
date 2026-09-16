public class PartA {
    // 1part Factory Method)
    public static void run() {
        
        SensorHub tempHub = new TemperatureHub();
        tempHub.inspectArea();

        SensorHub motionHub = new MotionHub();
        motionHub.inspectArea();
    }
}

interface Sensor {
    void readData();
}

// prod1
class TemperatureSensor implements Sensor {
    @Override
    public void readData() {
        System.out.println("[Temp] Reading temperature: 22.5°C");
    }
}

// prod2
class MotionSensor implements Sensor {
    @Override
    public void readData() {
        System.out.println("[Motion] Motion detected in living room!");
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

class TemperatureHub extends SensorHub {
    @Override
    public Sensor createSensor() {
        return new TemperatureSensor();
    }
}

class MotionHub extends SensorHub {
    @Override
    public Sensor createSensor() {
        return new MotionSensor();
    }
}