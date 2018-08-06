package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private Car oldCar = new Car();

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


        editURL.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String urlImage = String.valueOf(editURL.getText());
                Glide.with(getApplicationContext())
                        .asBitmap()
                        .load(urlImage)
                        .into(imgCar);
            }
        });

        initCarIfExist();
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

    private void initCarIfExist() {
        if (getIntentSerializable("car") != null) {
            oldCar = (Car) getIntentSerializable("car");
            setValueToField(oldCar);
            isNewCar = false;
            btnSave.setText("Update");
            getIntent().removeExtra("car");
        }else{
            isNewCar = true;
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
        Car newCar = new Car();
        String model = String.valueOf(editModel.getText());
        Double price = Double.valueOf(String.valueOf(editPrice.getText()));
        int year = Integer.valueOf(String.valueOf(editYear.getText()));
        String urlImg = String.valueOf(editURL.getText());

        newCar.setName(model);
        newCar.setPrice(price);
        newCar.setUrlImage(urlImg);
        newCar.setYear(year);
        saveCar(newCar, isNewCar);
    }

    private void saveCar(Car car, Boolean isNewCar) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cars");
        if (isNewCar) {
            String id = myRef.push().getKey();
            car.setId(id);
            myRef.child(id).setValue(car, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if (databaseError != null) {
                        makeMessage("Data could not be saved");
                    } else {
                        makeMessage("Data save successfully");
                    }
                }
            });
            clearField();
        } else {

            Map<String, Object> udpateCar = new HashMap<>();
            udpateCar.put("name", car.getName());
            udpateCar.put("price",car.getPrice());
            udpateCar.put("year", car.getYear());
            udpateCar.put("urlImage", car.getUrlImage());

            myRef.child(oldCar.getId()).updateChildren(udpateCar, new DatabaseReference.CompletionListener() {
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

    private void clearField(){
        editModel.setText("");
        editPrice.setText("");
        editURL.setText("");
        editYear.setText("");
    }
}
