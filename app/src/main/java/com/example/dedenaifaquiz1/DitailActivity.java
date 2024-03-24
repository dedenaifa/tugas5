package com.example.dedenaifaquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import java.util.Locale;

public class DitailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ditail);

        Intent intent = getIntent();
        String customerName = intent.getStringExtra("customerName");
        String customerType = intent.getStringExtra("customerType");
        String productCode = intent.getStringExtra("productCode");
        int quantity = intent.getIntExtra("quantity", 0);

        String productName = getProductDetails(productCode);
        int productPrice = getProductPrice(productCode);
        int totalPrice = productPrice * quantity;

        double discount = calculateDiscount(totalPrice, customerType);
        double totalAfterDiscount = totalPrice - discount;

        TextView tvCustomerName = findViewById(R.id.tv_customer_name);
        TextView tvCustomerType = findViewById(R.id.tv_customer_type);
        TextView tvProductCode = findViewById(R.id.tv_product_code);
        TextView tvProductName = findViewById(R.id.tv_product_name);
        TextView tvProductPrice = findViewById(R.id.tv_product_price);
        TextView tvTotalPrice = findViewById(R.id.tv_total_price);
        TextView tvDiscount = findViewById(R.id.tv_discount);
        TextView tvTotalAfterDiscount = findViewById(R.id.tv_total_after_discount);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("id", "ID")); // Menggunakan format Rupiah
        tvCustomerName.setText(getText(R.string.SelamatDatang)+ customerName);
        tvCustomerType.setText(getText(R.string.TipePelanggan) + customerType);
        tvProductCode.setText(getText(R.string.KodeBarang) + productCode);
        tvProductName.setText(getText(R.string.HargaBarang) + productName);
        tvProductPrice.setText(getText(R.string.NamaBarang) + formatter.format(productPrice));
        tvTotalPrice.setText(getText(R.string.HargaBarang) + formatter.format(totalPrice));
        tvDiscount.setText(getText(R.string.DiskonHarga) + formatter.format(discount));
        tvDiscount.setText(getText(R.string.DiskonMember) + formatter.format(discount));
        tvTotalAfterDiscount.setText(getText(R.string.TotalHarga) + formatter.format(totalAfterDiscount) +
                "\n\nTerima kasih telah berbelanja di sini");

        Button btnShare = findViewById(R.id.btn_share);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(totalPrice, discount, totalAfterDiscount); // Panggil metode untuk berbagi data
            }
        });
    }

    // Metode untuk berbagi data
    private void shareData(int totalPrice, double discount, double totalAfterDiscount) {
        // Contoh berbagi teks
        String textToShare = "Total Harga: " + totalPrice +
                "\nDiskon: " + discount +
                "\nTotal Setelah Diskon: " + totalAfterDiscount;

        // Membuat intent untuk berbagi
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(shareIntent, "Bagikan via"));
    }

    private String getProductDetails(String productCode) {
        switch (productCode) {
            case "SGS":
                return "Samsung Galaxy S";
            case "PCO":
                return "POCO M3";
            case "AV4":
                return "Asus Vivobook 14";
            default:
                return "";
        }
    }

    private int getProductPrice(String productCode) {
        switch (productCode) {
            case "SGS":
                return 12999999;
            case "PCO":
                return 2730551;
            case "AV4":
                return 9150999;
            default:
                return 0;
        }
    }

    private double calculateDiscount(int totalPrice, String customerType) {
        double discount = 0;
        if (totalPrice > 10000000) {
            discount += 100000;
        }
        switch (customerType) {
            case "Gold":
                discount += totalPrice * 0.1;
                break;
            case "Silver":
                discount += totalPrice * 0.05;
                break;
            case "Biasa":
                discount += totalPrice * 0.02;
                break;
        }
        return discount;
    }
}
