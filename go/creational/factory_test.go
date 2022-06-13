package creational

import "testing"

func TestFactory(t *testing.T) {
	f := &Factory{Name: "Factory"}
	car, err := f.GetVehicle("car")
	if err != nil {
		t.Error(err)
	}

	if car.GetName() != "Car4" {
		t.Errorf("Expected car.GetName() == Car4, but got %v", car.GetName())
	}

	truck, err := f.GetVehicle("truck")
	if err != nil {
		t.Error(err)
	}

	if truck.GetName() != "Truck" {
		t.Errorf("Expected truck.GetName() == Truck, but got %v", truck.GetName())
	}
}
