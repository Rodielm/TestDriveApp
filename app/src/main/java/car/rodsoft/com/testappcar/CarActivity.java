package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import car.rodsoft.com.testappcar.Model.Car;

public class CarActivity extends AppCompatActivity {

    private EditText editModel;
    private EditText editPrice;
    private EditText editURL;
    private Button btnSave;
    private Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_car);

        editModel = findViewById(R.id.edmodel);
        editPrice = findViewById(R.id.edPrice);
        editURL = findViewById(R.id.edUrl);
        btnSave = findViewById(R.id.btnSave);
        btnCancel = findViewById(R.id.btnCancel);

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

    private void addCar() {
        Car car = new Car();
        String model = String.valueOf(editModel.getText());
        Double price = Double.valueOf(String.valueOf(editPrice.getText()));
        car.setName(model);
        car.setPrice(price);
        saveCar(car);
    }

    private void saveCar(Car car) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("cars");
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
    }

    private void cancelActivity() {
        Intent mainActivity = new Intent(CarActivity.this, MainActivity.class);
        startActivity(mainActivity);
    }

    private void makeMessage(String msj) {
        Toast.makeText(getApplicationContext(), msj, Toast.LENGTH_LONG).show();
    }
}
