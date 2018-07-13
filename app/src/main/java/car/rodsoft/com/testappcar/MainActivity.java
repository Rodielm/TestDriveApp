package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import car.rodsoft.com.testappcar.Adapters.AdapterCarItem;
import car.rodsoft.com.testappcar.Model.Car;
import car.rodsoft.com.testappcar.Utils.CarUtils;

public class MainActivity extends AppCompatActivity {

    private List<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        for (int i = 0; i < 10; i++) {
//            Car car = new Car("Car" + i,
//                    CarUtils.randomWithRange(1000, 4000),
//                    i);
//            cars.add(car);
//        }

        cars = CarUtils.getDummyCars();
        final ListView listCars = findViewById(R.id.listCars);
        listCars.setAdapter(new AdapterCarItem(this, cars));
        listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Car car = (Car) listCars.getAdapter().getItem(position);
//                Toast.makeText(getApplicationContext(), car.getName(), Toast.LENGTH_LONG).show();
                Intent carDetailsActivity = new Intent(MainActivity.this, CarDetailActivity.class);
                carDetailsActivity.putExtra("Car", car);
                startActivity(carDetailsActivity);
            }
        });

    }



}
