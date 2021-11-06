package implementation.creational;

import lombok.Getter;

/**
 * Builder encapsulates the construction of object and allows it to construct in steps
 */
public class Builder {
    void main() {
        Person person = Person.builder().setMale(false).setAge(1).build();
    }
}

class Person {
    private String name;
    private int age;
    private boolean isMale;

    public Person(PersonBuilder builder) {
        this.name = builder.getName();
        this.age = builder.getAge();
        this.isMale = builder.isMale();
    }

    @Getter
    static class PersonBuilder {

        private String name;
        private int age;
        private boolean isMale;

        public PersonBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PersonBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder setMale(boolean male) {
            isMale = male;
            return this;
        }
        public Person build() {
            return new Person(this);
        }
    }

    static public PersonBuilder builder() {
        return new PersonBuilder();
    }
}