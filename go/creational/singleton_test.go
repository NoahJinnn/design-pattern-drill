package creational

import "testing"

func TestSingleton(t *testing.T) {
	s := GetInstance("Config")

	if s.Name != "Config" {
		t.Errorf("Expected s.Name == Config, but got %v", s.Name)
	}
}
