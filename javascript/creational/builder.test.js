const { Person } = require("./builder");

test("Person Builder test", () => {
  const person = Person.builder().setName("John").setAge(30).build();
  expect(person.getName()).toBe("John");
  expect(person.getAge()).toBe(30);
});
