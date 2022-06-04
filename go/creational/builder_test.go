package creational

import (
	"testing"
)

func TestBuilder(t *testing.T) {
	person := GetPersonBuilder().SetName("John").SetAge(30).Build()
	if person.name != "John" || person.age != 30 {
		t.Errorf("Expected person.name == John && person.age == 30, but got %v, %v", person.name, person.age)
	}
}
