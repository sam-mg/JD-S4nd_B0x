package com.jd_s4nd_b0x.getevenlucky;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jd_s4nd_b0x.getevenlucky.Logee.Log_Doubt;
import com.jd_s4nd_b0x.getevenlucky.Stringee.Strings;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Logee Button
        Button logeeButton = findViewById(R.id.button_logee);
        logeeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Log_Doubt.class)));

//        Stringee Button
        Button stringeeButton = findViewById(R.id.button_stringee);
        stringeeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Strings.class)));
    }
}