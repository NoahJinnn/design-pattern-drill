package creational

import (
	"fmt"
	"sync"
)

type singleton struct {
	Name string
}

var lock = &sync.Mutex{}
var instance *singleton

func GetInstance(name string) *singleton {
	if instance != nil {
		return instance
	}

	lock.Lock()
	defer lock.Unlock()
	if instance != nil {
		fmt.Println("Nil checking after acquiring lock")
		return instance
	}

	fmt.Printf("Instance must be a nil pointer here: %v\n", instance)
	instance = &singleton{
		Name: name,
	}

	return instance
}
