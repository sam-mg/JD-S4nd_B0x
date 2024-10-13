package com.jd_s4nd_b0x.mulyam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Invalid_Symbol extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_invalid_symbol);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView messageTextView = findViewById(R.id.message);
        messageTextView.setText("The entered stock name doesn't exist. Please try again.");

        Button tryAgainButton = findViewById(R.id.try_again_button);
        tryAgainButton.setOnClickListener(v -> {
            Intent intent = new Intent(Invalid_Symbol.this, Search_View.class);
            startActivity(intent);
            finish();
        });
    }
}