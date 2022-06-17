#[cfg(test)]
mod factory {
    trait Vehicle {
        fn new(name: &str) -> Self;
        fn get_name(&self) -> String;
        fn set_name(&mut self, name: &str);

        fn run() {
            println!("I'm running");
        }
    }

    trait Factory<T: Vehicle> {
        fn create_vehicle(&self, vehicle_name: &str) -> T;
        fn dispatch(&self, vehicle: &mut T) {
            vehicle.set_name("From factory");
        }
    }

    struct Car {
        name: String,
        wheels: u8,
    }

    struct Truck {
        name: String,
        weight: u8,
    }

    struct OldFactory {}

    impl<T: Vehicle> Factory<T> for OldFactory {
        fn create_vehicle(&self, vehicle_name: &str) -> T
        where
            T: Vehicle,
        {
            T::new(vehicle_name)
        }
    }

    impl Vehicle for Car {
        fn new(name: &str) -> Self {
            Car {
                name: name.to_owned(),
                wheels: 4,
            }
        }

        fn get_name(&self) -> String {
            format!("{} with {} wheels", self.name, self.wheels.to_string())
        }

        fn set_name(&mut self, name: &str) {
            self.name = format!("Car {} {}", &self.name, name);
        }
    }

    impl Vehicle for Truck {
        fn new(name: &str) -> Self {
            Truck {
                name: name.to_owned(),
                weight: 100,
            }
        }

        fn get_name(&self) -> String {
            format!(
                "{} is heavy boy with {} tons",
                self.name,
                self.weight.to_string()
            )
        }

        fn set_name(&mut self, name: &str) {
            self.name = format!("Truck {} {}", &self.name, name);
        }
    }

    #[test]
    fn test_factory() {
        let f = OldFactory {};
        let mut tr: Truck = f.create_vehicle("Monster");
        f.dispatch(&mut tr);
        let mut c: Car = f.create_vehicle("Porches");
        f.dispatch(&mut c);

        assert_eq!(tr.get_name(), "Truck Monster From factory is heavy boy with 100 tons");
        assert_eq!(c.get_name(), "Car Porches From factory with 4 wheels")
    }
}
