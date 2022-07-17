class Beverage {
  name = "";
  age;
  #ingredient;
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }

  addIngredient(i) {
    this.#ingredient = i;
  }
  description = () => {
    return this.name + this.age + this.#ingredient;
  }
}

class BeverageFactory {
  static ingredient = {
    cream: "cream",
    chocolate: "chocolate",
  };
  static getCream = (beverage) => {
    beverage.addIngredient(this.ingredient.cream);
    return beverage;
  };
  static getChocolate = (beverage) => {
    beverage.addIngredient(this.ingredient.chocolate);
    return beverage;
  };
}

module.exports = {
    Beverage,
    BeverageFactory
}