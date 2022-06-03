package implementation.creational;

public class Singleton {
    private volatile Singleton instance;
    private Singleton() {}
    public Singleton getInstance() {
        synchronized (this) {
            if(this.instance == null) {
                this.instance = new Singleton();
            }
        }
        return this.instance;
    }
}
