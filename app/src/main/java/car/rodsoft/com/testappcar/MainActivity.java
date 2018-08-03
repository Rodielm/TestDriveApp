package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import car.rodsoft.com.testappcar.Adapters.RecyclerAdapterCarItem;
import car.rodsoft.com.testappcar.Model.Car;

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
        Intent addCar = new Intent(MainActivity.this, CarActivity.class);
        startActivity(addCar);
    }

    private void initCars() {
        Log.d(TAG, "initCars");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cars");
        RecyclerAdapterCarItem adapter = new RecyclerAdapterCarItem(this, cars);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Car car = ds.getValue(Car.class);
                    cars.add(car);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        myRef.addListenerForSingleValueEvent(eventListener);
        initRecyclerView(adapter);
    }


    private void initRecyclerView(RecyclerAdapterCarItem adapter) {
        Log.d(TAG, "initRecyclerView: init recyclerview");
        RecyclerView recyclerView = findViewById(R.id.listCarsRecycle);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
