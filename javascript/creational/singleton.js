class Singleton {
  static #INSTANCE = null;
  static #name = "";

  static getInstance(name) {
    if (this.#INSTANCE === null) {
      this.#name = name;
      this.#INSTANCE = Object.freeze(new Singleton());
    }
    return this.#INSTANCE;
  }

  getName() {
    return Singleton.#name;
  }
}

module.exports = { Singleton };
