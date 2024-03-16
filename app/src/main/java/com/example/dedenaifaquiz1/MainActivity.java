package com.example.dedenaifaquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnProcess = findViewById(R.id.btn_process);
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etCustomerName = findViewById(R.id.et_customer_name);
                RadioGroup radioGroup = findViewById(R.id.radio_group);
                RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
                EditText etProductCode = findViewById(R.id.et_product_code);
                EditText etQuantity = findViewById(R.id.et_quantity);

                String customerName = etCustomerName.getText().toString();
                String customerType = radioButton.getText().toString();
                String productCode = etProductCode.getText().toString().toUpperCase();
                int quantity = Integer.parseInt(etQuantity.getText().toString());

                Intent intent = new Intent(MainActivity.this, DitailActivity.class);
                intent.putExtra("customerName", customerName);
                intent.putExtra("customerType", customerType);
                intent.putExtra("productCode", productCode);
                intent.putExtra("quantity", quantity);
                startActivity(intent);
            }
        });
    }
}

