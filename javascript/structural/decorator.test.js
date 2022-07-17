const { Beverage, BeverageFactory } = require("./decorator");

test('Decorator test', () => {
    let bev = new Beverage("wine", 1);
    let creamBev = BeverageFactory.getCream(bev);
    expect(creamBev.description()).toBe("wine1cream")
});