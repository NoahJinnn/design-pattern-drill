package implementation.creational;

/**
 * Factory decouples object creation with client code. There're 2 type of factory patterns:
 * 1. Factory method: Defer product creation for subclass of creator, both creator and product depends on abstraction of product;
 *    which calls Inversion of Dependency
 * 2. Abstract Factory: Provide interface for creating family of object; the objects is dynamically defined via composition
 */
public class Factory {
    void main() {
        HcmPizzaStore hcmPizzaStore = new HcmPizzaStore();
        Pizza myPizza = hcmPizzaStore.orderPizza("cheese"); // Improve passing type using enum, class to have more type safe
    }
}

abstract class Pizza {
    String name;
    IngredientFactory ingredientFactory;
    Dough dough;
    Sauce sauce;

    void prepare() {
        this.dough = this.ingredientFactory.createDough();
        this.sauce = this.ingredientFactory.createSauce();
        System.out.println(this.name + "prepared");
    }

    void slice() {
        System.out.println(this.name + "sliced");
    }

    void pack() {
        System.out.println(this.name + "packed");
    }
}

class SeaFoodPizza extends Pizza {
    public SeaFoodPizza(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
        this.name = "Sea food pizza";
    }
}

class CheesePizza extends Pizza {
    public CheesePizza(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
        this.name = "Cheesee pizza";
    }
}

// Implement Factory Method
abstract class PizzaStore {
    // Abstract factory method
    abstract Pizza createPizza(String type);

    public Pizza orderPizza(String type) {
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.slice();
        pizza.pack();
        return pizza;
    }
}

class HcmPizzaStore extends  PizzaStore {
    // Different stores use different family of ingredients
    IngredientFactory ingredientFactory = new HcmIngredientFactory();
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        if(type == "sf") {
            pizza = new SeaFoodPizza(ingredientFactory);
        } else if(type == "cheese") {
            pizza = new CheesePizza(ingredientFactory);
        } else {
            return null;
        }
        return pizza;
    }
}

class HnPizzaStore extends  PizzaStore {
    IngredientFactory ingredientFactory = new HnIngredientFactory();
    @Override
    Pizza createPizza(String type) {
        Pizza pizza;
        if(type == "sf") {
            pizza = new SeaFoodPizza(ingredientFactory);
        } else if(type == "cheese") {
            pizza = new CheesePizza(ingredientFactory);
        } else {
            return null;
        }
        return pizza;
    }
}

interface Dough {}
class HcmDough implements Dough {}
class HnDough implements Dough {}
interface Sauce {}
class HcmSauce implements Sauce {}
class HnSauce implements Sauce {}

// Abstract Factory
abstract class IngredientFactory {
    abstract Dough createDough();
    abstract Sauce createSauce();
    // ... Family of pizza's ingredients
}

class HcmIngredientFactory extends IngredientFactory {

    @Override
    Dough createDough() {
        return new HcmDough();
    }

    @Override
    Sauce createSauce() {
        return new HcmSauce();
    }
}

class HnIngredientFactory extends IngredientFactory {

    @Override
    Dough createDough() {
        return new HnDough();
    }

    @Override
    Sauce createSauce() {
        return new HnSauce();
    }
}




