package car.rodsoft.com.testappcar.Utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import car.rodsoft.com.testappcar.Model.Car;
import car.rodsoft.com.testappcar.Model.CarDetail;
import car.rodsoft.com.testappcar.R;

public class CarUtils {


    private static List<CarDetail> carDetailsG = getDummyCarDetail();

    public static Double randomWithRange(int min, int max) {
        DecimalFormat decimalFormat = new DecimalFormat(".##");
        int range = (max - min) + 1;
        return Double.valueOf(decimalFormat.format((Math.random() * range) + min));
    }

    public static List<Car> getDummyCars() {

        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1, "Toyota Corolla SE", 21900.00,2017 , R.drawable.car_toyota));
        cars.add(new Car(2, "Mazda CX-5", 37000.00,2017 ,R.drawable.car_mazda));
        cars.add(new Car(3, "Chevrolet Tahoe", 30000.00, 2011 ,R.drawable.car_chevrolet));
        cars.add(new Car(4,"Jeep Grand Cherokee Trackhawk", 150000.00,2018 ,R.drawable.car_jeep));
        cars.add(new Car(5, "Mini Cooper Countryman", 15450.00,2011 ,R.drawable.car_mini));
        cars.add(new Car(6, "Chevrolet Camaro S S", 42900.00,2017 ,R.drawable.car_camaro));
        cars.add(new Car(7, "Mazda 6", 16224.50,2014 ,R.drawable.car_mazda6));
        cars.add(new Car(8, "Chevrolet Camaro ZL1", 110000.00,2018,R.drawable.car_camarozl));
        cars.add(new Car(9, "Dodge Challenger", 57000.00,2018,R.drawable.car_challenger));
        cars.add(new Car(10, "Dodge Challenger RT", 28900.00,2014 ,R.drawable.challengerrt));

        return cars;
    }

    public static List<CarDetail> getDummyCarDetail() {
        List<CarDetail> carsDetails = new ArrayList<>();
        carsDetails.add(new CarDetail(1, new String[]{
                "Precio: US$ 21,900",
                "Motor: 1.8, 4 cilindros",
                "Exterior: Rojo",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: Delantera",
                "Tipo: Sedán",
                "Puertas: 5",
                "Pasajeros: 5"
        }, R.drawable.car_toyota));
        carsDetails.add(new CarDetail(2, new String[]{
                "Precio: US$ 37,000",
                "Motor: 2.0 4 cilindros",
                "Exterior: Negro",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: 4WD",
                "Tipo: Jeep",
                "Puertas: 5",
                "Pasajeros: 5"
        }, R.drawable.car_mazda));
        carsDetails.add(new CarDetail(3, new String[]{
                "Precio: US$ 19,800",
                "Motor: 5.3, 8 cilindros",
                "Exterior: Blanco",
                "Interior: Beige",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: Trasera",
                "Tipo: Jeep",
                "Puertas: 5",
                "Pasajeros: 7"
        }, R.drawable.car_chevrolet));
        carsDetails.add(new CarDetail(4, new String[]{
                "Precio: US$ 150,000",
                "Motor: 6.2, 4 cilindros, Supercharged",
                "Exterior: Blanco",
                "Interior: Rojo",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: 4WD",
                "Tipo: Jeep",
                "Puertas: 5",
                "Pasajeros: 5"
        }, R.drawable.car_jeep));
        carsDetails.add(new CarDetail(5, new String[]{
                "Precio: US$ 15,450.00",
                "Motor: 1.6, 4 cilindros",
                "Exterior: Blanco",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Mecanica",
                "Tracción: 4WD Full Time",
                "Tipo: Coupe/Deportivo",
                "Puertas: 5",
                "Pasajeros: 4"
        }, R.drawable.car_mini));
        carsDetails.add(new CarDetail(6, new String[]{
                "Precio: US$ 42,900",
                "Motor: 6.2, 8 cilindros",
                "Exterior: Amarillo",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: Trasera",
                "Tipo: Coupe/Deportivo",
                "Puertas: 5",
                "Pasajeros: 4"
        }, R.drawable.car_camaro));
        carsDetails.add(new CarDetail(7, new String[]{
                "Precio: US$ 16,224.50",
                "Motor: 2.2, 4 cilindros",
                "Exterior: Gris Plata",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: 2WD",
                "Tipo: Sedán",
                "Puertas: 4",
                "Pasajeros: 5"
        }, R.drawable.car_mazda6));
        carsDetails.add(new CarDetail(8, new String[]{
                "Precio: US$ 110,000",
                "Motor: 8 cilindros, Supercharged",
                "Exterior: Azul",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: Trasera",
                "Tipo: Coupé/Deportivo",
                "Puertas: 2",
                "Pasajeros: 4"
        }, R.drawable.car_camarozl));
        carsDetails.add(new CarDetail(9, new String[]{
                "Precio: US$ 57,000",
                "Motor: 3.6, 6 cilindros",
                "Exterior: Gris oscuro",
                "Interior: Negro",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: Trasera",
                "Tipo: Coupé/Deportivo",
                "Puertas: 2",
                "Pasajeros: 4"
        }, R.drawable.car_challenger));
        carsDetails.add(new CarDetail(10, new String[]{
                "Precio: US$ 28,900",
                "Motor: 3.5, 6 cilindros",
                "Exterior: Blanco",
                "Interior: Marrón",
                "Combustible: Gasolina",
                "Transmisión: Automática",
                "Tracción: 2WD",
                "Tipo: Coupé/Deportivo",
                "Puertas: 2",
                "Pasajeros: 5"
        }, R.drawable.challengerrt));


        return carsDetails;
    }

    public static CarDetail findById(int id) {

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            return carDetailsG.stream().filter(item -> item.getId() == id).findFirst().get();
//        } else {
            CarDetail carDetail = new CarDetail();
            for (CarDetail item : carDetailsG) {
                if (item.getId() == id) {
                    carDetail = item;
                }
            }
            return carDetail;
//        }
    }


}
