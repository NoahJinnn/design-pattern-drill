package implementation.behavioral;

/**
 * Strategies is seem to be algorithms to solve problems around an object.
 * The idea is that we split our algorithm implementation (which changes frequently bc of requirements) from our main object implementation.
 * This applies OO principles: Encapsulate what varies, Prefer composition over inheritance, Program to interface over implementation
 */
public class Strategy {
    void main() {
        Duck fineToy = new DuckToy();
        fineToy.doQuack(); // default quack
        fineToy.setQuackable(new NoisyQuack());
        fineToy.doQuack(); // start quacking hardly
    }
}

// This is an abstraction of main objects
abstract class Duck {
    IQuackable quackable = new SilentQuack(); // default algorithm
    IFlyable flyable = new TerribleFly();
    public void setQuackable(IQuackable quackable) {
        this.quackable = quackable;
    }
    public void setFlyable(IFlyable flyable) {
        this.flyable = flyable;
    }
    public void doQuack() {
        quackable.quack();
    }
    public void doFly() {
        flyable.fly();
    }
    public void display() {
        System.out.println("I have wings, eyes, legs");
    }
}

// Quack algorithm abstraction
interface IQuackable {
    public void quack();
}

class SilentQuack implements IQuackable {
    @Override
    public void quack() {
        System.out.println("I can't quack");
    }
}

class NoisyQuack implements IQuackable {
    @Override
    public void quack() {
        System.out.println("I quack as hell");
    }
}

// Fly algorithm abstraction
interface IFlyable {
    public void fly();
}

class TerribleFly implements IFlyable {
    @Override
    public void fly() {
        System.out.println("Can't fly for sure");
    }
}

class PilotFly implements IFlyable {
    @Override
    public void fly() {
        System.out.println("Fly to the moon");
    }
}

class DuckToy extends Duck {

    @Override
    public void display() {
        System.out.println("I'm a fine toy");
    }

}