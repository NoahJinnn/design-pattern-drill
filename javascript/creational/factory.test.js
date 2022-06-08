const {Car, Truck, HcmCarFactory, HnTruckFactory} = require("./factory");

test("Vehicle Factory test", () => {
    const hcmFactory = new HcmCarFactory();
    const hnFactory = new HnTruckFactory();
    hcmFactory.produce("Hcm");
    hnFactory.produce("Hn");
    const hcmCar = hcmFactory.getVehicle();
    const hnTruck = hnFactory.getVehicle();
    console.log(hcmCar.getName());
    console.log(hnTruck.getName());
    expect(hcmCar.getName()).toBe(`Car from Hcm produced at: ${new Date().getHours()}`);
    expect(hnTruck.getName()).toBe(`Truck from Hn produced at: ${new Date().getHours()}`);
  });
  