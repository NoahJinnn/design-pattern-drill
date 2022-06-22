const {Singleton} = require("./singleton");

test("Singleton test", () => {
    let singleton1 = Singleton.getInstance("Singleton");
    expect(singleton1.getName()).toBe("Singleton");
    let singleton2 = Singleton.getInstance("FakeSingleton");
    expect(singleton2.getName()).toBe("Singleton");
  });
  