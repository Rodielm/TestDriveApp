package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import car.rodsoft.com.testappcar.Adapters.AdapterCarItem;
import car.rodsoft.com.testappcar.Adapters.RecyclerAdapterCarItem;
import car.rodsoft.com.testappcar.Model.Car;
import car.rodsoft.com.testappcar.Utils.CarUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private List<Car> cars = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

        initCars();

//        final ListView listCars = findViewById(R.id.listCars);
        final FloatingActionButton addCar = findViewById(R.id.addCar);
        addCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity();
            }
        });
//        listCars.setAdapter(new AdapterCarItem(this, cars));
//        listCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Car car = (Car) listCars.getAdapter().getItem(position);
////                Toast.makeText(getApplicationContext(), car.getName(), Toast.LENGTH_LONG).show();
//                Intent carDetailsActivity = new Intent(MainActivity.this, CarDetailActivity.class);
//                carDetailsActivity.putExtra("Car", car);
//                startActivity(carDetailsActivity);
//            }
//        });
    }

    public void openActivity() {
        Intent addCar = new Intent(MainActivity.this, AddCarActivity.class);
        startActivity(addCar);
    }

    private void initCars() {
        Log.d(TAG, "initCars");
        cars = CarUtils.getDummyCars();
        initRecyclerView();
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.listCarsRecycle);
        RecyclerAdapterCarItem adapter = new RecyclerAdapterCarItem(this, cars);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
