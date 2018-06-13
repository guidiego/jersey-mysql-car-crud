package br.com.carlist.car;

public class Car {
    public Integer id = null;
    public String name;
    public String type;

    public Car() {

    }

    public Car(int id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
