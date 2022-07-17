const { Sample, Prototype } = require("./prototype")

test("Test Prototype", () => {
    let sample = new Sample("My sample");
    let clone = Prototype.clone(sample);
    // console.log(clone)
    // expect(clone.name()).toBe("My sample");
    expect(clone === sample).toBe(false);
})