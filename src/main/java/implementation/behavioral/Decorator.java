package implementation.behavioral;
/**
 * Decorator pattern helps attach additional responsibilities to an object dynamically
 * Sastify Open-Closed principle
 */
public class Decorator {
    void main() {
        Beverage cafe = new Cafe();
        cafe = new Milk(cafe); // become milk coffee
        cafe = new Cream(cafe); // become milk cream coffee
    }
}

abstract class Beverage {
    abstract String description();
    abstract int cost();
}

// I am decorator, i decorate beverage
abstract class BeverageCondiment extends Beverage {
    Beverage beverage;
}

class Cafe extends Beverage {

    @Override
    String description() {
        return "Coffe";
    }

    @Override
    int cost() {
        return 20;
    }
}

class Cream extends BeverageCondiment {

    public Cream(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    String description() {
        return "Cream" + this.beverage.description();
    }

    @Override
    int cost() {
        return 5 + this.beverage.cost();
    }
}

class Milk extends BeverageCondiment {

    public Milk(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    String description() {
        return "Milk" + this.beverage.description();
    }

    @Override
    int cost() {
        return 10 + this.beverage.cost();
    }
}