#[cfg(test)]
mod builder {
    pub struct Person {
        name: String,
        age: u8,
    }
    pub struct PersonBuilder {
        pub name: String,
        pub age: u8,
    }

    impl From<PersonBuilder> for Person {
        fn from(builder: PersonBuilder) -> Self {
            Person {
                name: builder.name,
                age: builder.age,
            }
        }
    }

    impl PersonBuilder {
        pub fn new() -> Self {
            PersonBuilder {
                name: String::new(),
                age: 0,
            }
        }

        pub fn set_name(mut self, name: &str) -> Self {
            self.name = name.to_string();
            self
        }

        pub fn set_age(mut self, age: u8) -> Self {
            self.age = age;
            self
        }

        pub fn build(self) -> Person {
            Person::from(self)
        }
    }

    impl Default for Person {
        fn default() -> Self {
            Person::from(PersonBuilder::default())
        }
    }

    impl Default for PersonBuilder {
        fn default() -> Self {
            PersonBuilder {
                name: String::from("John"),
                age: 18,
            }
        }
    }
    #[test]
    fn test_builder() {
        let person = PersonBuilder::new().set_name("John").set_age(18).build();
        assert_eq!(person.name, "John");
        assert_eq!(person.age, 18);
    }

    #[test]
    fn test_default() {
        let person = Person::default();
        assert_eq!(person.name, "John");
        assert_eq!(person.age, 18);
    }
}
