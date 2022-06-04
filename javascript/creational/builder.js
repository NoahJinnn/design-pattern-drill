class Person {
  #name;
  #age;

  constructor(name, age) {
    this.#name = name;
    this.#age = age;
  }

  getName() {
    return this.#name;
  }
  getAge() {
    return this.#age;
  }
  static builder() {
    return new PersonBuilder();
  }
}

class PersonBuilder {
  #date;
  #name;
  #age;
  constructor() {
    this.#date = Date.now();
  }

  setName(name) {
    this.#name = name;
    return this;
  }

  setAge(age) {
    this.#age = age;
    return this;
  }


  build() {
    let p = new Person(this.#name, this.#age);
    console.log(`Build ${p.getName()} at: ${this.#date}`);
    return p;
  }
}



module.exports = {
  Person,
  PersonBuilder
}