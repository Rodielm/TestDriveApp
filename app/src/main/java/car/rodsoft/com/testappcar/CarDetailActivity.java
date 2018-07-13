package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import car.rodsoft.com.testappcar.Model.Car;
import car.rodsoft.com.testappcar.Model.CarDetail;
import car.rodsoft.com.testappcar.Utils.CarUtils;

public class CarDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);

        Button btnForm = findViewById(R.id.btnForm);
        Car car = (Car) getIntent().getSerializableExtra("Car");
        CarDetail carDetail = CarUtils.findById(car.getId());

        TextView txName = findViewById(R.id.txtName);
        ImageView carImg = findViewById(R.id.imgCar);
        ListView lvDetails = findViewById(R.id.lvCarDetail);
        txName.setText(car.getName());

        carImg.setImageResource(car.getImg());

        ArrayAdapter carDetailsAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,carDetail.getDetails());
        lvDetails.setAdapter(carDetailsAdapter);

        btnForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent formActivity = new Intent(CarDetailActivity.this, FormActivity.class);
                formActivity.putExtra("carName", car.getName());
                startActivity(formActivity);
            }
        });

    }
}
