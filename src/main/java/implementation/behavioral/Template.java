package implementation.behavioral;

/**
 * Template defines outline for algorithm, reuse base algorithm, let subclasses implement algorithm steps without changing algorithm structure
 * Template combines 3 element: concrete method - base algorithm, primary method - algorithm implemented by subclass, hook - optional algorithm
 */
public class Template {

    void main() {
        CafeRecipe cafeRecipe = new CafeRecipe();
        cafeRecipe.getCustomerAns("Y");
        cafeRecipe.makeDrink();
    }
}

abstract class CaffeinBeverageRecipeTemplate {
    // Algorithm outline
    void makeDrink() {
        brew();
        baseStep();
        if(isCustomerWantAdditionalCondiment()) {
            addCondiment();
        }
    }

    // base
    void baseStep() {
        System.out.println("Boil water");
        System.out.println("Pour water");
    }

    // primary
    abstract void brew();
    abstract void addCondiment();

    // hook
    boolean isCustomerWantAdditionalCondiment() {
        return true;
    }
}

class TeaRecipe extends CaffeinBeverageRecipeTemplate {
    @Override
    void brew() {
        System.out.println("Brew tea leaves");
    }

    @Override
    void addCondiment() {
        System.out.println("Add sugar");
    }
}

class CafeRecipe extends  CaffeinBeverageRecipeTemplate {

    boolean customerAns;

    @Override
    void brew() {
        System.out.println("Brew cafe beans");
    }

    @Override
    void addCondiment() {
        System.out.println("Add milk");
    }

    void getCustomerAns(String ans) {
        if(ans == "Y") {
            this.customerAns = true;
        } else this.customerAns = false;
    }

    @Override
    boolean isCustomerWantAdditionalCondiment() {
        return this.customerAns;
    }
}