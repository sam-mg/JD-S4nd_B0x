package com.jd_s4nd_b0x.mulyam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Search_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_search_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextInputEditText textInputEditText = findViewById(R.id.stocknameInput);
        Button searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(v -> {
            String stockname = textInputEditText.getText() != null ? textInputEditText.getText().toString() : "";
            if (stockname.isEmpty()) {
                Toast.makeText(this, "Enter some Stock Name", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Getting Stock Details", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, Loading_View.class);
                intent.putExtra("stockName", stockname);
                startActivity(intent);
            }
        });
    }
}