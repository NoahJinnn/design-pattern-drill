class Person {
  #name;
  #age;
  #job;

  constructor(name, age, job) {
    this.#name = name;
    this.#age = age;
    this.#job = job;
  }

  getName() {
    return this.#name;
  }
  getJob() {
    return this.#job;
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
  #job;
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

  setJob(job) {
    this.#job = job;
    return this;
  }

  build() {
    let p = new Person(this.#name, this.#age, this.#job);
    console.log(`Build ${p.getName()} at: ${this.#date}`);
    return p;
  }
}

const person = Person.builder()
  .setName("John")
  .setAge(30)
  .setJob("Developer")
  .build();
console.log(person.getName());
