package creational

type Person struct {
	name string
	age  int
}

type PersonBuilder struct {
	person *Person
}

func GetPersonBuilder() *PersonBuilder {
	return &PersonBuilder{&Person{}}
}

func (pb *PersonBuilder) SetName(name string) *PersonBuilder {
	pb.person.name = name
	return pb
}

func (pb *PersonBuilder) SetAge(age int) *PersonBuilder {
	pb.person.age = age
	return pb
}

func (pb *PersonBuilder) Build() *Person {
	return pb.person
}
