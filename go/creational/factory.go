package creational

import (
	"errors"
	"strconv"
)

type Vehicle interface {
	GetName() string
}

type Car struct {
	Name       string
	WheelCount int
}

type Truck struct {
	Name string
}

type Factory struct {
	Name string
}

func (c *Car) GetName() string {
	return c.Name + strconv.Itoa(c.WheelCount)
}

func (t *Truck) GetName() string {
	return t.Name
}

func (f *Factory) GetVehicle(v string) (Vehicle, error) {
	switch v {
	case "car":
		return &Car{Name: "Car", WheelCount: 4}, nil
	case "truck":
		return &Truck{Name: "Truck"}, nil
	default:
		return nil, errors.New("Vehicle not found")
	}
}
