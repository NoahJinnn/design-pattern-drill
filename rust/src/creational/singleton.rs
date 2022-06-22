#[cfg(test)]
mod singleton {
    use lazy_static::lazy_static;

    // Ref: https://stackoverflow.com/questions/27791532/how-do-i-create-a-global-mutable-singleton/27826181#27826181
    struct Singleton {
        name: String,
    }

    impl Singleton {
        fn new(name: &str) -> Self {
            Singleton {
                name: name.to_owned(),
            }
        }

        pub fn get_name(&self) -> &str {
            return &self.name;
        }
    }

    lazy_static! {
        static ref INSTANCE: Singleton = Singleton::new("Config"); // this instance will be available in this scope only, to make it global we need to instantiate in main
    }

    #[test]
    fn test_singleton() {
        assert_eq!(INSTANCE.get_name(), "Config");
    }
}
