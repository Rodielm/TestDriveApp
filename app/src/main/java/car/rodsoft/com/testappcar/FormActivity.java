package car.rodsoft.com.testappcar;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import car.rodsoft.com.testappcar.Model.ServiceTest;

public class FormActivity extends AppCompatActivity {

    private String[] branch = {"Principal", "Galeria 360", "Agora", "Bella Vista Mall"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        Button sendEmail = findViewById(R.id.btnSend);
        TextView etName = findViewById(R.id.etName);
        TextView etCedula = findViewById(R.id.etCedula);
        TextView etEmail = findViewById(R.id.etEmail);
        TextView etPhone = findViewById(R.id.etPhone);
        Spinner etBranch = findViewById(R.id.spSucursales);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branch);
        etBranch.setAdapter(arrayAdapter);


        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceTest serviceTest = new ServiceTest();
                serviceTest.setName(etName.getText().toString());
                serviceTest.setDocument(etCedula.getText().toString());
                serviceTest.setEmail(etEmail.getText().toString());
                serviceTest.setPhone(etPhone.getText().toString());
                serviceTest.setBranch(etBranch.getSelectedItem().toString());

                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                String nameCar = getIntent().getStringExtra("carName");
                String messageformat = String.format("mailto:?subject=Test Drive en la surcusal %s &body=El Cliente %s con cedula %s desea probar el nuevo vehiculo %s &to=testdrive@autos.com"
                        , serviceTest.getBranch(), serviceTest.getName(), serviceTest.getDocument(), nameCar);
                Uri data = Uri.parse(messageformat);
                mailIntent.setData(data);
                try {
                    startActivity(Intent.createChooser(mailIntent, "Enviar Correo..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(),
                            "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}


