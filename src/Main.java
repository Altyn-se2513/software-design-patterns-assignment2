public class Main {
    public static void main(String[] args) {
        PartA.run();

        System.out.println("\n=== PART B: Abstract Factory ===");
        
        SmartHomeFactory appleFactory = new AppleHomeKitFactory();
        SmartHomeController appleHome = new SmartHomeController(appleFactory);
        appleHome.activateNightMode();

        SmartHomeFactory googleFactory = new GoogleHomeFactory();
        SmartHomeController googleHome = new SmartHomeController(googleFactory);
        googleHome.activateNightMode();
    }
}


interface SmartLight {
    void turnOn();
}

interface SmartLock {
    void lock();
}

class AppleLight implements SmartLight {
    @Override
    public void turnOn() {
        System.out.println("Apple HomeKit: Light turned on via Siri.");
    }
}

class AppleLock implements SmartLock {
    @Override
    public void lock() {
        System.out.println("Apple HomeKit: Door locked securely.");
    }
}

class GoogleLight implements SmartLight {
    @Override
    public void turnOn() {
        System.out.println("Google Home: Light turned on via Google Assistant.");
    }
}

class GoogleLock implements SmartLock {
    @Override
    public void lock() {
        System.out.println("Google Home: Door locked.");
    }
}

interface SmartHomeFactory {
    SmartLight createLight();
    SmartLock createLock();
}

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
