package implementation.structural;

/**
 * Adapter pattern convert one interface to another
 * Facade pattern simplify interface of system
 */
public class AdapterAndFacade {
    void main() {
        // Adapter
        IOldWorker blueCollar = new BlueCollarWorker();
        NewOldWorkerAdapter whiteCollar = new NewOldWorkerAdapter(blueCollar);
        whiteCollar.workEfficiently("Build web app");

        // Facade
        Conveyor conveyor = new Conveyor();
        PackingMachine packingMachine = new PackingMachine();
        FactoryFacade factoryFacade = new FactoryFacade(conveyor, packingMachine);
        factoryFacade.startAssembly();
    }
}

// Adapter pattern
interface IOldWorker {
    void work9to5(String tasks);
}

class BlueCollarWorker implements IOldWorker {
    @Override
    public void work9to5(String tasks) {
        System.out.println("Work 5 day to complete: " + tasks);
    }
}

interface INewWorker {
    void workEfficiently(String tasks);
    void relax();
}

class NewOldWorkerAdapter implements INewWorker {

    IOldWorker oldWorker;

    public NewOldWorkerAdapter(IOldWorker oldWorker) {
        this.oldWorker = oldWorker;
    }

    @Override
    public void workEfficiently(String tasks) {
        // ... adjust tasks so that old worker can work efficiently
        oldWorker.work9to5(tasks);
    }

    @Override
    public void relax() {
        System.out.println("Old worker doesn't need to relax");
    }
}

// ---------------------
// Facade pattern
class Conveyor {
    void start() {
        System.out.println("Run conveyor");
    }
}

class PackingMachine {
    void start() {
        System.out.println("Run machine");
    }
}

class FactoryFacade {
    Conveyor conveyor;
    PackingMachine packingMachine;

    public FactoryFacade(Conveyor conveyor, PackingMachine packingMachine) {
        this.conveyor = conveyor;
        this.packingMachine = packingMachine;
    }

    void startAssembly() {
        System.out.println("Turn on power");
        conveyor.start();
        packingMachine.start();
    }
}

