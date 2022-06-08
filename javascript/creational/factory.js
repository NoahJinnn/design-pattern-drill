class Vehicle {
  constructor(name) {
    this.name = name;
  }

  getName = () => {
      return this.name;
  }
}

class Car extends Vehicle {
  constructor(name) {
    super(name);
  }

  wheels = () => {
    return 2;
  };
}

class Truck extends Vehicle {
  constructor(name) {
    super(name);
  }

  wheels = () => {
    return 4;
  };
}

class AbstractFactory {
  #type = "Car";
  #origin;
  #date;
  #vehicle;

  constructor(type) {
    this.#type = type;
  }

  produce = (origin) => {
    this.#origin = origin;
    this.#date = new Date().getHours();
    let description = `${this.#type} from ${this.#origin} produced at: ${
      this.#date
    }`;
    if (this.#type === "Car") {
      this.#vehicle = new Car(description);
    } else if (this.#type === "Truck") {
      this.#vehicle = new Truck(description);
    } else {
        throw new Error("Abstract Factory does not know how to produce a vehicle");
    }
  };

  getType() {
    return this.#type;
  }

  getDate() {
    return this.#date;
  }

  getOrigin() {
    return this.#origin;
  }

  getVehicle() {
    return this.#vehicle;
  }
}

class HcmCarFactory extends AbstractFactory {
  constructor() {
    super("Car");
  }

  produce(origin) {
    return super.produce(origin);
  }
}

class HnTruckFactory extends AbstractFactory {
  constructor() {
    super("Truck");
  }

  produce(origin) {
    return super.produce(origin);
  }
}

module.exports = {
    Truck,
    Car,
    HcmCarFactory,
    HnTruckFactory
}