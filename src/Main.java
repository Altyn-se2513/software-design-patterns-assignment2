// Abstract Factory Pattern

// Abstract Product 1
interface SmartLight {
    void turnOn();
}

// Abstract Product 2
interface SmartLock {
    void lock();
}

// Concrete Product: Apple family
class AppleLight implements SmartLight {
    @Override
    public void turnOn() {
        System.out.println("Apple HomeKit light turned on.");
    }
}

class AppleLock implements SmartLock {
    @Override
    public void lock() {
        System.out.println("Apple HomeKit lock locked.");
    }
}

// Concrete Product: Google family
class GoogleLight implements SmartLight {
    @Override
    public void turnOn() {
        System.out.println("Google Home light turned on.");
    }
}

class GoogleLock implements SmartLock {
    @Override
    public void lock() {
        System.out.println("Google Home lock locked.");
    }
}

// Abstract Factory
interface SmartHomeFactory {
    SmartLight createLight();

    SmartLock createLock();
}

// Concrete Factory 1
class AppleHomeKitFactory implements SmartHomeFactory {

    @Override
    public SmartLight createLight() {
        return new AppleLight();
    }

    @Override
    public SmartLock createLock() {
        return new AppleLock();
    }
}

// Concrete Factory 2
class GoogleHomeFactory implements SmartHomeFactory {

    @Override
    public SmartLight createLight() {
        return new GoogleLight();
    }

    @Override
    public SmartLock createLock() {
        return new GoogleLock();
    }
}

// Platform options
enum SmartHomePlatform {
    APPLE,
    GOOGLE
}

// Factory Provider
class SmartHomeFactoryProvider {

    private SmartHomeFactoryProvider() {
    }

    public static SmartHomeFactory createFactory(SmartHomePlatform platform) {
        switch (platform) {
            case APPLE:
                return new AppleHomeKitFactory();

            case GOOGLE:
                return new GoogleHomeFactory();

            default:
                throw new IllegalArgumentException(
                        "Unsupported smart home platform: " + platform
                );
        }
    }
}

// Client
class SmartHomeController {

    private final SmartLight light;
    private final SmartLock lock;

    public SmartHomeController(SmartHomeFactory factory) {
        this.light = factory.createLight();
        this.lock = factory.createLock();
    }

    public void activateNightMode() {
        light.turnOn();
        lock.lock();
    }
}

// Main class
public class Main {

    public static void main(String[] args) {

        System.out.println("PART A: Factory Method");

        SensorHub temperatureHub = new TemperatureHub();
        temperatureHub.inspectArea();

        SensorHub motionHub = new MotionHub();
        motionHub.inspectArea();

        System.out.println();

        System.out.println("PART B: Abstract Factory");

        SmartHomeFactory appleFactory =
                SmartHomeFactoryProvider.createFactory(
                        SmartHomePlatform.APPLE
                );

        SmartHomeController appleHome =
                new SmartHomeController(appleFactory);

        System.out.println("Apple Home:");
        appleHome.activateNightMode();

        System.out.println();

        SmartHomeFactory googleFactory =
                SmartHomeFactoryProvider.createFactory(
                        SmartHomePlatform.GOOGLE
                );

        SmartHomeController googleHome =
                new SmartHomeController(googleFactory);

        System.out.println("Google Home:");
        googleHome.activateNightMode();
    }
}
