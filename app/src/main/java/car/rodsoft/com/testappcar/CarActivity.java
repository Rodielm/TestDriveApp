package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import car.rodsoft.com.testappcar.Model.Car;

public class CarActivity extends AppCompatActivity {

    private EditText editModel;
    private EditText editPrice;
    private EditText editURL;
    private EditText editYear;
    private ImageView imgCar;
    private boolean isNewCar;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        editModel = findViewById(R.id.edmodel);
        editPrice = findViewById(R.id.edPrice);
        editURL = findViewById(R.id.edUrl);
        editYear = findViewById(R.id.etYear);

        imgCar = findViewById(R.id.imgCar);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

        initCar();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCar();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelActivity();
            }
        });
    }

    private void initCar() {
        if (getIntentSerializable("car") != null) {
            Car car = (Car) getIntentSerializable("car");
            setValueToField(car);
            isNewCar = false;
            btnSave.setText("Update");
        }
    }

    private Serializable getIntentSerializable(String name) {
        return getIntent().getSerializableExtra(name);
    }

    private void setValueToField(Car car) {
        editModel.setText(car.getName());
        editPrice.setText(car.getPrice().toString());
        editURL.setText(car.getUrlImage());
        editYear.setText(String.valueOf(car.getYear()));
        Glide.with(getApplicationContext())
                .asBitmap()
                .load(car.getUrlImage())
                .into(imgCar);

    }

    private void addCar() {
        Car car = new Car();
        String model = String.valueOf(editModel.getText());
        Double price = Double.valueOf(String.valueOf(editPrice.getText()));
        int year = Integer.valueOf(String.valueOf(editYear.getText()));
        String urlImg = String.valueOf(editURL.getText());
        car.setName(model);
        car.setPrice(price);
        car.setUrlImage(urlImg);
        car.setYear(year);
        saveCar(car, isNewCar);
    }

    private void saveCar(Car car, Boolean isNewCar) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cars");
        if (isNewCar) {
            myRef.push().setValue(car, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        makeMessage("Data could not be saved");
                    } else {
                        makeMessage("Data save successfully");
                    }
                }
            });
        } else {
            Map<String, Object> udpateCar = new HashMap<>();
            udpateCar.put("name", car.getName());
            udpateCar.put("price", car.getPrice());
            udpateCar.put("year", car.getYear());
            udpateCar.put("urlImage", car.getUrlImage());

            myRef.updateChildren(udpateCar, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        makeMessage("Data could not be saved");
                    } else {
                        makeMessage("Data save successfully");
                    }
                }
            });
        }
    }

    private void cancelActivity() {
        Intent mainActivity = new Intent(CarActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    private void makeMessage(String msj) {
        Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_LONG).show();
    }
}
