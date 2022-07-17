// const structuredClone = require("@ungap/structured-clone");

class Prototype {
  static clone = (source) => {
    let target = structuredClone(source);
    let descriptors = Object.keys(source).reduce((descriptors, key) => {
        descriptors[key] = Object.getOwnPropertyDescriptor(source, key);
        return descriptors;
      }, {});
  
      // By default, Object.assign copies enumerable Symbols, too
      Object.getOwnPropertySymbols(source).forEach(sym => {
        let descriptor = Object.getOwnPropertyDescriptor(source, sym);
        if (descriptor.enumerable) {
          descriptors[sym] = descriptor;
        }
      });
      Object.defineProperties(target, descriptors);
      return target;
  };
}

class Sample {
  name;
  constructor(name) {
    this.name = name;
  }

  get name() {
    return this.name;
  }
}

module.exports = {
  Prototype,
  Sample,
};
