package com.jiyoung.andstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jiyoung.andstudy.R;
import com.jiyoung.andstudy.database.MyDBHandler;
import com.jiyoung.andstudy.database.Product;

import org.w3c.dom.Text;

public class DatabaseActivity extends AppCompatActivity {

    EditText etProductName;
    EditText etQuantity;
    TextView tvProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        etProductName = (EditText) findViewById(R.id.productName);
        etQuantity = (EditText) findViewById(R.id.productQuantity);
        tvProductId = (TextView) findViewById(R.id.productID);

        Button btnAdd = (Button) findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName = etProductName.getText().toString();
                int quantity = Integer.parseInt(etQuantity.getText().toString());
                Product product = new Product(productName, quantity);

                MyDBHandler myDBHandler = new MyDBHandler(DatabaseActivity.this);
                myDBHandler.addProduct(product);

                etProductName.setText("");
                etQuantity.setText("");
            }
        });

        Button btnFind = (Button) findViewById(R.id.btn_find);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName = etProductName.getText().toString();

                MyDBHandler dbHandler = new MyDBHandler(DatabaseActivity.this);
                Product product = dbHandler.findProduct(productName);

                if (product != null) {
                    tvProductId.setText(String.valueOf(product.getID()));
                    etQuantity.setText(String.valueOf(product.getQuantity()));
                } else {
                    tvProductId.setText("No Match Found");
                }
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btn_delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String productName = etProductName.getText().toString();

                MyDBHandler dbHandler = new MyDBHandler(DatabaseActivity.this);
                boolean result = dbHandler.deleteProduct(productName);

                if (result) {
                    tvProductId.setText("Record Deleted");
                    etProductName.setText("");
                    etQuantity.setText("");
                } else {
                    tvProductId.setText("No Match Found");
                }

            }
        });
    }
}
