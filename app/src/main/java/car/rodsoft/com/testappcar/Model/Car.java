package car.rodsoft.com.testappcar.Model;

import java.io.Serializable;

public class Car implements Serializable {

    private int id;
    private String name;
    private int year;
    private Double price;
    private int img ;

    public Car(){}

    public Car(int id,String name, Double price,int year ,int img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.year = year;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}