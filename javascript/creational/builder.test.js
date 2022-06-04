const { Person } = require("./Builder");

test("Returns about-us for english language", () => {
  const person = Person.builder().setName("John").setAge(30).build();
  expect(person.getName()).toBe("John");
  expect(person.getAge()).toBe(30);
});
